package com.company.java.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/9657
public class Backjun9657 {
    private static final String[] array = {
            "6",
            "3",
            "5",
            "7"
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
        int N = stoi(br.readLine());
        boolean[] check = new boolean[1001];
        check[1] = true;
        check[3] = true;
        check[4] = true;
        for (int i = 4; i <= N; i++) {
            if (!check[i - 1]) {
                check[i] = true;
            }

            if (!check[i - 3]) {
                check[i] = true;
            }

            if (!check[i - 4]) {
                check[i] = true;
            }
        }

        bw.write(check[N] ? "SK" : "CY");
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}