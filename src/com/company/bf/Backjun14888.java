package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14888
public class Backjun14888 {
    private static final String[] array = {
            "2\n" +
            "5 6\n" +
            "0 0 1 0",
            "3\n" +
            "3 4 5\n" +
            "1 0 1 0",
            "6\n" +
            "1 2 3 4 5 6\n" +
            "2 1 1 1",
            "5\n" +
            "100 100 100 100 10\n" +
            "0 0 4 0"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] o = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            o[i] = Integer.parseInt(st.nextToken());
        }

        long[] ans = new long[2];
        ans[0] = Long.MIN_VALUE;
        ans[1] = Long.MAX_VALUE;
        recursion(ans, 1, a, o, a[0], n);
        bw.write(String.valueOf(ans[0]));
        bw.write("\n");
        bw.write(String.valueOf(ans[1]));
        bw.flush();
    }

    private static void recursion(long[] ans, int index, int[] a, int[] o, long before, int n) {
        if (index == n) {
            if (ans[0] < before) {
                ans[0] = before;
            }

            if (before < ans[1]) {
                ans[1] = before;
            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            if (0 < o[i]) {
                long temp = before;
                if (i == 0) {
                    temp += a[index];
                } else if (i == 1) {
                    temp -= a[index];
                } else if (i == 2) {
                    temp *= a[index];
                } else {
                    temp /= a[index];
                }

                o[i]--;
                recursion(ans, index + 1, a, o, temp, n);
                o[i]++;
            }
        }
    }
}
