package com.company.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1699
public class Backjun1699 {
    private static final String[] array = {
            "7"
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

    public static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n + 1];
        a[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                int t = a[i - (j * j)] + 1;
                if (a[i] == 0 || a[i] > t) {
                    a[i] = t;

                }
            }
        }

        bw.write(String.valueOf(a[n]));
        bw.flush();
    }
}
