package com.company.java.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2579
public class Backjun2579 {
    private static int N;
    private static int[] a;
    private static int[] dp;
    private static final String[] array = {
            "6\n" +
            "10\n" +
            "20\n" +
            "15\n" +
            "25\n" +
            "10\n" +
            "20",
            "TEST MADE"
    };

    public static void main (String[] args) throws IOException {
        // TEST
        test();
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

    private static void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("300").append("\n");
        for (int i = 0; i< 300; i++) {
            sb.append("10000");
            if (i != 299) {
                sb.append("\n");
            }
        }

        array[1] = sb.toString();
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        N = stoi(br.readLine());
        dp = new int[N + 1];
        a = new int[N + 1];
        Arrays.fill(dp, -1);
        for (int i = 1; i <= N; i++) {
            a[i] = stoi(br.readLine());
        }

        dp[0] = a[0];
        dp[1] = a[1];

        if (N >= 2) {
            dp[2] = a[1] + a[2];
        }

        int answer = recursion(N);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion(int n) {
        if (dp[n] == -1) {
            dp[n] = Math.max(recursion(n - 2), recursion(n - 3) + a[n - 1]) + a[n];
        }

        return dp[n];
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}