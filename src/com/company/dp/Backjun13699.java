package com.company.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/13699
public class Backjun13699 {
    private static IOBuffered ioBuffered;
    private static int N;
    private static long[] dp;
    private static final String[] array = {
            "3",
            "4",
            "25",
            "35"
    };

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
        setInputData();
        setDp();
        printOutputData();
    }

    private static void setInputData() throws IOException {
        N = stoi(ioBuffered.readLine());
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setDp() {
        dp = new long[36];
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            long temp = 0;
            for (int j = 0; j < i; j++) {
                temp += dp[j] * dp[i - j - 1];
            }

            dp[i] = temp;
        }
    }

    private static void printOutputData() throws IOException {
        ioBuffered.print(dp[N]);
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

        public void print(long num) throws IOException {
            write(num);
            flush();
        }

        private void write(long num) throws IOException {
            bufferedWriter.write(String.valueOf(num));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}