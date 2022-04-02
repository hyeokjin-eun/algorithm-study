package com.company.graph;

import java.io.*;
import java.util.*;

// link
//
public class Backjun1520 {
    private static final String[] array = {
            "4 5\n" +
            "50 45 37 32 30\n" +
            "35 50 40 20 25\n" +
            "30 30 25 17 28\n" +
            "27 24 22 15 10"
    };
    private static IOBuffered ioBuffered;
    private static int M;
    private static int N;
    private static int[][] board;
    private static int answer;
    private static int[][] dist;

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
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        M = stoi(st.nextToken());
        N = stoi(st.nextToken());
        board = new int[M][N];
        dist = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(ioBuffered.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = stoi(st.nextToken());
                dist[i][j] = -1;
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = dfs(0, 0);
    }

    private static final int[] xa = new int[]{0, 1, 0, -1};
    private static final int[] ya = new int[]{-1, 0, 1, 0};
    private static int dfs(int y, int x) {
        if (isMemory(y, x)) {
            return dist[y][x];
        }

        if (isLastPoint(y, x)) {
            return 1;
        }

        dist[y][x] = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y + ya[i];
            int nx = x + xa[i];
            if (isNotOutOfIndex(ny, nx) && isRoute(y, x, ny, nx)) {
                dist[y][x] += dfs(ny, nx);
            }
        }

        return dist[y][x];
    }

    private static boolean isMemory(int y, int x) {
        return dist[y][x] != -1;
    }

    private static boolean isRoute(int y, int x, int ny, int nx) {
        return board[ny][nx] < board[y][x];
    }

    private static boolean isNotOutOfIndex(int y, int x) {
        return 0 <= y && y < M && 0 <= x && x < N;
    }

    private static boolean isLastPoint(int y, int x) {
        return y == M - 1 && x == N - 1;
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
