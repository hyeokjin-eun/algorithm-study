package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1012
public class Backjun1012 {
    private static final String[] array = {
            "2\n" +
            "10 8 17\n" +
            "0 0\n" +
            "1 0\n" +
            "1 1\n" +
            "4 2\n" +
            "4 3\n" +
            "4 5\n" +
            "2 4\n" +
            "3 4\n" +
            "7 4\n" +
            "8 4\n" +
            "9 4\n" +
            "7 5\n" +
            "8 5\n" +
            "9 5\n" +
            "7 6\n" +
            "8 6\n" +
            "9 6\n" +
            "10 10 1\n" +
            "5 5",
            "1\n" +
            "5 3 6\n" +
            "0 2\n" +
            "1 2\n" +
            "2 2\n" +
            "3 2\n" +
            "4 2\n" +
            "4 0"
    };
    private static IOBuffered ioBuffered;
    private static ArrayList<int[][]> testList;
    private static boolean[][] dist;
    private static int[][] board;
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
        testList = new ArrayList<>();
        int T = stoi(ioBuffered.readLine());
        for (int t = 0; t < T; t++) {
            testList.add(setBoard());
        }
    }

    private static int[][] setBoard() throws IOException {
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        int M = stoi(st.nextToken());
        int N = stoi(st.nextToken());
        int K = stoi(st.nextToken());
        return setBug(M, N, K);
    }

    private static int[][] setBug(int M, int N, int K) throws IOException {
        int[][] board = new int[N][M];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            int X = stoi(st.nextToken());
            int Y = stoi(st.nextToken());
            board[Y][X] = 1;
        }
        return board;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = new int[testList.size()];
        for (int i = 0; i < answer.length; i++) {
            getCount(i);
        }
    }

    private static void getCount(int index) {
        board = testList.get(index);
        dist = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (isVisit(i, j)) {
                    bfs(j, i);
                    answer[index]++;
                }
            }
        }
    }

    private static boolean isVisit(int y, int x) {
        return !dist[y][x] && board[y][x] == 1;
    }

    private static final int[] xa = new int[]{0, 1, 0, -1};
    private static final int[] ya = new int[]{-1, 0, 1, 0};

    private static void bfs(int x, int y) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(Pair.of(x, y));
        dist[y][x] = true;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            int cx = cur.getX();
            int cy = cur.getY();
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (isOutOfIndex(nx, ny)) {
                    continue;
                }

                if (isVisit(ny, nx)) {
                    queue.offer(Pair.of(nx, ny));
                    dist[ny][nx] = true;
                }
            }
        }
    }

    private static boolean isOutOfIndex(int nx, int ny) {
        return nx < 0 || ny < 0 || board.length <= ny || board[0].length <= nx;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static class Pair {
        private final int x;
        private final int y;

        private Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Pair of(int x, int y) {
            return new Pair(x, y);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
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
