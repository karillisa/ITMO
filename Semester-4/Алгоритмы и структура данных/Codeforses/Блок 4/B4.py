import sys
from collections import deque

def solve(grid, n, m):
    max_volume = 0
    seen = set()
    
    def bfs(i, j):
        queue = deque([(i, j)])
        volume = 0
        while queue:
            x, y = queue.popleft()
            if (x, y) in seen:
                continue
            seen.add((x, y))
            volume += grid[x][y]
            for dx, dy in [(0, 1), (1, 0), (0, -1), (-1, 0)]:
                nx, ny = x + dx, y + dy
                if 0 <= nx < n and 0 <= ny < m and grid[nx][ny] != 0 and (nx, ny) not in seen:
                    queue.append((nx, ny))
        return volume

    for i in range(n):
        for j in range(m):
            if (i, j) not in seen and grid[i][j] != 0:
                max_volume = max(max_volume, bfs(i, j))
    
    return max_volume

if __name__ == "__main__":
    input = sys.stdin.read
    data = input().splitlines()
    t = int(data[0])
    index = 1
    results = []
    
    for _ in range(t):
        n, m = map(int, data[index].split())
        index += 1
        grid = []
        for _ in range(n):
            grid.append(list(map(int, data[index].split())))
            index += 1
        results.append(str(solve(grid, n, m)))
    
    print("\n".join(results))