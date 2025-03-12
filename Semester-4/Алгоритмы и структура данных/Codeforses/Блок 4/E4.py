from collections import deque


def solve():
    n = int(input())
    a = [0] * n
    g = [set() for _ in range(n)]
    d = [0] * n

    input_values = input().split()
    for i in range(n):
        a[i] = int(input_values[i]) - 1
        g[i].add(a[i])
        g[a[i]].add(i)

    for i in range(n):
        d[i] = len(g[i])

    bamboos = 0
    cycles = 0
    vis = [False] * n

    for i in range(n):
        if not vis[i]:
            q = deque([i])
            vis[i] = True
            component = [i]

            while q:
                u = q.popleft()
                for v in g[u]:
                    if not vis[v]:
                        vis[v] = True
                        q.append(v)
                        component.append(v)

            bamboo = False
            for j in component:
                if d[j] == 1:
                    bamboo = True
                    break

            if bamboo:
                bamboos += 1
            else:
                cycles += 1

    print(f"{cycles + min(bamboos, 1)} {cycles + bamboos}")


t = int(input())
for _ in range(t):
    solve()
    