package com.company.lecture.algorithm1.stack;

import java.io.*;
import java.util.Stack;

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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        char[] command = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char charInput : command) {
            if ('A' <= charInput && 'Z' >= charInput) {
                bw.write(charInput);
            } else {
                if (charInput == '(') {
                    stack.push(charInput);
                } else if (charInput == ')') {
                    while (!stack.empty()) {
                        if (stack.peek() == '(') {
                            stack.pop();
                            break;
                        }

                        bw.write(stack.pop());
                    }
                } else {
                    while (!stack.empty() && priority(stack.peek()) >= priority(charInput)) {
                        bw.write(stack.pop());
                    }

                    stack.push(charInput);
                }
            }
        }

        while (!stack.empty()) {
            bw.write(stack.pop());
        }

        bw.flush();
    }

    private static int priority (char input) {
        if (input == '(') {
            return 0;
        } else if (input == '+' || input == '-') {
            return 1;
        } else {
            return 2;
        }
    }
}
