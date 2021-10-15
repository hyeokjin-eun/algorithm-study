package com.company.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11047
public class Backjun11047 {
    private static final String[] array = {
            "10 4200\n" +
            "1\n" +
            "5\n" +
            "10\n" +
            "50\n" +
            "100\n" +
            "500\n" +
            "1000\n" +
            "5000\n" +
            "10000\n" +
            "50000",
            "10 4790\n" +
            "1\n" +
            "5\n" +
            "10\n" +
            "50\n" +
            "100\n" +
            "500\n" +
            "1000\n" +
            "5000\n" +
            "10000\n" +
            "50000"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coin = new int[N];
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        int sum = 0;
        for (int i = N - 1; 0 <= i; i--) {
            sum += K / coin[i];
            K = K % coin[i];
        }

        bw.write(String.valueOf(sum));
        bw.flush();
    }
}