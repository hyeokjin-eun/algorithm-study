package com.company.java.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2225
public class Backjun2225 {
    private static final String[] array = {
            ""
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
        String[] nums = br.readLine().split(" ");
        int n = Integer.parseInt(nums[0]);
        int k = Integer.parseInt(nums[1]);
        int[][] a = new int[k + 1][n + 1];
        a[0][0]= 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                for (int l = 0; l <= j; l++) {
                    a[i][j] += a[i - 1][j - l];
                    a[i][j] %= 1000000000L;
                }
            }
        }

        bw.write(String.valueOf(a[k][n]));
        bw.flush();
    }
}
