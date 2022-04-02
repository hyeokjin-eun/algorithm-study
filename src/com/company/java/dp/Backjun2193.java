package com.company.java.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2193
public class Backjun2193 {
    private static long[] cnt;
    private static int N;
    private static final String[] array = {
            "3",
            "90"
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
        N = stoi(br.readLine());
        cnt = new long[N + 1];
        cnt[0] = 0;
        cnt[1] = 1;
        for (int i = 2; i <= N; i++) {
            cnt[i] = cnt[i - 1] + cnt[i - 2];
        }

        bw.write(String.valueOf(cnt[N]));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}