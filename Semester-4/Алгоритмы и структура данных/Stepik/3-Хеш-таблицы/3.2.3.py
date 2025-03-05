def get_input():
    return input(), input()


def calculate_powers(base, modulus, length):
    power_list = [1] * (length + 1)
    for index in range(1, length + 1):
        power_list[index] = (power_list[index - 1] * base) % modulus
    return power_list


def calculate_hash_value(string, length, base, modulus):
    hash_value = 0
    for index in range(length):
        hash_value = (hash_value * base + ord(string[index])) % modulus
    return hash_value


def rabin_karp_search(pattern, text):
    pattern_length, text_length = len(pattern), len(text)
    base = 53
    modulus = 500009
    power_list = calculate_powers(base, modulus, text_length)
    hash_values = [0] * (text_length - pattern_length + 1)

    hash_value = calculate_hash_value(text, pattern_length, base, modulus)
    hash_values[0] = hash_value

    for i in range(1, text_length - pattern_length + 1):
        hash_value = (hash_value - ord(text[i - 1]) * power_list[pattern_length - 1]) % modulus  
        hash_value = (hash_value * base + ord(text[i + pattern_length - 1])) % modulus  
        hash_values[i] = hash_value

    pattern_hash = calculate_hash_value(pattern, pattern_length, base, modulus)

    positions = []
    for i in range(text_length - pattern_length + 1):
        if pattern_hash != hash_values[i]:
            continue
        if text[i:i + pattern_length] == pattern:
            positions.append(i)

    return positions


if __name__ == '__main__':
    pattern, text = get_input()
    print(*rabin_karp_search(pattern, text))
