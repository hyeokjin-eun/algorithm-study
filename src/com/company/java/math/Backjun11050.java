package com.company.java.math;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11050
public class Backjun11050 {
    private static final String[] array = {
            "5 2",
            "10 10"
    };

    public static void main (String[] args) throws IOException {
        // TEST
        test();
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

    private static void test() {
        int[] answer = new int[]{
                1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800
        };

        for (int i = 1; i <= 10; i++) {
            assert factorial(i) == answer[i - 1];
        }
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int K = stoi(st.nextToken());
        int answer = factorial(N) / (factorial(K) * factorial(N - K));
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static int factorial(int n) {
        int temp = 1;
        for (int i = 1; i <= n; i++) {
            temp *= i;
        }

        return temp;
    }
}