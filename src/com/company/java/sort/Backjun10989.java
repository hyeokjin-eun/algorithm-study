package com.company.java.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10989
// TODO: 2021-10-22 시간 더 절약할 방법 생각
public class Backjun10989 {
    private static final String[] array = {
            "10\n" +
            "5\n" +
            "2\n" +
            "3\n" +
            "1\n" +
            "4\n" +
            "2\n" +
            "3\n" +
            "5\n" +
            "1\n" +
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

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(a);
        for (int i = 0; i < N; i++) {
            bw.write(String.valueOf(a[i]));
            if (i != N - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }
}