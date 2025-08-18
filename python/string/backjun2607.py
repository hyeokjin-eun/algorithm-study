# https://www.acmicpc.net/problem/2607
import sys

n = int(sys.stdin.readline())
diffs = [[0 for j in range(26)] for i in range(n)]
words = []
for i in range(n):
    for c in sys.stdin.readline().strip() :
        diffs[i][int(ord(c) - ord('A'))] += 1

count = 0
for i in range(1, n):
    diff1 = 0
    diff2 = 0
    for j in range(26):
        if diffs[0][j] > diffs[i][j]:
            diff1 += diffs[0][j] - diffs[i][j]
        if diffs[i][j] > diffs[0][j]:
            diff2 += diffs[i][j] - diffs[0][j]

    if (
        diff1 == 0 and diff2 == 0 or
        diff1 == 1 and diff2 == 0 or
        diff1 == 0 and diff2 == 1 or
        diff1 == 1 and diff2 == 1
    ):
        count += 1

print(count)
