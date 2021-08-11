package com.company.lecture.algorithm1.stack;

import java.io.*;
import java.util.Stack;

// link
// https://www.acmicpc.net/problem/10828
public class Backjun10828 {
    private static final String[] array  = {
            "14\n" +
            "push 1\n" +
            "push 2\n" +
            "top\n" +
            "size\n" +
            "empty\n" +
            "pop\n" +
            "pop\n" +
            "pop\n" +
            "size\n" +
            "empty\n" +
            "pop\n" +
            "push 3\n" +
            "empty\n" +
            "top",
            "7\n" +
            "pop\n" +
            "top\n" +
            "push 123\n" +
            "top\n" +
            "pop\n" +
            "top\n" +
            "pop"
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
        InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        Stack<Integer> stack = new Stack<>();
        int length = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < length; i++) {
            String[] array = bufferedReader.readLine().split(" ");
            if ("push".equals(array[0])) {
                stack.push(Integer.parseInt(array[1]));
            } else if ("pop".equals(array[0])) {
                if (stack.empty()) {
                    bufferedWriter.write("-1\n");
                } else {
                    bufferedWriter.write(stack.pop() + "\n");
                }
            } else if ("size".equals(array[0])) {
                bufferedWriter.write(stack.size() + "\n");
            } else if ("empty".equals(array[0])) {
                bufferedWriter.write((stack.empty() ? 1 : 0) + "\n");
            } else if ("top".equals(array[0])) {
                if (stack.empty()) {
                    bufferedWriter.write("-1\n");
                } else {
                    bufferedWriter.write(stack.peek() + "\n");
                }
            }
        }

        bufferedWriter.flush();
    }
}
