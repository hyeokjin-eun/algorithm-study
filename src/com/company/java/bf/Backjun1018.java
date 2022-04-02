package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1018
public class Backjun1018 {
    private static int N;
    private static int M;
    private static char[][] board;
    private static final String[] array = {
            "8 8\n" +
            "WBWBWBWB\n" +
            "BWBWBWBW\n" +
            "WBWBWBWB\n" +
            "BWBBBWBW\n" +
            "WBWBWBWB\n" +
            "BWBWBWBW\n" +
            "WBWBWBWB\n" +
            "BWBWBWBW",
            "10 13\n" +
            "BBBBBBBBWBWBW\n" +
            "BBBBBBBBBWBWB\n" +
            "BBBBBBBBWBWBW\n" +
            "BBBBBBBBBWBWB\n" +
            "BBBBBBBBWBWBW\n" +
            "BBBBBBBBBWBWB\n" +
            "BBBBBBBBWBWBW\n" +
            "BBBBBBBBBWBWB\n" +
            "WWWWWWWWWWBWB\n" +
            "WWWWWWWWWWBWB",
            "8 8\n" +
            "BWBWBWBW\n" +
            "WBWBWBWB\n" +
            "BWBWBWBW\n" +
            "WBWBWBWB\n" +
            "BWBWBWBW\n" +
            "WBWBWBWB\n" +
            "BWBWBWBW\n" +
            "WBWBWBWB",
            "9 23\n" +
            "BBBBBBBBBBBBBBBBBBBBBBB\n" +
            "BBBBBBBBBBBBBBBBBBBBBBB\n" +
            "BBBBBBBBBBBBBBBBBBBBBBB\n" +
            "BBBBBBBBBBBBBBBBBBBBBBB\n" +
            "BBBBBBBBBBBBBBBBBBBBBBB\n" +
            "BBBBBBBBBBBBBBBBBBBBBBB\n" +
            "BBBBBBBBBBBBBBBBBBBBBBB\n" +
            "BBBBBBBBBBBBBBBBBBBBBBB\n" +
            "BBBBBBBBBBBBBBBBBBBBBBW",
            "10 10\n" +
            "BBBBBBBBBB\n" +
            "BBWBWBWBWB\n" +
            "BWBWBWBWBB\n" +
            "BBWBWBWBWB\n" +
            "BWBWBWBWBB\n" +
            "BBWBWBWBWB\n" +
            "BWBWBWBWBB\n" +
            "BBWBWBWBWB\n" +
            "BWBWBWBWBB\n" +
            "BBBBBBBBBB",
            "8 8\n" +
            "WBWBWBWB\n" +
            "BWBWBWBW\n" +
            "WBWBWBWB\n" +
            "BWBBBWBW\n" +
            "WBWBWBWB\n" +
            "BWBWBWBW\n" +
            "WBWBWWWB\n" +
            "BWBWBWBW",
            "11 12\n" +
            "BWWBWWBWWBWW\n" +
            "BWWBWBBWWBWW\n" +
            "WBWWBWBBWWBW\n" +
            "BWWBWBBWWBWW\n" +
            "WBWWBWBBWWBW\n" +
            "BWWBWBBWWBWW\n" +
            "WBWWBWBBWWBW\n" +
            "BWWBWBWWWBWW\n" +
            "WBWWBWBBWWBW\n" +
            "BWWBWBBWWBWW\n" +
            "WBWWBWBBWWBW",
            "TEST MADE"
    };

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
        StringBuilder sb = new StringBuilder();
        sb.append("50 50\n");
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                sb.append("B");
            }

            if (i != 49) {
                sb.append("\n");
            }
        }

        array[array.length - 1] = sb.toString();
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int count = Integer.MAX_VALUE;
        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j < M - 7; j++) {
                count = Math.min(check(j, i), count);
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
    }

    private static char[][] aType = new char[][]{
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'}
    };

    private static char[][] bType = new char[][]{
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
            {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
            {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'}
    };

    private static int check(int x, int y) {
        int aCount = 0;
        int bCount = 0;
        for (int i = y; i < y + 8; i++) {
            for (int j = x; j < x + 8; j++) {
                if (board[i][j] != aType[i - y][j - x]) {
                    aCount++;
                }

                if (board[i][j] != bType[i - y][j - x]) {
                    bCount++;
                }
            }
        }

        return Math.min(aCount, bCount);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}