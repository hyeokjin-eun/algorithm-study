package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16933
// TODO: 2021-10-13 다시 한번 풀어보기
public class Backjun16933 {
    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static final String[] array = {
            "1 4 1\n" +
            "0010",
            "1 4 1\n" +
            "0100",
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
            "0000"
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
        int[][][][] dist = new int[N][M][K + 1][2];
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = temp[j] - '0';
            }
        }

        int answer = bfs(N, M, K, dist, board);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int N, int M, int K, int[][][][] dist, int[][] board) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0, 0, 0));
        dist[0][0][0][0] = 1;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int cx = pair.x;
            int cy = pair.y;
            int ck = pair.z;
            int d = pair.d;
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (nx < 0 || ny < 0 || M <= nx || N <= ny) {
                    continue;
                }

                if (dist[ny][nx][ck][1 - d] == 0 && board[ny][nx] == 0) {
                    dist[ny][nx][ck][1 - d] = dist[cy][cx][ck][d] + 1;
                    q.add(new Pair(nx, ny, ck, 1 - d));
                }

                if (d == 0 && ck + 1 <= K && dist[ny][nx][ck + 1][1 - d] == 0 && board[ny][nx] == 1) {
                    dist[ny][nx][ck + 1][1 - d] = dist[cy][cx][ck][d] + 1;
                    q.add(new Pair(nx, ny, ck + 1, 1 - d));
                }
            }

            if (dist[cy][cx][ck][1 - d] == 0) {
                dist[cy][cx][ck][1 - d] = dist[cy][cx][ck][d] + 1;
                q.add(new Pair(cx, cy, ck, 1 - d));
            }
        }

        int answer = -1;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i <= K; i++) {
                if (answer != -1 && dist[N - 1][M - 1][i][j] != 0) {
                    answer = Math.min(dist[N - 1][M - 1][i][j], answer);
                    continue;
                }

                if (dist[N - 1][M - 1][i][j] != 0 && answer == -1) {
                    answer = dist[N - 1][M - 1][i][j];
                }
            }
        }

        return answer;
    }

    private static class Pair {
        int x;
        int y;
        int z;
        int d;
        Pair(int x, int y, int z, int d) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.d = d;
        }
    }
}