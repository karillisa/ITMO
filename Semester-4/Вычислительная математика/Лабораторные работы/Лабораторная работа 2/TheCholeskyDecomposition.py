class Solution:
    isSolutionExists = True
    errorMessage = ""

    #
    # Complete the 'solveByCholeskyDecomposition' function below.
    #
    # The function is expected to return a DOUBLE_ARRAY.
    # The function accepts following parameters:
    #  1. INTEGER n
    #  2. 2D_DOUBLE_ARRAY matrix

    def kvaMatrix(n, matrix):
        A = [[0.0] * n for _ in range(n)]
        b = [0.0] * n
        for i in range(n):
            for j in range(n):
                A[i][j] = matrix[i][j]
            b[i] = matrix[i][n]
        return [A, b]

    def solveByCholeskyDecomposition(n, matrix):
        res = Solution.kvaMatrix(n, matrix)
        A = res[0]
        b = res[1] 

        Solution.isSimMatrix(n, A)
        
        return Solution.mainFunc(n, A, b)
    
    def isSimMatrix(n , A):
        for i in range(n):
            for j in range(n):
                if abs(A[i][j] - A[j][i]) > 1e-12:
                    Solution.isSolutionExists = False
                    Solution.errorMessage = "The system has no roots of equations or has an infinite set of them."
                    return []
    
    def mainFunc(n,  A, b):
        L = [[0.0] * n for _ in range(n)]
        try:
            for i in range(n):
                for j in range(i + 1):
                    s = 0.0
                    for k in range(j):
                        s += L[i][k] * L[j][k]

                    if i == j:
                        val = A[i][i] - s
                        if val <= 0:
                            Solution.isSolutionExists = False
                            Solution.errorMessage = "The system has no roots of equations or has an infinite set of them."
                            return []
                        L[i][i] = math.sqrt(val)
                    else:
                        L[i][j] = (A[i][j] - s) / L[j][j]
        except:
            Solution.isSolutionExists = False
            Solution.errorMessage = "The system has no roots of equations or has an infinite set of them."
            return []

        y = [0.0] * n
        for i in range(n):
            s = 0.0
            for k in range(i):
                s += L[i][k] * y[k]
            if abs(L[i][i]) < 1e-15:
                Solution.isSolutionExists = False
                Solution.errorMessage = "The system has no roots of equations or has an infinite set of them."
                return []
            y[i] = (b[i] - s) / L[i][i]
        
        x = [0.0] * n
        for i in range(n - 1, -1, -1):
            s = 0.0
            for k in range(i + 1, n):
                s += L[k][i] * x[k]
            if abs(L[i][i]) < 1e-15:
                Solution.isSolutionExists = False
                Solution.errorMessage = "The system has no roots of equations or has an infinite set of them."
                return []
            x[i] = (y[i] - s) / L[i][i]

        return x + y
    
    