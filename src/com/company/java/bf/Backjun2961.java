package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2961
public class Backjun2961 {
    private static final String[] array = {
            "1\n" +
            "3 10",
            "2\n" +
            "3 8\n" +
            "5 8",
            "4\n" +
            "1 7\n" +
            "2 6\n" +
            "3 8\n" +
            "4 9"
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
        int n = Integer.parseInt(br.readLine());
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            a[i][0] = s;
            a[i][1] = b;
        }

        long answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long temp = recursion(n, i + 1, a[i][0], a[i][1], a);
            if (temp < answer) {
                answer = temp;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static long recursion(int n, int i, long st, long bt, int[] [] a) {
        if (i == n) {
            return Math.abs(st - bt);
        } else {
            return Math.min(recursion(n, i + 1, st * a[i][0], bt + a[i][1], a), recursion(n, i + 1, st, bt, a));
        }
    }
}
