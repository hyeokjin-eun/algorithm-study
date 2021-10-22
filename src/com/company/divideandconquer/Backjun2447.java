package com.company.divideandconquer;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2447
public class Backjun2447 {
    private static final String[] array = {
            "27"
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
        int[][] a = new int[N][N];
        recursion(a, N, 0, 0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (a[i][j] == 1) {
                    bw.write("*");
                } else {
                    bw.write(" ");
                }
            }

            if (i != N - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static void recursion(int[][] a, int n, int x, int y) {
        if (n == 1) {
            a[y][x] = 1;
            return;
        }

        int temp = n / 3;
        recursion(a, temp, x, y);
        recursion(a, temp, x + temp, y);
        recursion(a, temp, x + temp * 2, y);
        recursion(a, temp, x, y + temp);
        recursion(a, temp, x + temp * 2, y + temp);
        recursion(a, temp, x, y + temp * 2);
        recursion(a, temp, x + temp, y + temp * 2);
        recursion(a, temp, x + temp * 2, y + temp * 2);
    }
}
