package com.company.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2108
public class Backjun2108 {
    private static final String[] array = {
            "5\n" +
            "1\n" +
            "3\n" +
            "8\n" +
            "-2\n" +
            "2",
            "1\n" +
            "4000",
            "5\n" +
            "-1\n" +
            "-2\n" +
            "-3\n" +
            "-1\n" +
            "-2"
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
        int N = stoi(br.readLine());
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = stoi(br.readLine());
        }

        Arrays.sort(a);
        bw.write(String.valueOf(mean(a)));
        bw.write("\n");
        bw.write(String.valueOf(center(a)));
        bw.write("\n");
        bw.write(String.valueOf(many(a)));
        bw.write("\n");
        bw.write(String.valueOf(lange(a)));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static int mean(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }

        return (int) Math.round((double) sum / a.length);
    }

    private static int center(int[] a) {
        return a[a.length / 2];
    }

    private static int many(int[] a) {
        int[] count = new int[8001];
        for (int n : a) {
            count[n + 4000]++;
        }

        int max = 0;
        for (int i = 0; i < count.length; i++) {
            max = Math.max(max, count[i]);
        }

        int answer = 0;
        boolean check = false;
        for (int i = 0; i < count.length; i++) {
             if (max == count[i]) {
                 if (check) {
                     return i - 4000;
                 }

                 answer = i - 4000;
                 check = true;
             }
        }

        return answer;
    }

    private static int lange(int[] a) {
        return a[a.length - 1] - a[0];
    }
}