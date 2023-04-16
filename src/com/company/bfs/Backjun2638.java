package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2638
public class Backjun2638 {
    private static final String[] array = {
            "8 9\n" +
            "0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 1 1 0 0 0 0\n" +
            "0 0 0 1 1 0 1 1 0\n" +
            "0 0 1 1 1 1 1 1 0\n" +
            "0 0 1 1 1 1 1 0 0\n" +
            "0 0 1 1 0 1 1 0 0\n" +
            "0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0",
            "TEST",
            "8 9\n" +
            "0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0\n" +
            "0 1 1 0 0 0 1 1 0\n" +
            "0 1 0 1 1 1 0 1 0\n" +
            "0 1 0 0 1 0 0 1 0\n" +
            "0 1 0 1 1 1 0 1 0\n" +
            "0 1 1 0 0 0 1 1 0\n" +
            "0 0 0 0 0 0 0 0 0"
    };
    private static IOBuffered ioBuffered;
    private static int N;
    private static int M;
    private static int[][] board;
    private static int answer;

    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};

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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("100 100");
        stringBuilder.append("\n");
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                stringBuilder.append("1");
                if (j != 99) {
                    stringBuilder.append(" ");
                }
            }

            if (i != 99) {
                stringBuilder.append("\n");
            }
        }

        array[1] = stringBuilder.toString();
    }

    private static void solution(String input) throws IOException {
        //TODO Algorithm Start
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setData() throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        N = stringToInteger(stringTokenizer.nextToken());
        M = stringToInteger(stringTokenizer.nextToken());
        board = new int[N + 2][M + 2];
        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(ioBuffered.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = stringToInteger(stringTokenizer.nextToken());
            }
        }
    }

    private static int stringToInteger(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = 0;
        while (check()) {
            answer++;
        }
    }

    private static boolean check() {
        bfs();
        int[][] temp = new int[N + 2][M + 2];
        copyArray(temp, board);
        boolean is = melt(temp);
        copyArray(board, temp);
        return is;
    }

    private static boolean melt(int[][] temp) {
        boolean is = false;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (board[i][j] == 1) {
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = j + xa[k];
                        int ny = i + ya[k];
                        if (nx < 0 || ny < 0 || N <= nx || M <= ny) {
                            count++;
                            continue;
                        }

                        if (board[ny][nx] == 0) {
                            count++;
                        }
                    }

                    if (count > 1) {
                        temp[i][j] = 0;
                        is = true;
                    }
                }
            }
        }
        return is;
    }

    private static void copyArray(int[][] temp, int[][] board) {
        for (int i = 0; i < N + 2; i++) {
            temp[i] = Arrays.copyOf(board[i], M + 2);
        }
    }

    private static void bfs() {
        arrayChange();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        queue.add(0);
        boolean[][] visit = new boolean[N + 2][M + 2];
        visit[0][0] = true;
        while ( ! queue.isEmpty()) {
            int cx = queue.poll();
            int cy = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (nx < 0 || ny < 0 || N + 2 <= ny || M + 2 <= nx) {
                    continue;
                }

                if (board[ny][nx] != 1 && ! visit[ny][nx]) {
                    queue.add(nx);
                    queue.add(ny);
                    visit[ny][nx] = true;
                    board[ny][nx] = 0;
                }
            }
        }
    }

    private static void arrayChange() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 2;
                }
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

        /**
         * IOBuffered Create
         * @param input Input String
         * @return IOBuffered Instance
         */
        public static IOBuffered create(String input) throws IOException {
            return new IOBuffered(input);
        }

        /**
         * BufferedReader Read Line
         * @return BufferedReader.readLIne
         */
        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        /**
         * Console Print Out (BufferedWriter.write())
         */
        public void print(int answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int answer) throws IOException {
            //TODO Answer Write Implement
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
