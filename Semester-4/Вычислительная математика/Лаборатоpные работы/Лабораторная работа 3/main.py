def slve(f, number_of_unknowns, initial_approximations):
    itr = 0
    mxItr = 20000
    eps = 1e-5   
    coeff = [0.001] * number_of_unknowns
    crApx = initial_approximations[:]
    
    while itr < mxItr:
        tmpApxs = crApx.copy()
        for j in range(number_of_unknowns):
            x_1 = f[j](crApx)
            tmpAppox = crApx[j] + coeff[j] * (x_1 - crApx[j])
            tmpApxs[j] = tmpAppox 
            if abs(x_1 - crApx[j]) < eps:
                coeff[j] *= 1.1
            else:
                coeff[j] *= 0.9
        if all(abs(tmpApxs[j] - crApx[j]) < eps for jj in range(number_of_unknowns)):
            break
        crApx = tmpApxs 

    return [round(res, 5) for res in crApx]

def solve_by_fixed_point_iterations(system_id, number_of_unknowns, initial_approximations):
    f = get_functions(system_id)
    
    return slve(f, number_of_unknowns, initial_approximations)

