def input_reader():
    input_string = ''
    first_line = input()
    n = int(first_line.split()[1])
    for _ in range(n):
        input_string += input() + '\n'

    return first_line + '\n' + input_string


def input_parser(input_string: str):
    lines = input_string.split('\n')
    size = int(lines[0].split()[0])
    n = int(lines[0].split()[1])
    packets = lines[1:n + 1]
    return size, n, packets


def process_packets(size: int, n: int, packets: list):
    buffer = []
    result = []
    
    for packet in packets:
        arrival, duration = map(int, packet.split())
        
        if len(buffer) == size:
            buffer = [p for p in buffer if p[0] > arrival]
        
        if len(buffer) < size:
            if not buffer:
                buffer.append((arrival + duration, arrival))
                result.append(arrival)
            elif buffer[-1][0] <= arrival:
                buffer.append((arrival + duration, arrival))
                result.append(arrival)
            else:
                buffer.append((buffer[-1][0] + duration, arrival))
                result.append(buffer[-2][0])
        else:
            result.append(-1)
    
    return result


if __name__ == '__main__':
    size, n, packets = input_parser(input_reader())
    for result in process_packets(size, n, packets):
        print(result)
