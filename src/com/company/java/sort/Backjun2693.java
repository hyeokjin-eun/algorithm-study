package com.company.java.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2693
public class Backjun2693 {
    private static final String[] array = {
            "4\n" +
            "1 2 3 4 5 6 7 8 9 1000\n" +
            "338 304 619 95 343 496 489 116 98 127\n" +
            "931 240 986 894 826 640 965 833 136 138\n" +
            "940 955 364 188 133 254 501 122 768 408"
    };
    private static IOBuffered ioBuffered;
    private static int T;
    private static int[][] nums;
    private static int[] answer;

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
        T = stoi(ioBuffered.readLine());
        nums = new int[T][10];
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            for (int j = 0; j < 10; j++) {
                nums[i][j] = stoi(st.nextToken());
            }

            Arrays.sort(nums[i]);
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = new int[T];
        for (int i = 0; i < T; i++) {
            answer[i] = nums[i][7];
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

        public static IOBuffered create(String input) {
            return new IOBuffered(input);
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print(int[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int[] answer) throws IOException {
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