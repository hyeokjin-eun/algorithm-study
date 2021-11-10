package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/15684
public class Backjun15684 {
    private static int N;
    private static int M;
    private static int H;
    private static int[][] board;
    private static ArrayList<Pair> pairs = new ArrayList<>();
    private static final String[] array = {
            "2 1 3\n" +
            "1 1",
            "2 0 3",
            "5 5 6\n" +
            "1 1\n" +
            "3 2\n" +
            "2 3\n" +
            "5 1\n" +
            "5 4",
            "6 5 6\n" +
            "1 1\n" +
            "3 2\n" +
            "1 3\n" +
            "2 5\n" +
            "5 5",
            "5 8 6\n" +
            "1 1\n" +
            "2 2\n" +
            "3 3\n" +
            "4 4\n" +
            "3 1\n" +
            "4 2\n" +
            "5 3\n" +
            "6 4",
            "5 12 6\n" +
            "1 1\n" +
            "1 3\n" +
            "2 2\n" +
            "2 4\n" +
            "3 1\n" +
            "3 3\n" +
            "4 2\n" +
            "4 4\n" +
            "5 1\n" +
            "5 3\n" +
            "6 2\n" +
            "6 4",
            "5 6 6\n" +
            "1 1\n" +
            "3 1\n" +
            "5 2\n" +
            "4 3\n" +
            "2 3\n" +
            "1 4"
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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new int[H][N];
        for (int i = 0; i < H; i++) {
            Arrays.fill(board[i], -1);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            board[y][x] = x + 1;
            board[y][x + 1] = x;
        }

        pairs = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (board[i][j] != -1 || board[i][j + 1] != -1) {
                    continue;
                }

                pairs.add(new Pair(j, i));
            }
        }

        int answer = -1;
        if (check()) {
            answer = 0;
        } else {
            for (int i = 0; i < pairs.size(); i++) {
                int x1 = pairs.get(i).x;
                int y1 = pairs.get(i).y;
                if (pointCheck(x1, y1)) continue;
                board[y1][x1] = x1 + 1;
                board[y1][x1 + 1] = x1;
                if (check()) {
                    if (answer == -1 || 1 < answer) {
                        answer = 1;
                    }
                }

                for (int j = i + 1; j < pairs.size(); j++) {
                    int x2 = pairs.get(j).x;
                    int y2 = pairs.get(j).y;
                    if (pointCheck(x2, y2)) continue;
                    board[y2][x2] = x2 + 1;
                    board[y2][x2 + 1] = x2;
                    if (check()) {
                        if (answer == -1 || 2 < answer) {
                            answer = 2;
                        }
                    }

                    for (int k = j + 1; k < pairs.size(); k++) {
                        int x3 = pairs.get(k).x;
                        int y3 = pairs.get(k).y;
                        if (pointCheck(x3, y3)) continue;
                        board[y3][x3] = x3 + 1;
                        board[y3][x3 + 1] = x3;
                        if (check()) {
                            if (answer == -1 || 3 < answer) {
                                answer = 3;
                            }
                        }

                        board[y3][x3] = -1;
                        board[y3][x3 + 1] = -1;
                    }

                    board[y2][x2] = -1;
                    board[y2][x2 + 1] = -1;
                }

                board[y1][x1] = -1;
                board[y1][x1 + 1] = -1;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static boolean check() {
        for (int i = 0; i < N; i++) {
            if (!dfs(i, i, 0)) {
                return false;
            }
        }

        return true;
    }

    private static boolean pointCheck(int x, int y) {
        return board[y][x] != -1 || board[y][x + 1] != -1;
    }

    private static boolean dfs(int s, int x, int y) {
        if (y == H) {
            return x == s;
        }

        if (board[y][x] != -1) {
            return dfs(s, board[y][x], y + 1);
        } else {
            return dfs(s, x, y + 1);
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