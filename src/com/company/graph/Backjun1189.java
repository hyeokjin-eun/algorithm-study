package com.company.graph;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1189
public class Backjun1189 {
    private static final String[] array = {
            "3 4 6\n" +
            "....\n" +
            ".T..\n" +
            "....",
            "1 1 1\n" +
            "."
    };

    private static IOBuffered ioBuffered;
    private static int R;
    private static int C;
    private static int K;
    private static char[][] board;
    private static boolean[][] visit;
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
        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        R = stoi(stringTokenizer.nextToken());
        C = stoi(stringTokenizer.nextToken());
        K = stoi(stringTokenizer.nextToken());
        board = new char[R][C];
        visit = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String row = ioBuffered.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = row.charAt(j);
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        visit[R - 1][0] = true;
        answer = resolve(0, R - 1, 0);
    }

    private static final int[] xa = new int[]{0, 1, 0, -1};

    private static final int[] ya = new int[]{-1, 0, 1, 0};

    private static int resolve(int x, int y, int c) {
        if (c == K - 1) {
            return x == C - 1 && y == 0 ? 1 : 0;
        }

        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + xa[i];
            int ny = y + ya[i];
            if (is(nx, ny)) {
                continue;
            }

            visit[ny][nx] = true;
            count += resolve(nx, ny, c + 1);
            visit[ny][nx] = false;
        }

        return count;
    }

    private static boolean is(int x, int y) {
        return outOfIndex(x, y) || isWall(x, y) || isVisit(x, y);
    }

    private static boolean outOfIndex(int x, int y) {
        return x < 0 || y < 0 || C <= x || R <= y;
    }

    private static boolean isWall(int x, int y) {
        return board[y][x] == 'T';
    }

    private static boolean isVisit(int x, int y) {
        return visit[y][x];
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
