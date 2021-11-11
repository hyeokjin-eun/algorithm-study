package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16985
public class Backjun16985 {
    private static final int n = 5;
    private static final String[] array = {
            "1 1 1 1 1\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "1 1 1 1 1\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "1 1 1 1 1\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "1 1 1 1 1\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "1 1 1 1 1\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0",
            "1 1 1 1 1\n" +
            "1 0 0 0 1\n" +
            "1 0 0 0 1\n" +
            "1 0 0 0 1\n" +
            "1 1 1 1 1\n" +
            "0 0 0 0 0\n" +
            "0 1 1 1 0\n" +
            "0 1 0 1 0\n" +
            "0 1 1 1 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 1 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 1 1 1 0\n" +
            "0 1 0 1 0\n" +
            "0 1 1 1 0\n" +
            "0 0 0 0 0\n" +
            "1 1 1 1 1\n" +
            "1 0 0 0 1\n" +
            "1 0 0 0 1\n" +
            "1 0 0 0 1\n" +
            "1 1 1 1 1",
            "1 1 1 1 1\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "1 1 1 1 1\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "1 1 1 1 1\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "1 1 1 1 1\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "1 1 1 1 1",
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1",
            "0 0 0 1 0\n" +
            "0 0 0 0 0\n" +
            "1 0 1 1 1\n" +
            "0 0 0 1 0\n" +
            "0 0 1 0 0\n" +
            "0 1 0 0 0\n" +
            "1 1 0 0 0\n" +
            "1 0 0 1 0\n" +
            "0 1 1 1 0\n" +
            "0 1 0 1 0\n" +
            "0 0 1 0 0\n" +
            "1 0 0 0 0\n" +
            "0 1 0 0 0\n" +
            "0 0 1 0 0\n" +
            "1 1 1 0 0\n" +
            "1 0 0 0 1\n" +
            "1 0 0 0 0\n" +
            "0 0 1 0 1\n" +
            "0 1 1 0 0\n" +
            "0 1 0 0 0\n" +
            "0 0 0 1 0\n" +
            "1 0 0 0 0\n" +
            "0 0 1 0 0\n" +
            "0 1 0 0 1\n" +
            "0 1 0 0 0",
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "1 0 0 0 1\n" +
            "0 0 1 0 0\n" +
            "0 0 1 1 1\n" +
            "0 1 0 0 1\n" +
            "0 0 0 0 1\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 1 0 0 0\n" +
            "0 1 0 0 1\n" +
            "1 0 0 1 0\n" +
            "0 0 0 1 0\n" +
            "0 1 1 0 0\n" +
            "0 1 0 0 0\n" +
            "1 0 1 0 0\n" +
            "0 0 0 0 0\n" +
            "1 0 0 0 0\n" +
            "0 0 0 1 0\n" +
            "1 0 0 0 0\n" +
            "0 0 0 1 0\n" +
            "0 0 0 0 1\n" +
            "1 1 0 0 0\n" +
            "1 0 0 1 1\n" +
            "1 0 0 0 0",
            "1 1 0 0 0\n" +
            "0 0 0 0 1\n" +
            "0 0 1 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 1 1 1\n" +
            "1 0 0 0 0\n" +
            "0 0 1 0 0\n" +
            "0 0 1 1 1\n" +
            "0 0 1 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 1 0 1\n" +
            "0 0 0 0 0\n" +
            "0 0 0 1 0\n" +
            "0 0 1 0 1\n" +
            "0 0 1 0 0\n" +
            "1 0 0 0 0\n" +
            "0 0 1 1 0\n" +
            "1 0 1 0 0\n" +
            "0 0 1 0 1\n" +
            "0 0 1 1 0\n" +
            "1 1 0 1 1\n" +
            "0 0 0 0 1\n" +
            "0 1 0 1 0\n" +
            "0 1 0 0 0",
            "0 0 1 0 0\n" +
            "0 0 0 0 0\n" +
            "1 1 0 0 0\n" +
            "0 0 1 0 0\n" +
            "1 1 1 0 0\n" +
            "0 0 0 0 1\n" +
            "1 0 0 0 0\n" +
            "0 1 0 0 1\n" +
            "0 0 0 0 0\n" +
            "0 1 0 1 0\n" +
            "1 0 0 0 1\n" +
            "1 1 1 1 1\n" +
            "1 1 0 0 0\n" +
            "0 0 0 1 0\n" +
            "0 0 0 1 0\n" +
            "0 0 0 1 1\n" +
            "0 0 1 0 0\n" +
            "0 1 1 1 0\n" +
            "1 0 0 0 0\n" +
            "0 1 1 0 1\n" +
            "0 1 0 0 0\n" +
            "0 0 0 1 0\n" +
            "1 0 0 0 0\n" +
            "0 0 0 1 0\n" +
            "0 0 0 1 0"
    };

    public static void main (String[] args) throws IOException {
        for (int i = 0; i < array.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            long before = System.currentTimeMillis();
            solution(array[i]);
            long after = System.currentTimeMillis();
            System.out.println();
            System.out.println("===== Time : " + (after - before) + "   =====");
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        // 미로 배열
        int[][][] a = new int[n][n][n];
        int[][][] at = new int[n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    a[i][j][k] = Integer.parseInt(st.nextToken());
                    at[i][j][k] = a[i][j][k];
                }
            }
        }

        // 순열용 배열
        int[] d = new int[n];
        int[] dt = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = i;
            dt[i] = i;
        }

        int answer = -1;
        // 순서 섞기
        do {
            for (int i = 0; i < n; i++) {
                at[dt[i]] = a[d[i]];
            }

            // 미로 회전
            int temp = recursion(0, at);
            if (temp != -1) {
                if (answer == -1 || temp < answer) {
                    answer = temp;
                }
            }
        } while (nextPermutation(dt));
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static boolean nextPermutation(int[] d) {
        int i = d.length - 1;
        while (i > 0 && d[i - 1] >= d[i]) {
            i--;
        }

        if (i < 1) {
            return false;
        }

        int j = d.length - 1;
        while (d[i - 1] >= d[j]) {
            j--;
        }

        int temp = d[i - 1];
        d[i - 1] = d[j];
        d[j] = temp;
        j = d.length - 1;
        while (i < j) {
            temp = d[i];
            d[i] = d[j];
            d[j] = temp;
            i++;
            j--;
        }

        return true;
    }

    private static int recursion(int index, int[][][] a) {
        if (index == n) {
            // 최단 거리 순회
            return bfs(a);
        }

        int answer = -1;
        for (int i = 0; i <= 3; i++) {
            // 시계 회전
            for (int j = 1; j <= i; j++) {
                turn(a[index], true);
            }

            int temp = recursion(index + 1, a);
            if (temp != -1) {
                if (answer == -1 || temp < answer) {
                    answer = temp;
                }
            }

            // 반시계 회전
            for (int j = 1; j <= i; j++) {
                turn(a[index], false);
            }
        }

        return answer;
    }

    private static final int[] xa = new int[]{1, 0, -1, 0, 0, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1, 0, 0};
    private static final int[] za = new int[]{0, 0, 0, 0, 1, -1};
    private static int bfs(int[][][] a) {
        if (a[n - 1][n - 1][n - 1] != 1) return -1;
        Queue<Pair> queue = new LinkedList<>();
        if (a[0][0][0] == 1) queue.offer(new Pair(0, 0, 0, 0));
        boolean[][][] check = new boolean[n][n][n];
        check[0][0][0] = true;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int x = pair.x;
            int y = pair.y;
            int z = pair.z;
            int c = pair.c;
            if (x == n - 1 && y == n - 1 && z == n - 1) {
                return c;
            }

            for (int i = 0; i < 6; i++) {
                int nx = x + xa[i];
                int ny = y + ya[i];
                int nz = z + za[i];
                if (nx < 0 || ny < 0 || nz < 0 || n <= nx || n <= ny || n <= nz) {
                    continue;
                }

                if (!check[nz][ny][nx] && a[nz][ny][nx] == 1) {
                    check[nz][ny][nx] = true;
                    queue.offer(new Pair(nx, ny, nz, c + 1));
                }
            }
        }

        return -1;
    }

    // 회전
    private static void turn(int[][] a, boolean turn) {
        int m = (n / 2);
        for (int i = 1; i <= m; i++) {
            for (int j = m - i; j < m + i; j++) {
                int temp = a[m - i][m - i];
                int min = m - i;
                int max = m + i;
                if (turn)  {
                    // 시계 방향
                    temp = moveX(min, m, i, temp, a, false);
                    temp = moveY(max, m, i, temp, a, false);
                    temp = moveX(max, m, i, temp, a, true);
                    moveY(min, m, i, temp, a, true);
                } else {
                    // 반시계 방향
                    temp = moveY(min, m, i, temp, a, false);
                    temp = moveX(max, m, i, temp, a, false);
                    temp = moveY(max, m, i, temp, a, true);
                    moveX(min, m, i, temp, a, true);
                }
            }
        }
    }

    private static int moveX(int y, int m, int i, int temp, int[][] a, boolean f) {
        int sx = f ? m + i - 1 : m - i + 1;
        for (int x = sx; m - i <= x && x <= m + i; x += f ? -1 : 1) {
            int t = a[y][x];
            a[y][x] = temp;
            temp = t;
        }

        return temp;
    }

    private static int moveY(int x, int m, int i, int temp, int[][] a, boolean f) {
        int sy = f ? m + i - 1 : m - i + 1;
        for (int y = sy; m - i <= y && y <= m + i; y += f ? -1 : 1) {
            int t = a[y][x];
            a[y][x] = temp;
            temp = t;
        }

        return temp;
    }

    private static class Pair {
        int x;
        int y;
        int z;
        int c;
        public Pair(int x, int y, int z, int c) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.c = c;
        }
    }
}