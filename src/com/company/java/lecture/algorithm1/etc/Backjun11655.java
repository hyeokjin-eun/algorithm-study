package com.company.java.lecture.algorithm1.etc;

import java.io.*;

// link
// https://www.acmicpc.net/problem/11655
public class Backjun11655 {
    private static final String[] array = {
            "Baekjoon Online Judge",
            "One is 1"
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
        char[] sentences = br.readLine().toCharArray();
        for (char charInput : sentences) {
            if (charInput >= 'a' && charInput <= 'z') {
                if (charInput + 13 > 'z') {
                    bw.write('a' + ((charInput + 13) % 'z' -1));
                } else {
                    bw.write(charInput + 13);
                }
            } else if (charInput >= 'A' && charInput <= 'Z') {
                if (charInput + 13 > 'Z') {
                    bw.write('A' + ((charInput + 13) % 'Z' -1));
                } else {
                    bw.write(charInput + 13);
                }
            } else {
                bw.write(charInput);
            }
        }

         bw.flush();
    }
}
