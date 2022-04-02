package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16197
public class Backjun16197 {
    private static final String[] array = {
            "1 2\n" +
            "oo",
            "6 2\n" +
            "..\n" +
            "..\n" +
            "..\n" +
            "o#\n" +
            "o#\n" +
            "##",
            "6 2\n" +
            "..\n" +
            "..\n" +
            "..\n" +
            "o#\n" +
            "o#\n" +
            "##",
            "5 3\n" +
            "###\n" +
            ".o.\n" +
            "###\n" +
            ".o.\n" +
            "###",
            "5 3\n" +
            "###\n" +
            ".o.\n" +
            "#.#\n" +
            ".o.\n" +
            "###"
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
        char[][] board = new char[N][M];
        Pair[] pairs = new Pair[2];
        int index = 0;
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'o') {
                    pairs[index] = new Pair(j, i);
                    index++;
                    board[i][j] = '.';
                }
            }
        }

        int min = recursion(pairs[0].x, pairs[1].x, pairs[0].y, pairs[1].y,0, N, M, board);
        bw.write(String.valueOf(min));
        bw.flush();

    }

    private static int recursion(int x1, int x2, int y1, int y2, int cnt, int N, int M, char[][] board) {
        if (cnt == 11) {
            return -1;
        }

        boolean fail1 = false;
        boolean fail2 = false;
        if (x1 < 0 || M <= x1 || y1 < 0 || N <= y1) {
            fail1 = true;
        }

        if (x2 < 0 || M <= x2 || y2 < 0 || N <= y2) {
            fail2 = true;
        }

        if (fail1 && fail2) {
            return -1;
        }

        if (fail1 || fail2) {
            return cnt;
        }

        int[] xa = new int[]{1, 0, -1, 0};
        int[] ya = new int[]{0, 1, 0, -1};
        int answer = -1;
        for (int i = 0; i < 4; i++) {
            int nx1 = x1 + xa[i];
            int ny1 = y1 + ya[i];
            int nx2 = x2 + xa[i];
            int ny2 = y2 + ya[i];
            if (0 <= nx1 && nx1 < M && 0 <= ny1 && ny1 < N && board[ny1][nx1] == '#') {
                nx1 = x1;
                ny1 = y1;
            }

            if (0 <= nx2 && nx2 < M && 0 <= ny2 && ny2 < N && board[ny2][nx2] == '#') {
                nx2 = x2;
                ny2 = y2;
            }

            int temp = recursion(nx1, nx2, ny1, ny2,cnt + 1, N, M, board);
            if (temp == -1) {
                continue;
            }

            if (answer == -1 || temp < answer) {
                answer = temp;
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

