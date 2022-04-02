package com.company.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1890
public class Backjun1890 {
    private static final String[] array = {
            "4\n" +
            "2 3 3 1\n" +
            "1 2 1 3\n" +
            "1 2 3 1\n" +
            "3 1 1 0"
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
        int[][] board = new int[n][n];
        long[][] dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0 || (i == n - 1 && j == n - 1)) {
                    continue;
                }

                int dist = board[i][j];
                if (i + dist < n) {
                    dp[i + dist][j] += dp[i][j];
                }

                if (j + dist < n) {
                    dp[i][j + dist] += dp[i][j];
                }
            }
        }

        long answer = dp[n - 1][n - 1];
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
