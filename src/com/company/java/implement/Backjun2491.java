package com.company.java.implement;

import java.io.*;
import java.util.*;

public class Backjun2491 {
    private static IOBuffered ioBuffered;
    private static int N;
    private static int[] nums;
    private static int answer;
    private static final String[] array = {
            "9\n" +
            "1 2 2 4 4 5 7 7 2",
            "9\n" +
            "4 1 3 3 2 2 9 2 3",
            "11\n" +
            "1 5 3 6 4 7 1 3 2 9 5"
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

    private static void solution(String input) throws IOException {
        ioBuffered = IOBuffered.create(input);
        setInputData();
        setAnswer();
        printOutData();
    }

    private static void setInputData() throws IOException {
        N = stoi(ioBuffered.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = stoi(st.nextToken());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = Math.max(increase(), decrease());
    }

    private static int increase() {
        int count = 1;
        int max = 1;
        for (int i = 0; i < N - 1; i++) {
            count = isIncrease(i) ? count + 1 : 1;
            max = Math.max(count, max);
        }

        return max;
    }

    private static int decrease() {
        int count = 1;
        int max = 1;
        for (int i = 0; i < N - 1; i++) {
            count = isDecrease(i) ? count + 1 : 1;
            max = Math.max(count, max);
        }

        return max;
    }

    private static boolean isIncrease(int i) {
        return nums[i] <= nums[i + 1];
    }

    private static boolean isDecrease(int i) {
        return nums[i] >= nums[i + 1];
    }

    private static void printOutData() throws IOException {
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
