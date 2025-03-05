def input_reader():
    text = input()
    query_count = int(input())
    queries = [list(map(int, input().split())) for _ in range(query_count)]
    return text, queries


def execute():
    text, queries = input_reader()
    for left, right, index in queries:
        substring = text[left:right + 1]
        text = text[:left] + text[right + 1:]  
        text = text[:index] + substring + text[index:]  
    print(text)


if __name__ == '__main__':
    execute()