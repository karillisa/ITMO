def input_operations():
    operations = []
    n = int(input())
    for _ in range(n):
        operations.append(input())

    return operations


class MaxStack:
    def __init__(self):
        self.stack = []
        self.max_values = []

    def push(self, value):
        self.stack.append(value)
        if not self.max_values or value >= self.max_values[-1]:
            self.max_values.append(value)

    def pop(self):
        if self.stack and self.stack[-1] == self.max_values[-1]:
            self.max_values.pop()
        if self.stack:
            self.stack.pop()

    def get_max(self):
        return self.max_values[-1] if self.max_values else 0


def execute_operations():
    operations = input_operations()
    stack = MaxStack()
    for operation in operations:
        if operation.startswith('push'):
            stack.push(int(operation.split()[1]))
        elif operation == 'pop':
            stack.pop()
        elif operation == 'max':
            print(stack.get_max())
        else:
            print("Unknown operation")


if __name__ == '__main__':
    execute_operations()
