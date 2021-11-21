package com.company.math;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11720
public class Backjun11720 {
    private static final String[] array = {
            "5\n" +
            "54321"
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
        char[] c = br.readLine().toCharArray();
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += c[i] - '0';
        }

        bw.write(String.valueOf(sum));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}