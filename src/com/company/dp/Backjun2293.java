package com.company.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2293
public class Backjun2293 {
    private static int N;
    private static int K;
    private static int[] coin;
    private static final String[] array = {
            "3 10\n" +
            "1\n" +
            "2\n" +
            "5"
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
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        coin = new int[101];
        for (int i = 1; i <= N; i++) {
            coin[i] = stoi(br.readLine());
        }

        bw.write(String.valueOf(dp()));
        bw.flush();
    }

    private static long dp() {
        long[] temp = new long[10001];
        temp[0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (j - coin[i] >= 0) {
                    temp[j] += temp[j - coin[i]];
                }
            }
        }

        return temp[K];
    }


    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}