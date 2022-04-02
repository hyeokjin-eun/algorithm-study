package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14502
public class Backjun14502 {
    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static final String[] array = {
            "7 7\n" +
            "2 0 0 0 1 1 0\n" +
            "0 0 1 0 1 2 0\n" +
            "0 1 1 0 1 0 0\n" +
            "0 1 0 0 0 0 0\n" +
            "0 0 0 0 0 1 1\n" +
            "0 1 0 0 0 0 0\n" +
            "0 1 0 0 0 0 0",
            "3 4\n" +
            "0 2 1 0\n" +
            "0 1 1 2\n" +
            "0 0 1 0"
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
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][M];
        Queue<Pair> virus = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    virus.add(new Pair(j, i));
                }
            }
        }

        int answer = bf(0, 0, N, M, 0, board, virus);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bf(int x, int y, int N, int M, int cnt, int[][] board, Queue<Pair> virus) {
        if (cnt == 3) {
            return bfs(virus, N, M, board);
        }

        if (y >= N) {
            return 0;
        }

        int ny = x == M - 1 ? y + 1 : y;
        int nx = x == M - 1 ? 0 : x + 1;
        if (board[y][x] == 0) {
            board[y][x] = 1;
            int select = bf(nx, ny, N, M, cnt + 1, board, virus);
            board[y][x] = 0;
            int unselect = bf(nx, ny, N, M, cnt, board, virus);
            return Math.max(select, unselect);
        } else {
            return bf(nx, ny, N, M, cnt, board, virus);
        }
    }

    private static int bfs(Queue<Pair> virus, int N, int M, int[][] board) {
        int[][] temp = new int[board.length][board[0].length];
        for (int i = 0; i < temp.length; i++) {
            System.arraycopy(board[i], 0, temp[i], 0, board[0].length);
        }

        Queue<Pair> q = new LinkedList<>(virus);
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = pair.x + xa[i];
                int ny = pair.y + ya[i];
                if (nx < 0 || M <= nx || ny < 0 || N <= ny) {
                    continue;
                }

                if (temp[ny][nx] == 0) {
                    temp[ny][nx] = 2;
                    q.add(new Pair(nx, ny));
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 0) {
                   answer++;
                }
            }
        }

        return answer;
    }

    private static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}