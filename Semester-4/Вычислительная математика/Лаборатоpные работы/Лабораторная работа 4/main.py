import math
import numpy as np
import matplotlib.pyplot as plt

class Result:
    error_message = ""
    has_discontinuity = False
    small_epsilon = 0.000001  # Добавляем небольшое значение eps для обработки случая sin(x)/x при x = 0

    def reciprocal_function(x: float):
        return 1 / x

    def sinc_function(x: float):
        if x == 0:
            return (math.sin(Result.small_epsilon) / Result.small_epsilon + math.sin(-Result.small_epsilon) / -Result.small_epsilon) / 2
        return math.sin(x) / x

    def quadratic_function(x: float):
        return x * x + 2

    def linear_function(x: float):
        return 2 * x + 2

    def logarithmic_function(x: float):
        return math.log(x)

    # How to use thos function:
    # func = Result.get_function(4)
    # func(0.01)
    def get_function(n: int):
        if n == 1:
            return Result.reciprocal_function
        elif n == 2:
            return Result.sinc_function
        elif n == 3:
            return Result.quadratic_function
        elif n == 4:
            return Result.linear_function
        elif n == 5:
            return Result.logarithmic_function
        else:
            raise NotImplementedError(f"Функция {n} не определена.")

    #
    # Complete the 'calculate_integral' function below.
    #
    # The function is expected to return a DOUBLE.
    # The function accepts following parametres:
    #  1. DOUBLE a
    #  2. DOUBLE b
    #  3. INTEGER f
    #  4. DOUBLE epsilon
    #
    def calculate_integral(a, b, f, epsilon):
        selected_function = Result.get_function(f)

        # Проверка на наличие разрыва в функции на заданном интервале
        if (f == 1 and (a <= 0 or b <= 0)) or (f == 5 and (a <= 0 or b <= 0)):
            Result.has_discontinuity = True
            Result.error_message = "Integrated function has discontinuity or does not defined in current interval"
            return 0

        # Метод трапеций
        number_of_subintervals = 1
        integral_value = 0.5 * (b - a) * (selected_function(a) + selected_function(b))
        difference_between_iterations = float('inf')

        while difference_between_iterations >= epsilon:
            number_of_subintervals *= 2
            subinterval_length = (b - a) / number_of_subintervals
            new_integral_value = 0.5 * (selected_function(a) + selected_function(b))
            for i in range(1, number_of_subintervals):
                new_integral_value += selected_function(a + i * subinterval_length)
            new_integral_value *= subinterval_length

            difference_between_iterations = abs(new_integral_value - integral_value)
            integral_value = new_integral_value

        if a > b:
            integral_value = -integral_value

        return integral_value

if __name__ == '__main__':
    a = float(input().strip())
    b = float(input().strip())
    f = int(input().strip())
    epsilon = float(input().strip())

    result = Result.calculate_integral(a, b, f, epsilon)
    if not Result.has_discontinuity:
        print(str(result) + '\n')
    else:
        print(Result.error_message + '\n')

    Result.plot_function(a, b, f, epsilon)
