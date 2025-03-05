class Processor:
    def __init__(self, index):
        self.index = index
        self.time = 0

    def less_than(self, other):
        if self.time == other.time:
            return self.index < other.index
        return self.time < other.time

    def less_equal(self, other):
        if self.time == other.time:
            return self.index <= other.index
        return self.time <= other.time

    def greater_than(self, other):
        if self.time == other.time:
            return self.index > other.index
        return self.time > other.time

    def greater_equal(self, other):
        if self.time == other.time:
            return self.index >= other.index
        return self.time >= other.time

    def is_equal(self, other):
        return self.time == other.time and self.index == other.index

    def not_equal(self, other):
        return self.time != other.time or self.index != other.index

    def __str__(self):
        return f'Index: {self.index}, Time: {self.time}'


class MinHeap:
    def __init__(self, items, size):
        self.array = items
        self.size = size

    def create_heap(self):
        for index in range(self.size // 2, -1, -1):
            self._sift_down(index)

    def insert(self, element):
        self.array.append(element)
        self._sift_up(self.size - 1)

    def delete(self, index):
        self.array[index] = self.array[-1]
        self.array.pop()
        self._sift_down(index)

    def _sift_up(self, index):
        if index == 0:
            return
        parent_index = (index - 1) // 2
        if self.array[parent_index].greater_than(self.array[index]):
            self.array[index], self.array[parent_index] = self.array[parent_index], self.array[index]
            self._sift_up(parent_index)

    def _sift_down(self, index):
        left_child = 2 * index + 1
        right_child = 2 * index + 2
        smallest_index = index
        if left_child < self.size and self.array[left_child].less_than(self.array[smallest_index]):
            smallest_index = left_child
        if right_child < self.size and self.array[right_child].less_than(self.array[smallest_index]):
            smallest_index = right_child
        if index != smallest_index:
            self.array[index], self.array[smallest_index] = self.array[smallest_index], self.array[index]
            self._sift_down(smallest_index)

    def display_heap(self):
        print(*self.array, sep=' ')


def input_reader():
    first_line = list(map(int, input().split()))
    second_line = list(map(int, input().split()))
    n = first_line[0]
    m = first_line[1]
    return n, m, second_line


def execute():
    num_processors, task_count, second_line = input_reader()
    results = []
    processor_objects = [Processor(i) for i in range(num_processors)]
    processor_queue = MinHeap(processor_objects, num_processors)

    while second_line:
        current_processor = processor_queue.array[0]
        results.append([current_processor.index, current_processor.time])
        current_processor.time += second_line.pop(0)
        processor_queue._sift_down(0)

    return results


if __name__ == '__main__':
    for result in execute():
        print(*result)