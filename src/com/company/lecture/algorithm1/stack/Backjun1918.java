package com.company.lecture.algorithm1.stack;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1918
public class Backjun1918 {
    private static final String[] array = {
            "A*(B+C)"
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

    // TODO: 2021/08/13 작업 필요
    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        char[] command = br.readLine().toCharArray();
        for (char charInput : command) {
            if ('A' <= charInput && 'Z' >= charInput) {
                bw.write(charInput);
            } else {
                if (charInput == '(') {

                } else if (charInput == ')') {

                } else {

                }
            }
        }
    }
}
