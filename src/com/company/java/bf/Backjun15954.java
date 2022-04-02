package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/15954
public class Backjun15954 {
    private static final String[] array = {
            "5 3\n" +
            "1 2 3 4 5",
            "10 3\n" +
            "1 4 1 5 9 2 6 5 3 5"
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
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        double answer = Double.MAX_VALUE;
        for (int i = k; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                double temp = sd(j, i, a, n);
                if (temp < answer) {
                    answer = temp;
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static double sd(int s, int k, int[] a, int n) {
        double total = 0;
        for (int i = 0; i < k; i++) {
            total += a[i + s];
        }

        total /= k;
        double dis = 0;
        for (int i = 0; i < k; i++) {
            dis += Math.pow(a[i + s] - total, 2);
        }

        dis /= k;
        return Math.sqrt(dis);
    }
}
