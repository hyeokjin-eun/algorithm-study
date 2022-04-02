package com.company.java.binarysearch;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1654
public class Backjun1654 {
    private static final String[] array = {
            "4 11\n" +
            "802\n" +
            "743\n" +
            "457\n" +
            "539"
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
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long[] a = new long[K];
        long max = 0;
        for (int i = 0; i < K; i++) {
            a[i] = Long.parseLong(br.readLine());
            max = Math.max(max, a[i]);
        }

        long left = 1;
        long right = max;
        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            int cnt = calc(mid, a);
            if (N <= cnt) {
                answer = Math.max(mid, answer);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int calc(long n, long[] a) {
        int answer = 0;
        for (int i = 0; i < a.length; i++) {
            answer += a[i] / n;
        }

        return answer;
    }
}