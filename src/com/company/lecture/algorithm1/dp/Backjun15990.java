package com.company.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/15990
public class Backjun15990 {
    private static final String[] array = {
            "3\n" +
            "4\n" +
            "7\n" +
            "10"
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
        int index = Integer.parseInt(br.readLine());
        long[][] dp = dp();
        for (int i = 0; i < index; i++) {
            int n = Integer.parseInt(br.readLine());
            bw.write(String.valueOf((dp[n][1] + dp[n][2] + dp[n][3]) % 1000000009L));
            bw.write("\n");
        }

        bw.flush();
    }

    private static long[][] dp () {
        long[][] dp = new long[100001][4];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;
        for (int i = 4; i < 100001; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % 1000000009L;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % 1000000009L;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % 1000000009L;
        }

        return dp;
    }
}
