package com.company.implement;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/2563
public class Backjun2563 {

    private static final String[] array = {
            "3\n" +
            "3 7\n" +
            "15 7\n" +
            "5 2"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int[][] paper;
    private static final boolean[][] board = new boolean[101][101];
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
        N = stoi(ioBuffered.readLine());
        paper = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
            int x = stoi(stringTokenizer.nextToken());
            int y = stoi(stringTokenizer.nextToken());
            paper[i] = new int[]{x, y};
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        for (int i = 0; i < N; i++) {
            set(paper[i][0], paper[i][1]);
        }

        answer = get();
    }

    private static final int width = 10;
    private static final int height = 10;
    private static void set(int x, int y) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                board[i][j] = true;
            }
        }
    }

    private static int get() {
        int count = 0;
        for (int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                if (board[i][j]) {
                    count++;
                }
            }
        }

        return count;
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