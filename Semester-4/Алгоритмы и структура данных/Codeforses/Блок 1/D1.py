import math

def process_case():
    count_of_numbers = int(input())
    numbers = list(map(int, input().split()))
    logarithmic_sum = sum(math.log(num) for num in numbers)
    
    geometric_mean = math.exp(logarithmic_sum / count_of_numbers)
    rounded_geometric_mean = round(geometric_mean)
    
    if (abs(geometric_mean - rounded_geometric_mean) <= 0.00001 and 
        all(int(num) == 1 or math.gcd(int(num), rounded_geometric_mean) != 1 for num in numbers)):
        print("YES")
    else:
        print("NO")

if __name__ == '__main__':
    test_cases = int(input())
    for _ in range(test_cases):
        process_case()