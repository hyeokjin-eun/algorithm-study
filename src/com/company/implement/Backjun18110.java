package com.company.implement;

import java.io.*;
import java.util.Arrays;

// link
// https://www.acmicpc.net/problem/18110
public class Backjun18110 {

    private static final String[] array = {
            "5\n" +
            "1\n" +
            "5\n" +
            "5\n" +
            "7\n" +
            "8",
            "10\n" +
            "1\n" +
            "13\n" +
            "12\n" +
            "15\n" +
            "3\n" +
            "16\n" +
            "13\n" +
            "12\n" +
            "14\n" +
            "15"
    };

    private static IOBuffered ioBuffered;
    private static int n;
    private static int[] score;
    private static int answer;

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
        printAnswer();
    }

    private static void setData() throws IOException {
        n = stoi(ioBuffered.readLine());
        score = new int[n];
        for (int i = 0; i < n; i++) {
            score[i] = stoi(ioBuffered.readLine());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        Arrays.sort(score);
        int out = (int) Math.round(n * 0.15);
        double sum = 0;
        for (int i = out; i < n - out; i++) {
            sum += score[i];
        }

        answer = (int) Math.round(sum / (n - out * 2));
    }

    private static void printAnswer() throws IOException {
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

        public void print(int answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int answer) throws IOException {
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}

