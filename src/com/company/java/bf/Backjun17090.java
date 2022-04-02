package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/17090
public class Backjun17090 {
    private static int N;
    private static int M;
    private static int[][] board;
    private static int[][] d;
    private static final String[] array = {
            "3 3\n" +
            "DDD\n" +
            "DDD\n" +
            "DDD",
            "3 3\n" +
            "DDR\n" +
            "DLU\n" +
            "LLL",
            "3 3\n" +
            "RRD\n" +
            "RDD\n" +
            "ULL",
            "3 4\n" +
            "RRDD\n" +
            "RRDR\n" +
            "DULU",
            ""
    };

    public static void main (String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("500 500\n");
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    sb.append("R");
                } else if (i % 2 == 0 && j % 2 != 0) {
                    sb.append("D");
                } else if (i % 2 != 0 && j % 2 != 0) {
                    sb.append("L");
                } else {
                    sb.append("U");
                }
            }

            sb.append("\n");
        }

        array[4] = sb.toString();
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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (c[j] == 'R') {
                    board[i][j] = 0;
                } else if (c[j] == 'D') {
                    board[i][j] = 1;
                } else if (c[j] == 'L') {
                    board[i][j] = 2;
                } else {
                    board[i][j] = 3;
                }
            }
        }

        d = new int[N][M];
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (d[i][j] == 0) {
                    if (recursion(j, i)) {
                        answer++;
                    }
                } else if (d[i][j] == 1) {
                    answer++;
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static boolean recursion(int x, int y) {
        if (x < 0 || y < 0 || N <= y || M <= x) {
            return true;
        }

        if (d[y][x] != 0) {
            return d[y][x] == 1;
        }

        d[y][x] = 2;
        boolean ok = recursion(x + xa[board[y][x]], y + ya[board[y][x]]);
        if (ok) {
            d[y][x] = 1;
            return true;
        }

        return false;
    }
}