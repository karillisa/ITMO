class MinHeap:
    def __init__(self, elements):
        self.array = elements
        self.movements = []
        self._initialize_heap()

    def _initialize_heap(self):
        for index in range(len(self.array) // 2, -1, -1):
            self._sift_down(index)

    def insert(self, value):
        self.array.append(value)
        self._sift_up(len(self.array) - 1)

    def delete(self, index):
        self.array[index] = self.array[-1]
        self.array.pop()
        self._sift_down(index)

    def _get_parent_index(self, index):
        return (index - 1) // 2

    def _sift_up(self, index):
        if index == 0:
            return
        parent = self._get_parent_index(index)
        if self.array[parent] > self.array[index]:
            self.movements.append((parent, index))
            self.array[index], self.array[parent] = self.array[parent], self.array[index]
            self._sift_up(parent)

    def _sift_down(self, index):
        left_child = 2 * index + 1
        right_child = 2 * index + 2
        smallest_index = index
        if left_child < len(self.array) and self.array[left_child] < self.array[smallest_index]:
            smallest_index = left_child
        if right_child < len(self.array) and self.array[right_child] < self.array[smallest_index]:
            smallest_index = right_child
        if index != smallest_index:
            self.movements.append((index, smallest_index))
            self.array[index], self.array[smallest_index] = self.array[smallest_index], self.array[index]
            self._sift_down(smallest_index)

    def display_heap(self):
        print(*self.array)

    def display_movements(self):
        for move in self.movements:
            print(move[0], move[1])

    def total_movements(self):
        print(len(self.movements))


def visualize_heap(array):
    def recursive_print_tree(index, level=0, prefix="Root: "):
        if index < len(array):
            print(" " * (level * 4) + prefix + "{" + str(array[index]) + "," + str(index) + "}")
            if 2 * index + 1 < len(array):
                recursive_print_tree(2 * index + 1, level + 1, "L-- ")
            if 2 * index + 2 < len(array):
                recursive_print_tree(2 * index + 2, level + 1, "R-- ")

    recursive_print_tree(0)


def input_reader():
    n = int(input())
    elements = list(map(int, input().split()))
    return n, elements


if __name__ == '__main__':
    n, elements = input_reader()
    heap = MinHeap(elements)

    heap.total_movements()
    heap.display_movements()
    heap.display_heap()