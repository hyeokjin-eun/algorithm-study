package com.company.divideandconquer;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1074
public class Backjun1074 {
    private static final String[] array = {
            "2 3 1",
            "3 7 7"
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
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        bw.write(String.valueOf(solve(N, r, c)));
        bw.flush();
    }

    private static int solve(int n, int x, int y) {
        if (n == 1) {
            return 2 * x + y;
        } else {
            if (x < power(n - 1)) {
                if (y < power(n - 1)) {
                    return solve(n - 1, x, y);
                } else {
                    return solve(n - 1, x, y - power(n - 1)) + power(2 * n - 2);
                }
            } else {
                if (y < power(n - 1)) {
                    return solve(n - 1, x - power(n - 1), y) + power(2 * n - 2) * 2;
                } else {
                    return solve(n - 1, x - power(n - 1), y - power(n - 1)) + power(2 * n - 2) * 3;
                }
            }
        }
    }

    private static int power(int k) {
        return (1 << k);
    }
}