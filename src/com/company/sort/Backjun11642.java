package com.company.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11652
public class Backjun11642 {
    private static final String[] array = {
            "5\n" +
            "1\n" +
            "2\n" +
            "1\n" +
            "2\n" +
            "1"
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
        for (int i = 0; i < N; i++) {
            a[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(a);
        long ans = a[0];
        int ansc = 1;
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (a[i] == a[i - 1]) {
                cnt++;
            } else {
                cnt = 1;
            }

            if (ansc < cnt) {
                ansc = cnt;
                ans = a[i];
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
    }
}