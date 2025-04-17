import math
import os
import random
import re
import sys


class Solution:

    #
    # Complete the 'solve_by_power_method' function below.
    #
    # The function is expected to return a double.
    # The function accepts following parameters:
    #  1. INTEGER n
    #  2. 2D_DOUBLE_ARRAY matrix
    #
    def solve_by_power_method(n, matrix):
        x = [1.0] * n  # Начальное приближение вектора
        epsilon = 1e-6  # Точность
        lambda_prev = 0
        while True:
            y = [0] * n
            for i in range(n):
                for j in range(n):
                    y[i] += matrix[i][j] * x[j]
            lambda_new = max(y, key=abs)
            x = [yi / lambda_new for yi in y]
            if abs(lambda_new - lambda_prev) < epsilon:
                break
            lambda_prev = lambda_new
        return lambda_new


if __name__ == '__main__':
    n = int(input().strip())

    matrix_rows = n
    matrix_columns = n

    matrix = []

    for _ in range(matrix_rows):
        matrix.append(list(map(float, input().rstrip().split())))

    result = Solution.solve_by_power_method(n, matrix)
    print(str(result))
    print("")
