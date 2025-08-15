# https://www.acmicpc.net/problem/25314
import sys

n = int(sys.stdin.readline())
divid = 4
share = int(n / divid)
print(share * "long " + "int")