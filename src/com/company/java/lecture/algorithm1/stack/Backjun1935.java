package com.company.java.lecture.algorithm1.stack;

import java.io.*;
import java.util.Stack;

// link
// https://www.acmicpc.net/problem/1935
public class Backjun1935 {
    private static final String[] array = {
            "5\n" +
            "ABC*+DE/-\n" +
            "1\n" +
            "2\n" +
            "3\n" +
            "4\n" +
            "5",
            "1\n" +
            "AA+A+\n" +
            "1"
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
        int index = Integer.parseInt(br.readLine());
        char[] commandArray = br.readLine().toCharArray();
        double[] numberArray = new double[26];
        for (int i = 0; i < index; i++) {
            numberArray[i] = Double.parseDouble(br.readLine());
        }

        Stack<Double> stack = new Stack<>();
        for (char command : commandArray) {
            double numA;
            double numB;
            if (command == '+') {
                numB = stack.pop();
                numA = stack.pop();
                stack.push(numA + numB);
            } else if (command == '-') {
                numB = stack.pop();
                numA = stack.pop();
                stack.push(numA - numB);
            } else if (command == '*') {
                numB = stack.pop();
                numA = stack.pop();
                stack.push(numA * numB);
            } else if (command == '/') {
                numB = stack.pop();
                numA = stack.pop();
                stack.push(numA / numB);
            } else {
                stack.push(numberArray[command - 65]);
            }
        }

        bw.write(String.format("%.2f", stack.pop()));
        bw.flush();
    }
}
