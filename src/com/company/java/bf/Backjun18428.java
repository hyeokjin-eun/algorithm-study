package com.company.java.bf;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/12101
public class Backjun18428 {
    private static int N;
    private static char[][] board;
    private static final int obstacle = 3;
    private static final String[] array = {
            "5\n" +
            "X S X X T\n" +
            "T X S X X\n" +
            "X X X X X\n" +
            "X T X X X\n" +
            "X X T X X",
            "4\n" +
            "S S S T\n" +
            "X X X X\n" +
            "X X X X\n" +
            "T T T X"
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
        // Dump Data Setting
        char[][] b1 = new char[][]{
                {'S', 'S', 'S', 'T'},
                {'O', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X'},
                {'T', 'T', 'T', 'X'}
        };

        N = 4;
        board = b1;

        // check Method Test
        assert !check(3, 0);
        assert check(0, 3);

        // Dump Data Setting
        char[][] b2 = new char[][]{
                {'S', 'S', 'S', 'T'},
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X'},
                {'T', 'T', 'T', 'X'}
        };

        board = b2;
        // recursion Method Test
        assert !recursion(0);
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        N = stoi(br.readLine());
        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = st.nextToken().charAt(0);
            }
        }

        bw.write(recursion(0) ? "YES" : "NO");
        bw.flush();
    }

    private static boolean recursion(int select) {
        if (select == obstacle) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 'T' && !check(j, i)) {
                        return false;
                    }
                }
            }

            return true;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'X') {
                    board[i][j] = 'O';
                    if (recursion(select + 1)) {
                        return true;
                    }

                    board[i][j] = 'X';
                }
            }
        }

        return false;
    }

    private static final int[] xa = new int[]{0, 1, 0, -1};
    private static final int[] ya = new int[]{-1, 0, 1, 0};
    private static boolean check(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + xa[i];
            int ny = y + ya[i];
            while (0 <= nx && 0 <= ny && nx < N && ny < N) {
                if (board[ny][nx] == 'O') {
                    break;
                }

                if (board[ny][nx] == 'S') {
                    return false;
                }

                nx += xa[i];
                ny += ya[i];
            }
        }

        return true;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}