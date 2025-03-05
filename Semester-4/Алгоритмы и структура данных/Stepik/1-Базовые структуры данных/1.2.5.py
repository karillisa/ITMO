from collections import deque


def get_input():
    n = int(input())
    numbers = list(map(int, input().split()))
    m = int(input())
    return n, numbers, m


def find_max_in_sliding_window(nums, m):
    if not nums:
        return []
    window = deque()
    max_values = []
    for i, num in enumerate(nums):
        while window and window[0] < i - m + 1:
            window.popleft()
        while window and nums[window[-1]] < num:
            window.pop()
        window.append(i)
        if i >= m - 1:
            max_values.append(nums[window[0]])

    return max_values


if __name__ == '__main__':
    n, array, m = get_input()
    print(*find_max_in_sliding_window(array, m), sep=' ')
