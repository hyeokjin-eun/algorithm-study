package com.company.bronze1;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2748
public class Backjun2748 {
    private static final String[] array = {
            "10",
            "3",
            "2",
            "1",
            "0",
            "90"
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
        long[] a = new long[91];
        a[0] = 0;
        a[1] = 1;
        for (int i = 2; i <= n ; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }

        bw.write(String.valueOf(a[n]));
        bw.flush();
    }
}
