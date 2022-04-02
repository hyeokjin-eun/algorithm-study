package com.company.java.dp;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1010
public class Backjun1010 {
    private static IOBuffered ioBuffered;
    private static int T;
    private static int N;
    private static int M;
    private static int[] answer;
    private static final int[][] dp = new int[30][30];
    private static final String[] array = {
            "3\n" +
            "2 2\n" +
            "1 5\n" +
            "13 29"
    };

    public static void main(String[] args) throws IOException {
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
        T = stoi(ioBuffered.readLine());
        answer = new int[T];
        setDp();
        for (int t = 0; t < T; t++) {
            setInputData();
            answer[t] = setAnswer();
        }

        printOutData();
    }

    /**
     * Use Combination Formula
     * https://blog.naver.com/mykepzzang/220823769604
     */
    private static void setDp() {
        for (int i = 0; i < 30; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }

        for (int i = 2; i < 30; i++) {
            for (int j = 1; j < 30; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setInputData() throws IOException {
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
    }

    private static int setAnswer() {
        return dp[M][N];
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