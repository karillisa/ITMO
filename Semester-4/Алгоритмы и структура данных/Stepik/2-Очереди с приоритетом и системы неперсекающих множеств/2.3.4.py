def input_reader():
    num_nodes, num_equal, num_different = map(int, input().split())
    equal_pairs = [Pair(*map(int, input().split())) for _ in range(num_equal)]
    different_pairs = [Pair(*map(int, input().split())) for _ in range(num_different)]
    return num_nodes, num_equal, num_different, equal_pairs, different_pairs


class Pair:
    def __init__(self, dest_index, src_index):
        self.destination = dest_index
        self.source = src_index


class Node:
    def __init__(self, identifier):
        self.identifier = identifier
        self.parent = identifier


class DisjointSet:
    def __init__(self, count):
        self.nodes = [Node(i) for i in range(1, count + 1)]

    def find(self, index):
        if index != self.nodes[index - 1].parent:
            self.nodes[index - 1].parent = self.find(self.nodes[index - 1].parent)
        return self.nodes[index - 1].parent

    def union(self, dest_index, src_index):
        dest_id = self.find(dest_index)
        src_id = self.find(src_index)
        if dest_id == src_id:
            return
        self.nodes[src_id - 1].parent = self.nodes[dest_id - 1].parent


def execute(num_nodes, num_equal, num_different, equal_pairs, different_pairs):
    ds = DisjointSet(num_nodes)
    for pair in equal_pairs:
        ds.union(pair.destination, pair.source)
    for pair in different_pairs:
        if ds.find(pair.destination) == ds.find(pair.source):
            return 0
    return 1


if __name__ == '__main__':
    n, e, d, eq_pairs, dq_pairs = input_reader()
    print(execute(n, e, d, eq_pairs, dq_pairs))