package com.company.java.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2228
// TODO: 2021-10-08 다시 풀어볼것
public class Backjun2228 {
    private static final String[] array = {
            "6 2\n" +
            "-1\n" +
            "3\n" +
            "1\n" +
            "2\n" +
            "4\n" +
            "-1"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] a= new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < N - 1; i++) {
            int temp = recursion(M, N, 1, a[i], i, a);
            if (answer < temp) {
                answer = temp;
            }
        }

        System.out.println();
    }

    private static int recursion(int M, int N, int index, int total, int i, int[] a) {
        if (M < index) {
            return total;
        } else {
            if (N - 2 <= i) {
                return Integer.MIN_VALUE;
            } else {
                int ta = recursion(M, N, index, total + a[i + 1], i + 1, a);
                int tb = recursion(M, N, index + 1, total + a[i + 2], i + 2, a);
                return Math.max(ta, tb);
            }
        }
    }
}
