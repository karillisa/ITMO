def input_data():
    n, m = map(int, input().split())
    sizes = list(map(int, input().split()))
    db = Database(sizes)
    operations = [Operation(*map(int, input().split())) for _ in range(m)]
    return n, m, db, operations


class TableEntry:
    def __init__(self, index: int, size):
        self.index = index
        self.size = size
        self.parent = index

    def __str__(self):
        return f'Index: {self.index}, Size: {self.size}, Parent: {self.parent}'


class Database:
    def __init__(self, sizes):
        self.entries = [TableEntry(i, sizes[i - 1]) for i in range(1, len(sizes) + 1)]
        self.largest_size = max(sizes)

    def find_parent(self, index):
        if index != self.entries[index - 1].parent:
            self.entries[index - 1].parent = self.find_parent(self.entries[index - 1].parent)
        return self.entries[index - 1].parent

    def merge(self, dest_index, src_index):
        dest_parent = self.find_parent(dest_index)
        src_parent = self.find_parent(src_index)
        if dest_parent == src_parent:
            return
        if self.entries[dest_parent - 1].size >= self.entries[src_parent - 1].size:
            self.entries[src_parent - 1].parent = dest_parent
            self.entries[dest_parent - 1].size += self.entries[src_parent - 1].size
            if self.entries[dest_parent - 1].size > self.largest_size:
                self.largest_size = self.entries[dest_parent - 1].size
        else:
            self.entries[dest_parent - 1].parent = src_parent
            self.entries[src_parent - 1].size += self.entries[dest_parent - 1].size
            if self.entries[src_parent - 1].size > self.largest_size:
                self.largest_size = self.entries[src_parent - 1].size


class Operation:
    def __init__(self, dest_index, src_index):
        self.destination = dest_index
        self.source = src_index

    def __str__(self):
        return f'Destination: {self.destination}, Source: {self.source}'


def execute_operations(db, ops):
    for op in ops:
        db.merge(op.destination, op.source)
        print(db.largest_size)


if __name__ == '__main__':
    n, m, database, queries = input_data()
    execute_operations(database, queries)