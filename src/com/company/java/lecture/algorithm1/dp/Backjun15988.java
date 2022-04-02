package com.company.java.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/15988
public class Backjun15988 {
    private static final String[] array = {
            "7\n" +
            "4\n" +
            "7\n" +
            "10\n" +
            "1\n" +
            "2\n" +
            "3\n" +
            "1000000"
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
            long answer = dp(n);
            bw.write(String.valueOf(answer));
            bw.write("\n");
        }

        bw.flush();
    }

    private static long dp (int n) {
        if (n < 2) {
            return 1;
        }

        long[] a = new long[n + 1];
        a[0] = 1;
        a[1] = 1;
        a[2] = 2;
        for (int i = 3; i <= n; i++) {
            a[i] += (a[i - 1] + a[i - 2] + a[i - 3])  % 1000000009;
        }

        return a[n];
    }
}
