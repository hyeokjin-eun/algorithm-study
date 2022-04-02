package com.company.java.lecture.algorithm1.stack;

import java.io.*;
import java.util.Stack;
import java.lang.StringBuilder;

// link
// https://www.acmicpc.net/problem/1874
public class Backjun1874 {
    private static final String[] array = {
            "8\n" +
            "4\n" +
            "3\n" +
            "6\n" +
            "8\n" +
            "7\n" +
            "5\n" +
            "2\n" +
            "1",
            "5\n" +
            "1\n" +
            "2\n" +
            "5\n" +
            "3\n" +
            "4"
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
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int commandIndex = Integer.parseInt(br.readLine());
        int stackCnt = 0;
        for (int i = 0; i < commandIndex; i++) {
            int inputCnt = Integer.parseInt(br.readLine());
            if (stackCnt < inputCnt) {
                while (stackCnt < inputCnt) {
                    stack.push(++stackCnt);
                    sb.append("+\n");
                }
            }

            if (stack.peek() != inputCnt) {
                sb = new StringBuilder("NO\n");
                break;
            }

            stack.pop();
            sb.append("-\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
