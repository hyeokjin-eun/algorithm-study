package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/5568
public class Backjun5568 {
    private static final String[] array = {
            "4\n" +
            "2\n" +
            "1\n" +
            "2\n" +
            "12\n" +
            "1",
            "6\n" +
            "3\n" +
            "72\n" +
            "2\n" +
            "12\n" +
            "7\n" +
            "2\n" +
            "1"
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
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        HashSet<String> t = new HashSet<>();
        boolean[] check = new boolean[N];
        bf("", K, a, t, check, N, 0);
        bw.write(String.valueOf(t.size()));
        bw.flush();
    }

    private static void bf(String s, int K, int[] a, HashSet<String> t, boolean[] check, int N, int cnt) {
        if (K == cnt) {
            t.add(s);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!check[i]) {
                check[i] = true;
                bf(s + a[i], K, a, t, check, N, cnt + 1);
                check[i] = false;
            }
        }
    }
}
