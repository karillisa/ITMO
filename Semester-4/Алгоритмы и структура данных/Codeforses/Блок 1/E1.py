import itertools

class E1:
    @staticmethod
    def calculate_min_permutation_difference(data, k):
        min_difference = float('inf')
        
        for permutation in itertools.permutations(range(k)):
            current_min = float('inf')
            current_max = float('-inf')

            for number in data:
                combined_num = ''.join(number[idx] for idx in permutation)
                combined_num = int(combined_num)
                current_max = max(current_max, combined_num)
                current_min = min(current_min, combined_num)

            difference = current_max - current_min
            min_difference = min(min_difference, difference)

        return min_difference

if __name__ == "__main__":
    n, k = map(int, input().split())
    input_data = [input() for _ in range(n)]
    result = E1.calculate_min_permutation_difference(input_data, k)
    print(result)