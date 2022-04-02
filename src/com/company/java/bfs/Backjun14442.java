package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14442
public class Backjun14442 {
    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static final String[] array = {
            "6 4 1\n" +
            "0100\n" +
            "1110\n" +
            "1000\n" +
            "0000\n" +
            "0111\n" +
            "0000",
            "6 4 2\n" +
            "0100\n" +
            "1110\n" +
            "1000\n" +
            "0000\n" +
            "0111\n" +
            "0000",
            "4 4 3\n" +
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
        int K = Integer.parseInt(st.nextToken());
        int[][][] dist = new int[N][M][K + 1];
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int answer = bfs(N, M, K, board, dist);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int N, int M, int K, int[][] board, int[][][] dist) {
        Queue<Integer> xq = new LinkedList<>();
        Queue<Integer> yq = new LinkedList<>();
        Queue<Integer> kq = new LinkedList<>();
        xq.add(0);
        yq.add(0);
        kq.add(0);
        dist[0][0][0] = 1;
        while (!xq.isEmpty() && !yq.isEmpty() && !kq.isEmpty()) {
            int cx = xq.poll();
            int cy = yq.poll();
            int ck = kq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (nx < 0 || ny < 0 || M <= nx || N <= ny) {
                    continue;
                }

                if (dist[ny][nx][ck] == 0 && board[ny][nx] == 0) {
                    dist[ny][nx][ck] = dist[cy][cx][ck] + 1;
                    xq.add(nx);
                    yq.add(ny);
                    kq.add(ck);
                }

                if (ck + 1 <= K && dist[ny][nx][ck + 1] == 0 && board[ny][nx] == 1) {
                    dist[ny][nx][ck + 1] = dist[cy][cx][ck] + 1;
                    xq.add(nx);
                    yq.add(ny);
                    kq.add(ck + 1);
                }
            }
        }

        int answer = -1;
        for (int i = 0; i <= K; i++) {
            if (answer != -1 && dist[N - 1][M - 1][i] != 0) {
                answer = Math.min(dist[N - 1][M - 1][i], answer);
                continue;
            }

            if (dist[N - 1][M - 1][i] != 0 && answer == -1) {
                answer = dist[N - 1][M - 1][i];
            }
        }

        return answer;
    }
}