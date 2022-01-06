package com.company.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2747
public class Backjun2747 {
    private static final String[] array = {
            "10"
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
        int[] a = new int[N + 1];
        a[0] = 0;
        a[1] = 1;
        for (int i = 2; i <= N; i++) {
            a[i] = a[i - 2] + a[i - 1];
        }

        bw.write(String.valueOf(a[N]));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}