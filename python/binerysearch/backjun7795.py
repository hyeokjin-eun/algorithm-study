# https://www.acmicpc.net/problem/7795
import sys

def solve(target: int, b: list[int]):
    l = 0
    r = len(b)
    while l < r:
        mid = (l + r) // 2
        if b[mid] < target:
            l = mid + 1
        else :
            r = mid

    return l

t = int(sys.stdin.readline())


for _ in range(t):
    count = 0
    temp = sys.stdin.readline().split()
    n = int(temp[0])
    m = int(temp[1])

    a = list(map(int, sys.stdin.readline().strip().split()))
    b = list(map(int, sys.stdin.readline().strip().split()))

    b.sort()

    for i in range(n):
        count += solve(a[i], b)

    print(count)



