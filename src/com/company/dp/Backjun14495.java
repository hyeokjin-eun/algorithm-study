package com.company.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14495
public class Backjun14495 {
    private static final String[] array = {
            "10",
            "1",
            "2",
            "0",
            "116"
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
        long[] a = new long[117];
        a[1] = 1;
        a[2] = 1;
        a[3] = 1;
        for (int i = 4; i <= n; i++) {
            a[i] = a[i - 1] + a[i - 3];
        }

        bw.write(String.valueOf(a[n]));
        bw.flush();
    }
}
