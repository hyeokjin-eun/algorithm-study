package com.company.java.math;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1546
public class Backjun1546 {
    private static final String[] array = {
            "3\n" +
            "40 80 60",
            "3\n" +
            "10 20 30",
            "4\n" +
            "1 100 100 100",
            "5\n" +
            "1 2 4 8 16",
            "2\n" +
            "3 10",
            "4\n" +
            "10 20 0 100",
            "1\n" +
            "50",
            "9\n" +
            "10 20 30 40 50 60 70 80 90"
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
        int N = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        double max = 0;
        double[] a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = stoi(st.nextToken());
            max = Math.max(max, a[i]);
        }

        double total = 0;
        for (int i = 0; i < N; i++) {
            a[i] = a[i] / max * 100;
            total += a[i];
        }

        bw.write(String.valueOf(total / N));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}