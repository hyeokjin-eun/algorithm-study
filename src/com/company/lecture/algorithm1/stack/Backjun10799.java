package com.company.lecture.algorithm1.stack;

import java.io.*;
import java.util.Stack;

// link
// https://www.acmicpc.net/problem/10799
public class Backjun10799 {
    private static final String[] array = {
            "()(((()())(())()))(())",
            "(((()(()()))(())()))(()())"
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

    public static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        char[] charArray = br.readLine().toCharArray();
        Stack<Integer> stack = new Stack<>();
        int count = 1;
        int result = 0;
        for (char charInput : charArray) {
            if (charInput ==  '(') {
                stack.push(count++);
            } else {
                if (count - stack.peek() == 1) {
                    stack.pop();
                    result += stack.size();
                } else {
                    stack.pop();
                    result += 1;
                }
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
    }
}
