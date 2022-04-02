package com.company.lecture.algorithm1.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1212
public class Backjun1212 {
    private static final String[] first = {
            "", "1", "10", "11", "100", "101", "110", "111"
    };
    private static final String[] temp = {
            "000", "001", "010", "011", "100", "101", "110", "111"
    };
    private static final String[] array = {
            "314"
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
        char[] num = br.readLine().toCharArray();
        bw.write(first[num[0] - 48]);
        for (int i = 1; i < num.length; i++) {
            bw.write(temp[num[i] - 48]);
        }

        bw.flush();
    }
}
