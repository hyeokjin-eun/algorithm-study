package com.company.java.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2292
public class Backjun2292 {
    private static final String[] array = {
            "13"
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
        int temp = 1;
        int i = 0;
        do {
            temp += 6 * i;
            i++;
        } while (temp < N);

        bw.write(String.valueOf(i));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}