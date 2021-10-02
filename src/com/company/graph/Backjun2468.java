package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2468
public class Backjun2468 {
    private static final String[] array = {
            "5\n" +
            "6 8 2 6 2\n" +
            "3 2 3 4 6\n" +
            "6 7 3 3 2\n" +
            "7 2 5 3 6\n" +
            "8 9 5 2 7",
            "7\n" +
            "9 9 9 9 9 9 9\n" +
            "9 2 1 2 1 2 9\n" +
            "9 1 8 7 8 1 9\n" +
            "9 2 7 9 7 2 9\n" +
            "9 1 8 7 8 1 9\n" +
            "9 2 1 2 1 2 9\n" +
            "9 9 9 9 9 9 9"
    };

    public static void main(String[] args) throws IOException {
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
        int n = Integer.parseInt(br.readLine());
        int[][] a = new int[n][n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                if (max < a[i][j]) {
                    max = a[i][j];
                }
            }
        }

        int answer = 0;
        for (int k = 0; k < max; k++) {
            boolean[][] check = new boolean[n][n];
            int temp = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[j][i] <= k) {
                        continue;
                    }

                    if (!check[j][i]) {
                        temp++;
                        dfs(check, i, j, n, k, a);
                    }
                }
            }

            if (answer < temp) {
                answer = temp;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static void dfs(boolean[][] check, int x, int y, int n, int k, int[][] a) {
        check[y][x] = true;
        int[] xa = new int[]{1, 0, 0, -1};
        int[] ya = new int[]{0, 1, -1, 0};
        for (int i = 0; i < 4; i++) {
            int nx = x + xa[i];
            int ny = y + ya[i];
            if (nx < 0 || n <= nx || ny < 0 || n <= ny || a[ny][nx] <= k) {
                continue;
            }

            if (!check[ny][nx]) {
                dfs(check, nx, ny, n, k, a);
            }
        }
    }
}
