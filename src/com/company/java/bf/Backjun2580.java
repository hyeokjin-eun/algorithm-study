package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2580
public class Backjun2580 {
    private static final String[] array = {
            "0 3 5 4 6 9 2 7 8\n" +
            "7 8 2 1 0 5 6 0 9\n" +
            "0 6 0 2 7 8 1 3 5\n" +
            "3 2 1 0 4 6 8 9 7\n" +
            "8 0 4 9 1 3 5 0 6\n" +
            "5 9 6 8 2 0 4 1 3\n" +
            "9 1 7 6 5 2 0 8 0\n" +
            "6 0 3 7 0 1 9 5 2\n" +
            "2 5 8 3 9 4 7 6 0",
            "0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0",
            "0 7 2 0 0 0 0 0 5\n" +
            "0 6 1 0 0 0 8 4 2\n" +
            "0 0 9 2 1 8 0 0 7\n" +
            "0 0 6 0 0 0 0 0 3\n" +
            "7 0 8 0 6 0 0 0 0\n" +
            "4 0 0 0 3 2 7 6 0\n" +
            "0 0 0 5 9 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0\n" +
            "9 0 0 4 0 0 0 7 8"
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
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursion(0, 0, board, 0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(String.valueOf(board[i][j]));
                bw.write(" ");
            }

            if (i != 8) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static boolean recursion(int row, int col, int[][] board, int z) {
        if (z == 81) {
            return true;
        }

        int t = z;
        for (int i = col; i < 9; i++) {
            t++;
            if (board[row][i] == 0) {
                ArrayList<Integer> a = check(row, i, board);
                for (int j = 0; j < a.size(); j++) {
                    board[row][i] = a.get(j);
                    boolean temp = recursion(row, i + 1, board, t);
                    if (temp) {
                        return true;
                    } else {
                        board[row][i] = 0;
                    }
                }

                return false;
            }
        }

        return recursion(row + 1, 0, board, t);
    }

    private static ArrayList<Integer> check(int row, int col, int[][] board) {
        boolean[] check = new boolean[10];
        for (int i = 0; i < 9; i++) {
            check[board[i][col]] = true;
            check[board[row][i]] = true;
        }

        int srow = 3 * (row / 3);
        int scol = 3 * (col / 3);
        for (int i = srow; i < srow + 3; i++) {
            for (int j = scol; j < scol + 3; j++) {
                check[board[i][j]] = true;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (!check[i]) {
                result.add(i);
            }
        }

        return result;
    }
}