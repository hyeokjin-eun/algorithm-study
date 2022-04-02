package com.company.java.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/10844
public class Backjun10844 {
    private static final String[] array = {
            "1",
            "2",
            "45656"
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
        // d[n][l] = d[n - 1][l - 1] + d[n - 1][l + 1];
        long dp = dp(n);
        bw.write(String.valueOf(dp));
        bw.flush();
    }

    private static long dp (int n) {
        long[][] dp = new long[n + 1][10];
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9 ; j++) {
                dp[i][j] = 0;
                if (j - 1 >= 0) dp[i][j] += dp[i - 1][j - 1];
                if (j + 1 <= 9) dp[i][j] += dp[i - 1][j + 1];
                dp[i][j] %= 1000000000L;
            }
        }

        long answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += dp[n][i];
        }

        return answer % 1000000000L;
    }
}
