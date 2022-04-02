package com.company.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/9465
public class Backjun9465 {
    private static final String[] array = {
            "2\n" +
            "5\n" +
            "50 10 100 20 40\n" +
            "30 50 70 10 60\n" +
            "7\n" +
            "10 30 10 50 100 20 40\n" +
            "20 40 30 50 60 20 80"
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
        for (int i = 0; i < index; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] a = new int[n + 1][2];
            String[] first = br.readLine().split(" ");
            String[] second = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                a[j][0] = Integer.parseInt(first[j - 1]);
                a[j][1] = Integer.parseInt(second[j - 1]);
            }

            int max = dp(n, a);
            bw.write(String.valueOf(max));
            bw.write("\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static int dp (int n, int[][] a) {
        for (int i = 2; i <= n; i++) {
            a[i][0] = Math.max(a[i - 1][1], a[i - 2][1]) + a[i][0];
            a[i][1] = Math.max(a[i - 1][0], a[i - 2][0]) + a[i][1];
        }

        return Math.max(a[n][0], a[n][1]);
    }
}
