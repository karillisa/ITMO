import math
import os
import random
import re
import sys


class Result:
    def first_function(x: float, y: float):
        return math.sin(x)

    def second_function(x: float, y: float):
        return (x * y) / 2

    def third_function(x: float, y: float):
        return y - (2 * x) / y

    def fourth_function(x: float, y: float):
        return x + y

    def default_function(x: float, y: float):
        return 0.0

    def get_function(function_number: int):
        if function_number == 1:
            return Result.first_function
        elif function_number == 2:
            return Result.second_function
        elif function_number == 3:
            return Result.third_function
        elif function_number == 4:
            return Result.fourth_function
        else:
            return Result.default_function

    def solveByEulerImproved(f, epsilon, a, y_a, b):
        selected_function = Result.get_function(f)
        step_size = 0.01  
        current_x = a
        current_y = y_a

        while current_x < b:
            k1 = step_size * selected_function(current_x, current_y)
            k2 = step_size * selected_function(current_x + step_size, current_y + k1)
            current_y = current_y + (k1 + k2) / 2
            current_x = current_x + step_size
        return current_y


if __name__ == '__main__':
    f = int(input().strip())

    epsilon = float(input().strip())

    a = float(input().strip())

    y_a = float(input().strip())

    b = float(input().strip())

    result = Result.solveByEulerImproved(f, epsilon, a, y_a, b)

    print(str(result) + '\n')