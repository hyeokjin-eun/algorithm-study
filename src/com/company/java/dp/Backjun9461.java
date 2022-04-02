package com.company.java.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/9461
public class Backjun9461 {
    private static long[] a;
    private static final String[] array = {
            "3\n" +
            "6\n" +
            "12\n" +
            "100"
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
        int T = stoi(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = stoi(br.readLine());
            a = new long[101];
            Arrays.fill(a, -1);
            a[0] = 0;
            a[1] = 1;
            a[2] = 1;
            a[3] = 1;
            long answer = recursion(N);
            bw.write(String.valueOf(answer));
            if (t != T - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static long recursion(int n) {
        if (a[n] == -1) {
            a[n] = recursion(n - 2) + recursion(n - 3);
        }

        return a[n];
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}