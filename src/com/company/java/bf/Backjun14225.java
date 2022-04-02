package com.company.java.bf;

import java.io.*;
import java.util.*;

public class Backjun14225 {
    private static final String[] array = {
            "3\n" +
            "5 1 2"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            max += a[i];
        }

        boolean[] check = new boolean[max + 2];
        recursion(check, 0, 0, a);
        int answer = 0;
        for (int i = 1; i < max + 2; i++) {
            if (!check[i]) {
                answer = i;
                break;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static void recursion(boolean[] check, int total, int i, int[] a) {
        if (i == a.length) {
            check[total] = true;
            return;
        }

        recursion(check, total + a[i], i + 1, a);
        recursion(check, total, i + 1, a);
    }
}