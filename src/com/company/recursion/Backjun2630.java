package com.company.recursion;

import com.company.string.Backjun5525_2;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/2630
public class Backjun2630 {
    private static final String[] array = {
            "8\n" +
            "1 1 0 0 0 0 1 1\n" +
            "1 1 0 0 0 0 1 1\n" +
            "0 0 0 0 1 1 0 0\n" +
            "0 0 0 0 1 1 0 0\n" +
            "1 0 0 0 1 1 1 1\n" +
            "0 1 0 0 1 1 1 1\n" +
            "0 0 1 1 1 1 1 1\n" +
            "0 0 1 1 1 1 1 1"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int board[][];
    private static int answer[];
    private static boolean visit[][];

    public static void main(String[] args) throws IOException {
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
        setDate();
        setAnswer();
        printAnswer();
    }

    private static void setDate() throws IOException {
        N = stoi(ioBuffered.readLine());
        board = new int[N][N];
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
            for (int j = 0; j < N; j++) {
                int number = stoi(stringTokenizer.nextToken());
                board[i][j] = number;
                visit[i][j] = true;
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static int white;
    private static int blue;
    private static void setAnswer() {
        white = 0;
        blue = 0;
        recursion(0, 0, N);
        answer = new int[]{white, blue};
    }

    private static void recursion(int x, int y, int size) {
        if (check(x, y, size)) {
            if (board[x][y] == 0) {
                white++;
            } else {
                blue++;
            }

            return;
        }

        int half = size / 2;
        recursion(x, y, half);
        recursion(x + half, y, half);
        recursion(x, y + half, half);
        recursion(x + half, y + half, half);
    }

    private static boolean check(int x, int y, int size) {
        int color = board[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (color != board[i][j]) {
                    return false;
                }
            }
        }

        return true;
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

        public void print(int[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int[] answer) throws IOException {
            for (int i = 0; i < 2; i++) {
                bufferedWriter.write(String.valueOf(answer[i]));
                if (i == 0) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
