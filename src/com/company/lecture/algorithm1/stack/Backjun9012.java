package com.company.lecture.algorithm1.stack;

import java.io.*;

// link
// https://www.acmicpc.net/problem/9012
public class Backjun9012 {
    private static final String[] array = {
            "6\n" +
            "(())())\n" +
            "(((()())()\n" +
            "(()())((()))\n" +
            "((()()(()))(((())))()\n" +
            "()()()()(()()())()\n" +
            "(()((())()(",
            "3\n" +
            "((\n" +
            "))\n" +
            "())(()"
    };

    public static void main (String[] args) throws IOException {
        for (int i = 0; i < array.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            long before = System.currentTimeMillis();
            solution(array[i]);
            long after = System.currentTimeMillis();
            System.out.println("===== Time : " + (after - before) + "   =====");
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int index = Integer.parseInt(br.readLine());
        for (int i = 0; i < index; i++) {
            char[] charArray = br.readLine().toCharArray();
            int count = 0;
            boolean isCheck = true;
            for (char charInput : charArray) {
                if ('(' == charInput) {
                    count++;
                } else {
                    if (count < 1) {
                        isCheck = false;
                        break;
                    }

                    count--;
                }
            }

            if (count != 0) {
                isCheck = false;
            }

            bw.write(isCheck ? "YES" : "NO");
            bw.write("\n");
        }

        bw.flush();;
    }
}
