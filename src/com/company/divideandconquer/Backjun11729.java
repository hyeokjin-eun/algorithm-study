package com.company.divideandconquer;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11729
public class Backjun11729 {
    private static final String[] array = {
            "3"
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
        StringBuilder sb = new StringBuilder();
        recursion(N, 1, 3, sb);
        bw.write(String.valueOf((1<<N) - 1));
        bw.write("\n");
        bw.write(sb.toString());
        bw.flush();
    }

    private static void recursion(int n, int x, int y, StringBuilder sb) {
        if (n == 0) {
            return;
        }

        recursion(n - 1, x, 6 - x - y, sb);
        sb.append(x).append(" ").append(y).append("\n");
        recursion(n - 1, 6 - x - y, y, sb);
    }
}