from collections import deque


def reader():
    size = int(input())
    count = int(input())
    commands = [input().split() for _ in range(count)]
    return size, count, commands


def hash_string(string, size):
    hash_value = 0
    prime_base = 263
    large_prime = 1000000007
    for i in range(len(string)):
        hash_value += ord(string[i]) * (prime_base ** i)
    return hash_value % large_prime % size


class Table:
    def __init__(self, size):
        self.size = size
        self.table = {i: deque() for i in range(size)}

    def add(self, data):
        hash = hash_string(data, self.size)
        if data not in self.table[hash]:
            self.table[hash].appendleft(data)

    def delete(self, data):
        hash = hash_string(data, self.size)
        if data in self.table[hash]:
            self.table[hash].remove(data)

    def find(self, data):
        hash = hash_string(data, self.size)
        return "yes" if data in self.table[hash] else "no"

    def check(self, data):
        return " ".join(self.table[int(data)])


def run():
    size, count, queries = reader()
    table = Table(size)

    for query in queries:
        output = None
        if query[0] == 'del':
            table.delete(query[1])
        else:
            output = getattr(table, query[0])(query[1])
        if output:
            print(output)


if __name__ == '__main__':
    run()
