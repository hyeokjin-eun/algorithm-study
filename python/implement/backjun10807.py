# https://www.acmicpc.net/problem/10807
import sys

n = int(sys.stdin.readline())
arr = list(map(int, sys.stdin.readline().split()))
v = int(sys.stdin.readline())
count = 0
for i in range(n):
    if arr[i] == v:
        count += 1
print(count)