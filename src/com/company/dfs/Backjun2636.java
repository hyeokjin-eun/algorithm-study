package com.company.dfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2636
public class Backjun2636 {
    private static final String[] array = {
            "13 12\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 1 1 0 0 0\n" +
            "0 1 1 1 0 0 0 1 1 0 0 0\n" +
            "0 1 1 1 1 1 1 0 0 0 0 0\n" +
            "0 1 1 1 1 1 0 1 1 0 0 0\n" +
            "0 1 1 1 1 0 0 1 1 0 0 0\n" +
            "0 0 1 1 0 0 0 1 1 0 0 0\n" +
            "0 0 1 1 1 1 1 1 1 0 0 0\n" +
            "0 0 1 1 1 1 1 1 1 0 0 0\n" +
            "0 0 1 1 1 1 1 1 1 0 0 0\n" +
            "0 0 1 1 1 1 1 1 1 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0"
    };

    public static void main(String[] args) throws IOException {
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
        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[][] board = new int[Y][X];
        for (int i = 0; i < Y; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < X; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int time = 0;
        int answer = 0;
        while (true) {
            boolean[][] check = new boolean[Y][X];
            int temp = bfs(0, 0, Y, X, check, board);
            if (0 < temp) {
                time++;
                answer = temp;
            } else {
                break;
            }
        }

        bw.write(String.valueOf(time));
        bw.write("\n");
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int x, int y, int n, int m, boolean[][] check, int[][] board) {
        int[] xa = new int[]{1, 0, -1, 0};
        int[] ya = new int[]{0, 1, 0, -1};
        Queue<Integer> xq = new LinkedList<>();
        Queue<Integer> yq = new LinkedList<>();
        xq.add(x);
        yq.add(y);
        check[y][x] = true;
        boolean[][] cheese = new boolean[n][m];
        int cnt = 0;
        while (!xq.isEmpty() && !yq.isEmpty()) {
            int cx = xq.poll();
            int cy = yq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (nx < 0 || ny < 0 || m <= nx || n <= ny) {
                    continue;
                }

                if (!check[ny][nx] && board[ny][nx] == 0 && !cheese[ny][nx]) {
                    xq.add(nx);
                    yq.add(ny);
                    check[ny][nx] = true;
                }

                if (!check[ny][nx] && board[ny][nx] == 1 && !cheese[ny][nx]) {
                    board[ny][nx] = 0;
                    cheese[ny][nx] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
