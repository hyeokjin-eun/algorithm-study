package com.company.java.binarysearch;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2805
public class Backjun2805 {
    private static final String[] array = {
            "4 7\n" +
            "20 15 10 17",
            "5 20\n" +
            "4 42 40 26 46"
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
        long M = Long.parseLong(st.nextToken());
        long max = 0;
        long[] a = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Long.parseLong(st.nextToken());
            max = Math.max(max, a[i]);
        }

        long left = 0;
        long right = max;
        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = calc(mid, a);
            if (M <= sum) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static long calc(long n, long[] a) {
        long answer = 0;
        for (int i = 0; i < a.length; i++) {
            if (0 <= a[i] - n) {
                answer += a[i] - n;
            }
        }

        return answer;
    }
}