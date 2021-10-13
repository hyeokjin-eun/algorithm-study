package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16954
public class Backjun16954 {
    private static final int[] xa = {0, 0, 1, -1, 1, -1, 1, -1, 0};
    private static final int[] ya = {1, -1, 0, 0, 1, 1, -1, -1, 0};
    private static final String[] array = {
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "........",
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "##......\n" +
            "........",
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            "........\n" +
            ".#......\n" +
            "#.......\n" +
            ".#......"
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
        char[][] board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            board[i] = br.readLine().toCharArray();
        }

        bw.write(String.valueOf(bfs(board)));
        bw.flush();
    }

    private static int bfs(char[][] board) {
        Queue<Integer> q = new LinkedList<>();
        boolean[][][] check = new boolean[8][8][9];
        q.add(0);
        q.add(7);
        q.add(0);
        boolean answer = false;
        check[7][0][0] = true;
        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            int t = q.poll();
            if (x == 7 && y == 0) {
                answer = true;
            }

            for (int i = 0; i < 9; i++) {
                int nx = x + xa[i];
                int ny = y + ya[i];
                int nt = Math.min(t + 1, 8);
                if (nx < 0 || ny < 0 || 8 <= nx || 8 <= ny) {
                    continue;
                }

                if (ny - t >= 0 && board[ny - t][nx] == '#') {
                    continue;
                }

                if (ny - t - 1 >= 0 && board[ny - t - 1][nx] == '#') {
                    continue;
                }

                if (!check[ny][nx][nt]) {
                    check[ny][nx][nt] = true;
                    q.add(nx);
                    q.add(ny);
                    q.add(nt);
                }
            }
        }

        return answer ? 1 : 0;
    }
}