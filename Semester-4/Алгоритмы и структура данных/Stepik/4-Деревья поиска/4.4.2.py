import sys

sys.setrecursionlimit(10 ** 5)

def get_input(num: int):
    data = []
    for _ in range(num):
        line = list(map(int, input().split()))
        data.append(line)

    return data


class BinaryTree:
    def __init__(self, value, left_index, right_index):
        self.value = value
        self.left_index = left_index
        self.right_index = right_index

    def __str__(self):
        return f"Value = {self.value}, left = ({self.left_index}), right = ({self.right_index})"

    def in_order_traversal(self):
        nodes = []
        if self.left_index != -1:
            nodes.extend(self.left_index.in_order_traversal())
        nodes.append(self.value)
        if self.right_index != -1:
            nodes.extend(self.right_index.in_order_traversal())
        return nodes

    def pre_order_traversal(self):
        nodes = [self.value]
        if self.left_index != -1:
            nodes.extend(self.left_index.pre_order_traversal())
        if self.right_index != -1:
            nodes.extend(self.right_index.pre_order_traversal())
        return nodes

    def post_order_traversal(self):
        nodes = []
        if self.left_index != -1:
            nodes.extend(self.left_index.post_order_traversal())
        if self.right_index != -1:
            nodes.extend(self.right_index.post_order_traversal())
        nodes.append(self.value)
        return nodes

    def is_valid_bst(self):
        sorted_nodes = self.in_order_traversal()
        if not sorted_nodes:
            return True
        return all(sorted_nodes[i] <= sorted_nodes[i + 1] for i in range(len(sorted_nodes) - 1))


if __name__ == '__main__':
    node_data = get_input(int(input()))
    tree_nodes = [BinaryTree(data[0], data[1], data[2]) for data in node_data]

    for node in tree_nodes:
        if node.left_index != -1:
            node.left_index = tree_nodes[node.left_index]
        if node.right_index != -1:
            node.right_index = tree_nodes[node.right_index]

    result = "CORRECT" if not tree_nodes or tree_nodes[0].is_valid_bst() else "INCORRECT"
    print(result)
