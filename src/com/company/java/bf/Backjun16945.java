package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16945
public class Backjun16945 {
    private static final int n = 3;
    private static final int[][] magic = {
            {8, 1, 6, 3, 5, 7, 4, 9, 2},
            {6, 1, 8, 7, 5, 3, 2, 9, 4},
            {4, 3, 8, 9, 5, 1, 2, 7, 6},
            {2, 7, 6, 9, 5, 1, 4, 3, 8},
            {2, 9, 4, 7, 5, 3, 6, 1, 8},
            {4, 9, 2, 3, 5, 7, 8, 1, 6},
            {6, 7, 2, 1, 5, 9, 8, 3, 4},
            {8, 3, 4, 1, 5, 9, 6, 7, 2}
    };

    private static final String[] array = {
            "4 9 2\n" +
            "3 5 7\n" +
            "8 1 5",
            "4 8 2\n" +
            "4 5 7\n" +
            "6 1 6"
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
        int[] a = new int[n * n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[index++] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int[] b : magic) {
            answer = Math.min(solve(a, b), answer);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int solve(int[] a, int b[]) {
        int sum = 0;
        for (int i = 0; i < n * n; i++) {
            sum += Math.abs(a[i] - b[i]);
        }

        return sum;
    }
}