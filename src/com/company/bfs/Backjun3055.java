package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/3055
public class Backjun3055 {
    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static final String[] array = {
            "3 3\n" +
            "D.*\n" +
            "...\n" +
            ".S.",
            "3 3\n" +
            "D.*\n" +
            "...\n" +
            "..S",
            "3 6\n" +
            "D...*.\n" +
            ".X.X..\n" +
            "....S.",
            "5 4\n" +
            ".D.*\n" +
            "....\n" +
            "..X.\n" +
            "S.*.\n" +
            "....",
            "3 3\n" +
            "S..\n" +
            ".D.\n" +
            "...",
            "4 4\n" +
            "DX.*\n" +
            ".X..\n" +
            ".X..\n" +
            "...S",
            "3 3\n" +
            ".D.\n" +
            ".*.\n" +
            ".S*",
            "5 5\n" +
            "*.XS.\n" +
            "..X..\n" +
            "..X..\n" +
            "..X..\n" +
            "..X.D",
            "5 5\n" +
            "*.X..\n" +
            "..X..\n" +
            "..S..\n" +
            "..X..\n" +
            "..X.D"
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
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] board = new char[R][C];
        int[][] w = new int[R][C];
        int[][] d = new int[R][C];
        for (int i = 0; i < R; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                board[i][j] = temp[j];
                w[i][j] = -1;
                d[i][j] = -1;
            }
        }

        Queue<Pair> q = new LinkedList<>();
        int sx = 0;
        int sy = 0;
        int ex = 0;
        int ey = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == '*') {
                    q.offer(new Pair(j, i));
                    w[i][j] = 0;
                } else if (board[i][j] == 'S') {
                    sx = j;
                    sy = i;
                } else if (board[i][j] == 'D') {
                    ex = j;
                    ey = i;
                }
            }
        }

        wbfs(q, board, R, C, w);
        int answer = bfs(sx, sy, ex, ey, d, R, C, board, w);
        if (answer == -1) {
            bw.write("KAKTUS");
        } else {
            bw.write(String.valueOf(answer));
        }

        bw.flush();
    }

    private static int bfs(int sx, int sy, int ex, int ey, int[][] d, int R, int C, char[][] board, int[][] w) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(sx, sy));
        d[sy][sx] = 0;
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int cx = p.x;
            int cy = p.y;
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];



                if (d[ny][nx] != -1) {
                    continue;
                }

                if (board[ny][nx] == 'X') {
                    continue;
                }

                if (w[ny][nx] != -1 && d[cy][cx] + 1 >= w[ny][nx]) {
                    continue;
                }

                d[ny][nx] = d[cy][cx] + 1;
                q.add(new Pair(nx, ny));
            }
        }

        return d[ey][ex];
    }

    private static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void wbfs(Queue<Pair> q, char[][] board, int R, int C, int[][] w) {
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int cx = p.x;
            int cy = p.y;
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (nx < 0 || ny < 0 || C <= nx || R <= ny) {
                    continue;
                }

                if (w[ny][nx] != -1) continue;
                if (board[ny][nx] == 'X') continue;
                if (board[ny][nx] == 'D') continue;
                w[ny][nx] = w[cy][cx] + 1;
                q.add(new Pair(nx, ny));
            }
        }
    }
}