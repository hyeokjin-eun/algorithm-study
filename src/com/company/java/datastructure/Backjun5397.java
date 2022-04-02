package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/5397
public class Backjun5397 {
    private static final String[] array = {
            "2\n" +
            "<<BP<A>>Cd-\n" +
            "ThIsIsS3Cr3t"
    };
    private static IOBuffered ioBuffered;
    private static int T;
    private static String[] text;
    private static String[] answer;

    public static void main (String[] args) throws IOException {
        // TEST
        test();
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

    private static void test() {

    }

    private static void solution(String input) throws IOException {
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printData();
    }

    private static void setData() throws IOException {
        T = stoi(ioBuffered.readLine());
        text = new String[T];
        for (int t = 0; t < T; t++) {
            text[t] = ioBuffered.readLine();
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = new String[T];
        for (int t = 0; t < T; t++) {
            Stack<Character> left = new Stack<>();
            Stack<Character> right = new Stack<>();
            textAction(text[t], left, right);
            moveLeftToRight(left, right);
            answer[t] = convertToString(right);
        }
    }

    private static String convertToString(Stack<Character> right) {
        StringBuilder sb = new StringBuilder();
        while (!right.isEmpty()) {
            sb.append(right.pop());
        }
        return sb.toString();
    }

    private static void moveLeftToRight(Stack<Character> left, Stack<Character> right) {
        while (!left.isEmpty()) {
            right.push(left.pop());
        }
    }

    private static void textAction(String s, Stack<Character> left, Stack<Character> right) {
        for (char c : s.toCharArray()) {
            if (c == '<') {
                if (!left.isEmpty()) {
                    right.push(left.pop());
                }
            } else if (c == '>') {
                if (!right.isEmpty()) {
                    left.push(right.pop());
                }
            } else if (c == '-') {
                if (!left.isEmpty()) {
                    left.pop();
                }
            } else {
                left.push(c);
            }
        }
    }

    private static void printData() throws IOException {
        ioBuffered.print(answer);
    }

    private static class IOBuffered {
        private final BufferedReader bufferedReader;
        private final BufferedWriter bufferedWriter;

        private IOBuffered(String input) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
            InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }

        public static IOBuffered create(String input) throws IOException {
            return new IOBuffered(input);
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print(String[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(String[] answer) throws IOException {
            for (int i = 0; i < answer.length; i++) {
                bufferedWriter.write(answer[i]);
                if (i != answer.length - 1) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}