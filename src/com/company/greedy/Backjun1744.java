package com.company.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1744
public class Backjun1744 {
    private static final String[] array = {
            "4\n" + // 6
            "-1\n" +
            "2\n" +
            "1\n" +
            "3",
            "5\n" + //11
            "-4\n" +
            "-3\n" +
            "-2\n" +
            "0\n" +
            "1\n",
            "5\n" +
            "-2\n" +
            "-1\n" +
            "0\n" +
            "3\n" +
            "4",
            "5\n" +
            "-2\n" +
            "-2\n" +
            "3\n" +
            "4\n" +
            "5",
            "4\n" +
            "-4\n" +
            "-3\n" +
            "-2\n" +
            "-1",
            "4\n" +
            "2\n" +
            "3\n" +
            "4\n" +
            "5",
            "6\n" +
            "-5\n" +
            "-2\n" +
            "-1\n" +
            "0\n" +
            "3\n" +
            "6"
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
        int[] a = new int[N];
        int positive = N;
        int negative = 0;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(br.readLine());
            if (a[i] < 1) {
                negative++;
            } else if (1 < a[i]) {
                positive--;
            } else {
                answer += a[i];
            }
        }

        Arrays.sort(a);
        int temp = 0;
        for (int i = 0; i < negative; i++) {
            if (temp == 0) {
                temp = a[i];
            } else {
                answer += temp * a[i];
                temp = 0;
            }
        }

        answer += temp;
        temp = 0;
        for (int i = N - 1; positive <= i; i--) {
            if (temp == 0) {
                temp = a[i];
            } else {
                answer += temp * a[i];
                temp = 0;
            }
        }

        answer += temp;
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}