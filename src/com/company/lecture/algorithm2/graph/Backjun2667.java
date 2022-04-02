package com.company.lecture.algorithm2.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2667
public class Backjun2667 {
    private static final String[] array = {
            "7\n" +
            "0110100\n" +
            "0110101\n" +
            "1110101\n" +
            "0000111\n" +
            "0100000\n" +
            "0111110\n" +
            "0111000"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int n = Integer.parseInt(br.readLine());
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(str[j]);
            }
        }

        boolean[][] check = new boolean[n][n];
        int cnt = 0;
        int[] temp = new int[(n * n / 2) + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1 && !check[i][j]) {
                    temp[cnt] = dfs(i, j, n, check, a);
                    cnt++;
                }
            }
        }

        Arrays.sort(temp, 0, cnt);
        bw.write(String.valueOf(cnt));
        bw.write("\n");
        for (int i = 0; i < cnt; i++) {
            bw.write(String.valueOf(temp[i]));
            if (i != cnt - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int dfs(int x, int y, int n, boolean[][] check, int[][] a) {
        check[x][y] = true;
        int answer = 1;
        int[] xt = new int[]{1, 0, -1, 0};
        int[] yt = new int[]{0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            if (0 <= (x + xt[i]) && (x + xt[i]) < n && 0 <= (y + yt[i]) && (y + yt[i]) < n) {
                if (a[x + xt[i]][y + yt[i]] == 1 && !check[x + xt[i]][y + yt[i]]) {
                    answer += dfs(x + xt[i], y + yt[i], n, check, a);
                }
            }
        }

        return answer;
    }
}
