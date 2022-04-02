package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2206
public class Backjun2206 {
    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static final String[] array = {
            "6 4\n" +
            "0100\n" +
            "1110\n" +
            "1000\n" +
            "0000\n" +
            "0111\n" +
            "0000",
            "4 4\n" +
            "0111\n" +
            "1111\n" +
            "1111\n" +
            "1110"
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
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int answer = bfs(N, M, board);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int N, int M, int[][] board) {
        int[][][] dist = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }

        Queue<Integer> xq = new LinkedList<>();
        Queue<Integer> yq = new LinkedList<>();
        Queue<Integer> tq = new LinkedList<>();
        xq.add(0);
        yq.add(0);
        tq.add(0);
        dist[0][0][0] = 1;
        while (!xq.isEmpty() && !yq.isEmpty() && !tq.isEmpty()) {
            int cx = xq.poll();
            int cy = yq.poll();
            int ct = tq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (nx < 0 || ny < 0 || M <= nx || N <= ny) {
                    continue;
                }

                if (dist[ny][nx][ct] == -1 && board[ny][nx] == 0) {
                    dist[ny][nx][ct] = dist[cy][cx][ct] + 1;
                    xq.add(nx);
                    yq.add(ny);
                    tq.add(ct);
                }

                if (ct == 0 && dist[ny][nx][ct + 1] == -1 && board[ny][nx] == 1) {
                    dist[ny][nx][ct + 1] = dist[cy][cx][ct] + 1;
                    xq.add(nx);
                    yq.add(ny);
                    tq.add(ct + 1);
                }
            }
        }

        if (dist[N - 1][M - 1][0] == -1 && dist[N - 1][M - 1][1] == -1) {
            return -1;
        } else {
            if (dist[N - 1][M - 1][0] == -1 || dist[N - 1][M - 1][1] == -1) {
                return dist[N - 1][M - 1][0] == -1 ? dist[N - 1][M - 1][1] : dist[N - 1][M - 1][0];
            } else {
                return Math.min(dist[N - 1][M - 1][0], dist[N - 1][M - 1][1]);
            }
        }
    }
}