package com.company.java.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/17626
public class Backjun17626 {
    private static int N;
    private static int[] a;
    private static final String[] array = {
            "25",
            "26",
            "11339"
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

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        N = stoi(br.readLine());
        a = new int[N + 1];
        a[0] = 0;
        a[1] = 1;
        for (int i = 2; i <= N; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, a[i - j * j]);
            }

            a[i] = min + 1;
        }

        bw.write(String.valueOf(a[N]));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}