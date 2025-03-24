#
# Complete the 'approximate_linear_least_modules' function below.
#
# The function is expected to return a DOUBLE.
# The function accepts following parameters:
#  1. DOUBLE_ARRAY x_axis
#  2. DOUBLE_ARRAY y_axis
#

def med(ar):
    return ar[len(ar) // 2]

def koefNak(x_axis, y_axis, n_size):
    return [(y_axis[i] - y_axis[j]) / (x_axis[i] - x_axis[j])
        for i in range(n_size) for j in range(i) if x_axis[i] != x_axis[j]]
    

def approximate_linear_least_modules(x_axis, y_axis):
    n_size = len(x_axis)
    
    points = sorted(zip(x_axis, y_axis))
    x_axis, y_axis = zip(*points)
    
    med_x = med(x_axis)
    med_y = med(y_axis)
    
    slopes = koefNak(x_axis, y_axis, n_size)
    
    slopes.sort()
    med_k = slopes[len(slopes) // 2]  # Медианный наклон
    med_b = med_y - med_k * med_x  # Смещение
    
    # Вычисляем отклонения
    res = [abs(y_axis[i] - (med_k * x_axis[i] + med_b)) for i in range(n_size)]
    
    return max(res)