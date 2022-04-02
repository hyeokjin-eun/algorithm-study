package com.company.java.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/12865
public class Backjun12865 {
    private static IOBuffered ioBuffered;
    private static int N;
    private static int K;
    private static Object[] objects;
    private static int answer;
    private static final String[] array = {
            "4 7\n" +
            "6 13\n" +
            "4 8\n" +
            "3 6\n" +
            "5 12"
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
        setMaxValue();
        printOutputData();
    }

    private static void setInputData() throws IOException {
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        setObjects();
    }

    private static void setObjects() throws IOException {
        objects = new Object[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            objects[i] = new Object(stoi(st.nextToken()), stoi(st.nextToken()));
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setMaxValue() {
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (objects[i - 1].weight > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - objects[i - 1].weight] + objects[i - 1].value);
                }
            }
        }

        answer = dp[N][K];
    }

    private static void printOutputData() throws IOException {
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

        public void print(int num) throws IOException {
            write(num);
            flush();
        }

        private void write(int num) throws IOException {
            bufferedWriter.write(String.valueOf(num));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }

    private static class Object {
        private final int weight;
        private final int value;

        public Object(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}