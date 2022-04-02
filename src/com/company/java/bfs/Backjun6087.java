package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/6087
public class Backjun6087 {
    private static final int[] xa = new int[]{0, -1, 1, 0};
    private static final int[] ya = new int[]{-1, 0, 0, 1};
    private static final String[] array = {
            "7 8\n" +
            ".......\n" +
            "......C\n" +
            "......*\n" +
            "*****.*\n" +
            "....*..\n" +
            "....*..\n" +
            ".C..*..\n" +
            ".......",
            "4 7\n" +
            "....\n" +
            ".*..\n" +
            ".*.C\n" +
            "C*..\n" +
            ".*..\n" +
            ".*..\n" +
            "...."
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
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        char[][] board = new char[H][W];
        boolean is = true;
        Pair sp = new Pair(0, 0);
        Pair ep = new Pair(0, 0);
        for (int i = 0; i < H; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                board[i][j] = temp[j];
                if (board[i][j] == 'C') {
                    if (is) {
                        is = false;
                        sp = new Pair(j, i);
                    } else {
                        ep = new Pair(j, i);
                    }
                }
            }

        }

        int answer = bfs(sp, ep, H, W, board);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(Pair sp, Pair ep, int H, int W, char[][] board) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] check = new boolean[H][W];
        int[][] dist = new int[H][W];
        q.add(sp);
        check[sp.y][sp.x] = true;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int x = pair.x;
            int y = pair.y;
            for (int i = 0 ; i < 4; i++) {
                int nx = x + xa[i];
                int ny = y + ya[i];
                while (0 <= nx && nx < W && 0 <= ny && ny < H) {
                    if (board[ny][nx] == '*') {
                        break;
                    }

                    if (!check[ny][nx]) {
                        check[ny][nx] = true;
                        dist[ny][nx] = dist[y][x] + 1;
                        q.add(new Pair(nx, ny));
                    }

                    nx += xa[i];
                    ny += ya[i];
                }
            }
        }

        return dist[ep.y][ep.x] - 1;
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