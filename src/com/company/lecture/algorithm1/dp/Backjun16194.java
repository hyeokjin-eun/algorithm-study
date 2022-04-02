package com.company.lecture.algorithm1.dp;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/16194
public class Backjun16194 {
    private static final String[] array = {
            "4\n" +
            "1 5 6 7",
            "5\n" +
            "10 9 8 7 6",
            "10\n" +
            "1 1 2 3 5 8 13 21 34 55",
            "10\n" +
            "5 10 11 12 13 30 35 40 45 47"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int c = Integer.parseInt(br.readLine());
        int[] p = new int[c + 1];
        int[] a = new int[c + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= c; i++) {
            p[i] = Integer.parseInt(st.nextToken());
            a[i] = -1;
        }

        for (int i = 0; i <= c; i++) {
            for (int j = 0; j <= i; j++) {
                int t = a[i - j] + p[j];
                if (a[i] == -1 || a[i] > t) a[i] = t;
            }
        }

        bw.write(String.valueOf(a[c]));
        bw.flush();
    }
}
