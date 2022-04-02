package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/9944
public class Backjun9944 {
    private static final String[] array = {
            "5 5\n" +
            "**...\n" +
            ".....\n" +
            "....*\n" +
            "..*..\n" +
            ".....\n" +
            "3 4\n" +
            "****\n" +
            "*...\n" +
            "*..*"
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
        int index = 1;
        while (true) {
            String s = br.readLine();
            if (s == null) {
                break;
            }

            StringTokenizer st = new StringTokenizer(s);
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] board = new int[N][M];
            int max = 0;
            for (int i = 0; i < N; i++) {
                char[] c= br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    if (c[j] == '*') {
                        board[i][j] = -1;
                    } else {
                        max++;
                    }
                }
            }

            int answer = -1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = -1;
                        int temp = recursion(max, 1, 0, j, i, board, N, M);
                        if (temp != -1) {
                            if (answer == -1 || temp < answer) {
                                answer = temp;
                            }
                        }

                        board[i][j] = 0;
                    }
                }
            }

            bw.write("Case " + index + ": " + answer);
            bw.write("\n");
            index++;
        }

        bw.flush();
    }

    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static int recursion(int max, int sum, int index, int x, int y, int[][] board, int N, int M) {
        if (max == sum) {
            return index;
        }

        int ans = -1;
        for (int i = 0; i < 4; i++) {
            int nx = x + xa[i];
            int ny = y + ya[i];
            int temp = 0;
            while (0 <= nx && 0 <= ny && nx < M && ny < N && board[ny][nx] == 0) {
                board[ny][nx] = -1;
                temp++;
                nx += xa[i];
                ny += ya[i];
            }

            nx -= xa[i];
            ny -= ya[i];
            if (!(x == nx && y == ny)) {
                int re = recursion(max, sum + temp, index + 1, nx, ny, board, N, M);
                if (re != -1) {
                    if (ans == -1 || re < ans) {
                        ans = re;
                    }
                }
            }

            while (!(x == nx && y == ny)) {
                board[ny][nx] = 0;
                temp--;
                nx -= xa[i];
                ny -= ya[i];
            }
        }

        return ans;
    }
}