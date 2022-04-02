package com.company.java.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/9507
public class Backjun9507 {
    private static final String[] array = {
            "8\n" +
            "0\n" +
            "1\n" +
            "2\n" +
            "3\n" +
            "4\n" +
            "5\n" +
            "30\n" +
            "67"
    };
    private static IOBuffered ioBuffered;
    private static long[] fibonacci;
    private static int T;
    private static int[] nums;
    private static long[] answer;

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

    private static void solution(String input) throws IOException {
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printData();
    }

    private static void setData() throws IOException {
        T = stoi(ioBuffered.readLine());
        nums = new int[T];
        for (int t = 0; t < T; t++) {
            nums[t] = stoi(ioBuffered.readLine());
        }

        setFibonacci();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setFibonacci() {
        fibonacci = new long[68];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        fibonacci[2] = 2;
        fibonacci[3] = 4;
        for (int i = 4; i < fibonacci.length; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2] + fibonacci[i - 3] + fibonacci[i - 4];
        }
    }

    private static void setAnswer() {
        answer = new long[T];
        for (int t = 0; t < T; t++) {
            answer[t] = fibonacci[nums[t]];
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

        public static IOBuffered create(String input) {
            return new IOBuffered(input);
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print(long[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(long[] answer) throws IOException {
            for (int i = 0; i < answer.length; i++) {
                bufferedWriter.write(String.valueOf(answer[i]));
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