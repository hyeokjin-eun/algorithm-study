package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10026
public class Backjun10026 {
    private static final int[] xa = new int[]{0, -1, 1, 0};
    private static final int[] ya = new int[]{-1, 0, 0, 1};
    private static final String[] array = {
            "5\n" +
            "RRRBB\n" +
            "GGBBB\n" +
            "BBBRR\n" +
            "BBRRR\n" +
            "RRRRR"
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
        int N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        boolean[][] check1 = new boolean[N][N];
        boolean[][] check2 = new boolean[N][N];
        int answer1 = 0;
        int answer2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!check1[i][j]) {
                    bfs1(j, i, board[i][j], check1, N, board);
                    answer1++;
                }

                if (!check2[i][j]) {
                    bfs2(j, i, board[i][j], check2, N, board);
                    answer2++;
                }
            }
        }

        bw.write(String.valueOf(answer1));
        bw.write(" ");
        bw.write(String.valueOf(answer2));
        bw.flush();
    }

    private static void bfs1(int x, int y, char c, boolean[][] check, int N, char[][] board) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        check[y][x] = true;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int cx = pair.x;
            int cy = pair.y;
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (nx < 0 || ny < 0 || N <= nx || N <= ny) {
                    continue;
                }

                if (!check[ny][nx] && board[ny][nx] == c) {
                    check[ny][nx] = true;
                    q.add(new Pair(nx, ny));
                }
            }
        }
    }

    private static void bfs2(int x, int y, char c, boolean[][] check, int N, char[][] board) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        check[y][x] = true;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int cx = pair.x;
            int cy = pair.y;
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (nx < 0 || ny < 0 || N <= nx || N <= ny) {
                    continue;
                }

                boolean is = false;
                if (board[ny][nx] == 'B' && c == 'B') {
                    is = true;
                } else if ((board[ny][nx] == 'R' || board[ny][nx] == 'G') && (c == 'R' || c == 'G')) {
                    is = true;
                }

                if (!check[ny][nx] && is) {
                    check[ny][nx] = true;
                    q.add(new Pair(nx, ny));
                }
            }
        }
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

