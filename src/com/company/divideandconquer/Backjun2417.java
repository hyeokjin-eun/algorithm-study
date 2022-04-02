package com.company.divideandconquer;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2417
public class Backjun2417 {
    private static final String[] array = {
            "9223372036854775807",
            "122333444455555",
            "16",
            "12",
            "1",
            "9223372030926249000"

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
        long N = Long.parseLong(br.readLine());
        long answer;
        if (N <= 2) {
            answer = N;
        } else {
            answer = binarySearch(N);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static long binarySearch(long N) {
        long start = 1;
        long end = N / 2;
        long mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            long temp = (long) Math.pow(mid, 2);
            if (N <= temp || temp <= 0) {
                // Long.MAX_VALUE 초과시 -수 값이면 너무 크므로 그냥 -1
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        if (mid * mid < N) {
            mid++;
        }

        return mid;
    }
}