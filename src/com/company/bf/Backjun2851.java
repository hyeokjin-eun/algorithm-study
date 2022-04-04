package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2851
public class Backjun2851 {
    private static final String[] array = {
            "10\n" +
            "20\n" +
            "30\n" +
            "40\n" +
            "50\n" +
            "60\n" +
            "70\n" +
            "80\n" +
            "90\n" +
            "100",
            "1\n" +
            "2\n" +
            "3\n" +
            "5\n" +
            "8\n" +
            "13\n" +
            "21\n" +
            "34\n" +
            "55\n" +
            "89",
            "40\n" +
            "40\n" +
            "40\n" +
            "40\n" +
            "40\n" +
            "40\n" +
            "40\n" +
            "40\n" +
            "40\n" +
            "40"
    };
    private static IOBuffered ioBuffered;
    private static int[] mushroom;
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
        mushroom = new int[10];
        for (int i = 0; i < 10; i++) {
            mushroom[i] = stoi(ioBuffered.readLine());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        int min = Integer.MAX_VALUE;
        answer = 0;
        for (int i = 0; i < 10; i++) {
            int sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += mushroom[j];
            }

            int abs = Math.abs(100 - sum);
            if (abs <= min) {
                min = abs;
                answer = sum;
            }
        }
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

        public static IOBuffered create(String input) throws IOException {
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
