package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1303
public class Backjun1303 {
    private static final String[] array = {
            "5 5\n" +
            "WBWWW\n" +
            "WWWWW\n" +
            "BBBBB\n" +
            "BBBWW\n" +
            "WWWWW"
    };
    private static IOBuffered ioBuffered;
    private static int N;
    private static int M;
    private static char[][] board;
    private static boolean[][] check;
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
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        board = new char[M][N];
        check = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            board[i] = ioBuffered.readLine().toCharArray();
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = new int[2];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!check[i][j]) {
                    int temp = bfs(i, j);
                    if (board[i][j] == 'W') {
                        answer[0] += temp * temp;
                    } else {
                        answer[1] += temp * temp;
                    }
                }
            }
        }
    }

    private static final int[] xa = new int[]{0, 1, 0, -1};
    private static final int[] ya = new int[]{-1, 0, 1, 0};
    private static int bfs(int y, int x) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(Pair.of(y, x));
        check[y][x] = true;
        int cnt = 1;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.getX() + xa[i];
                int ny = cur.getY() + ya[i];
                if (isOutOfArray(ny, nx)) {
                    continue;
                }

                if (isVisit(ny, nx)) {
                    continue;
                }

                if (isNotFriendly(y, x, ny, nx)) {
                    continue;
                }

                queue.offer(Pair.of(ny, nx));
                check[ny][nx] = true;
                cnt++;
            }
        }

        return cnt;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static boolean isVisit(int ny, int nx) {
        return check[ny][nx];
    }

    private static boolean isOutOfArray(int y, int x) {
        return y < 0 || x < 0 || M <= y || N <= x;
    }

    private static boolean isNotFriendly(int y, int x, int ny, int nx) {
        return board[y][x] != board[ny][nx];
    }

    private static class Pair {
        private final int y;
        private final int x;

        private Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public static Pair of(int y, int x) {
            return new Pair(y, x);
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
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
            for (int i = 0; i < answer.length; i++) {
                bufferedWriter.write(String.valueOf(answer[i]));
                if (i != answer.length - 1) {
                    bufferedWriter.write(" ");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
