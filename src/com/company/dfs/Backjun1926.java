package com.company.dfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1926
public class Backjun1926 {
    private static final String[] array = {
            "6 5\n" +
            "1 1 0 1 1\n" +
            "0 1 1 0 0\n" +
            "0 0 0 0 0\n" +
            "1 0 1 1 1\n" +
            "0 0 1 1 1\n" +
            "0 0 1 1 1",
            "4 5\n" +
            "1 1 1 0 1\n" +
            "1 0 1 0 1\n" +
            "1 0 1 0 1\n" +
            "1 0 1 1 1"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] check = new boolean[n][m];
        int cnt = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!check[i][j] && a[i][j] == 1) {
                    int temp = dfs(j, i, n, m, 1, check, a);
                    cnt++;
                    if (max < temp) {
                        max = temp;
                    }
                }
            }
        }

        bw.write(String.valueOf(cnt));
        bw.write("\n");
        bw.write(String.valueOf(max));
        bw.flush();
    }

    private static int dfs(int x, int y, int n, int m, int index, boolean[][] check, int[][] a) {
        check[y][x] = true;
        int[] xa = new int[]{1, 0, -1, 0};
        int[] ya = new int[]{0, 1, 0, -1};
        int answer = 1;
        for (int i = 0; i < 4; i++) {
            int xn = x + xa[i];
            int yn = y + ya[i];
            if (xn < 0 || yn < 0 || m <= xn || n <= yn) {
                continue;
            }

            if (!check[yn][xn] && a[yn][xn] == 1) {
                int temp = dfs(xn, yn, n, m, index + 1, check, a);
                answer += temp;
            }
        }

        return answer;
    }
}
