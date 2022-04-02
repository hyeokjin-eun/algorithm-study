package com.company.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2156
public class Backjun2156 {
    private static final String[] array = {
            "6\n" +
            "6\n" +
            "10\n" +
            "13\n" +
            "9\n" +
            "8\n" +
            "1",
            "6\n" +
            "100\n" +
            "400\n" +
            "2\n" +
            "1\n" +
            "4\n" +
            "200",
            "10\n" +
            "0\n" +
            "0\n" +
            "10\n" +
            "0\n" +
            "5\n" +
            "10\n" +
            "0\n" +
            "0\n" +
            "1\n" +
            "10",
            "7\n" +
            "100\n" +
            "100\n" +
            "1\n" +
            "1\n" +
            "1\n" +
            "100\n" +
            "100",
            "1\n" +
            "10"
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
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        int[] temp = new int[10001];
        temp[1] = a[1];
        if (1 < n) {
            temp[2] = a[1] + a[2];
        }

        for (int i = 3; i <= n; i++) {
            temp[i] = Math.max(temp[i - 1], Math.max(temp[i - 2] + a[i], temp[i - 3] + a[i - 1] + a[i]));
        }

        bw.write(String.valueOf(temp[n]));
        bw.flush();
    }
}
