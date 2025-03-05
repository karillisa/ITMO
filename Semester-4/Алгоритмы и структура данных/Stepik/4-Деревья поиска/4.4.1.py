def get_input(count: int):
    values_list = []
    for _ in range(count):
        values = list(map(int, input().split()))
        values_list.append(values)

    return values_list


class BinaryTree:
    def __init__(self, value, left_index, right_index):
        self.value = value
        self.left_index = left_index
        self.right_index = right_index

    def __str__(self):
        return f"Value = {self.value}, left = ({self.left_index}), right = ({self.right_index})"

    def in_order_traversal(self):
        result = []
        if self.left_index != -1:
            result.extend(self.left_index.in_order_traversal())
        result.append(self.value)
        if self.right_index != -1:
            result.extend(self.right_index.in_order_traversal())
        return result

    def pre_order_traversal(self):
        result = [self.value]
        if self.left_index != -1:
            result.extend(self.left_index.pre_order_traversal())
        if self.right_index != -1:
            result.extend(self.right_index.pre_order_traversal())
        return result

    def post_order_traversal(self):
        result = []
        if self.left_index != -1:
            result.extend(self.left_index.post_order_traversal())
        if self.right_index != -1:
            result.extend(self.right_index.post_order_traversal())
        result.append(self.value)
        return result


if __name__ == '__main__':
    node_data = get_input(int(input()))
    tree_nodes = [BinaryTree(data[0], data[1], data[2]) for data in node_data]

    for node in tree_nodes:
        if node.left_index != -1:
            node.left_index = tree_nodes[node.left_index]
        if node.right_index != -1:
            node.right_index = tree_nodes[node.right_index]

    print(*tree_nodes[0].in_order_traversal())
    print(*tree_nodes[0].pre_order_traversal())
    print(*tree_nodes[0].post_order_traversal())
