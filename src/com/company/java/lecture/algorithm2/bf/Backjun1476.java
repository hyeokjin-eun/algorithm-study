package com.company.java.lecture.algorithm2.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1476
public class Backjun1476 {
    private static final String[] array = {
            "1 16 16",
            "1 1 1",
            "1 2 3",
            "15 28 19"
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
        String[] years = br.readLine().split(" ");
        int[] a = new int[3];
        for (int i = 0; i < 3; i++) {
            a[i] = Integer.parseInt(years[i]);
        }

        int e = 1;
        int s = 1;
        int m = 1;
        int year = 1;
        while (true) {
            if (a[0] == e && a[1] == s && a[2] == m) {
                break;
            }

            e++;
            if (e == 16) {
                e = 1;
            }

            s++;
            if (s == 29) {
                s = 1;
            }

            m++;
            if (m == 20) {
                m = 1;
            }

            year++;
        }

        bw.write(String.valueOf(year));
        bw.flush();
    }
}
