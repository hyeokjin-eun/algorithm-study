package com.company.java.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2470
public class Backjun2470 {
    private static final String[] array = {
            "5\n" +
            "-2 4 -99 -1 98"
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
        long[] a = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());

        }

        Arrays.sort(a);
        int left = 0;
        int right = N - 1;
        long min = 0;
        long max = 0;
        long temp = Long.MAX_VALUE;
        while (left < right) {
            if (Math.abs(a[left] + a[right]) < temp) {
                temp = Math.abs(a[left] + a[right]);
                min = a[left];
                max = a[right];
            }

            if (a[left] + a[right] > 0) {
                right--;
            } else {
                left++;
            }
        }

        bw.write(String.valueOf(min));
        bw.write(" ");
        bw.write(String.valueOf(max));
        bw.flush();
    }
}