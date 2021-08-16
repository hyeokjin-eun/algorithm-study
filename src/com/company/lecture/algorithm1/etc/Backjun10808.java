package com.company.lecture.algorithm1.etc;

import java.io.*;

// link
// https://www.acmicpc.net/problem/10808
public class Backjun10808 {
    private static final String[] array = {
            "baekjoon"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        char[] inputArray = br.readLine().toCharArray();
        int[] countArray = new int[26];
        for (char charInput : inputArray) {
            countArray[charInput - 97]++;
        }

        for (int count : countArray) {
            bw.write(String.valueOf(count));
            bw.write(" ");
        }

        bw.flush();
    }
}
