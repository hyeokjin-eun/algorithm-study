# https://www.acmicpc.net/problem/25304
import sys

total_price = int(sys.stdin.readline())
n = int(sys.stdin.readline())
temp_price = 0
for _ in range(n):
    price, count = map(int, sys.stdin.readline().split())
    temp_price += price * count

print("Yes" if total_price == temp_price else "No")