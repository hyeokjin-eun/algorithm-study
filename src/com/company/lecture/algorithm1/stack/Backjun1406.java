package com.company.lecture.algorithm1.stack;

import java.io.*;
import java.util.Stack;

// link
// https://www.acmicpc.net/problem/1406
public class Backjun1406 {
    private static final String[] array = {
            "abcd\n" +
            "3\n" +
            "P x\n" +
            "L\n" +
            "P y",
            "abc\n" +
            "9\n" +
            "L\n" +
            "L\n" +
            "L\n" +
            "L\n" +
            "L\n" +
            "P x\n" +
            "L\n" +
            "B\n" +
            "P y",
            "dmih\n" +
            "11\n" +
            "B\n" +
            "B\n" +
            "P x\n" +
            "L\n" +
            "B\n" +
            "B\n" +
            "B\n" +
            "P y\n" +
            "D\n" +
            "D\n" +
            "P z"
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
        Stack<String> leftStack = new Stack<>();
        Stack<String> rightStack = new Stack<>();
        for (char inputChar : br.readLine().toCharArray()) {
            leftStack.push(String.valueOf(inputChar));
        }

        int commandIndex = Integer.parseInt(br.readLine());
        for (int i = 0; i < commandIndex; i++) {
            String[] commandArray = br.readLine().split(" ");
            if ("L".equals(commandArray[0])) {
                if (!leftStack.empty()) {
                    rightStack.push(leftStack.pop());
                }
            } else if ("D".equals(commandArray[0])) {
                if (!rightStack.empty()) {
                    leftStack.push(rightStack.pop());
                }
            } else if ("B".equals(commandArray[0])) {
                if (!leftStack.empty()) {
                    leftStack.pop();
                }
            } else {
                leftStack.push(commandArray[1]);
            }
        }

        while (!leftStack.empty()) {
            rightStack.push(leftStack.pop());
        }

        while (!rightStack.empty()) {
            bw.write(rightStack.pop());
        }

        bw.flush();
    }
}
