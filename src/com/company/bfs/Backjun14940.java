package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14940
public class Backjun14940 {
    private static int N;
    private static int M;
    private static int[][] map;
    private static int[][] dist;
    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static final String[] array = {
            "15 15\n" +
            "2 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "1 1 1 1 1 1 1 1 1 1 0 0 0 0 1\n" +
            "1 1 1 1 1 1 1 1 1 1 0 1 1 1 1\n" +
            "1 1 1 1 1 1 1 1 1 1 0 1 0 0 0\n" +
            "1 1 1 1 1 1 1 1 1 1 0 1 1 0 1"
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
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        int x = -1;
        int y = -1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] == 0 || map[i][j] == 2) {
                    dist[i][j] = 0;
                    if (map[i][j] == 2) {
                        y = i;
                        x = j;
                    }
                }
            }
        }

        bfs(x, y);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bw.write(String.valueOf(dist[i][j]));
                if (j != M - 1) {
                    bw.write(" ");
                }
            }

            if (i != N - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static void bfs(int x, int y) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        queue.offer(y);
        boolean[][] check = new boolean[N][M];
        check[y][x] = true;
        while (!queue.isEmpty()) {
            int cx = queue.poll();
            int cy = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (isArrayOutOfIndex(nx, ny)) {
                    continue;
                }

                if (isRoad(nx, ny) && !isVisit(nx, ny, check)) {
                    queue.offer(nx);
                    queue.offer(ny);
                    dist[ny][nx] = dist[cy][cx] + 1;
                    check[ny][nx] = true;
                }
            }
        }
    }

    private static boolean isArrayOutOfIndex(int x, int y) {
        return x < 0 || y < 0 || N <= y || M <= x;
    }

    private static boolean isRoad(int x, int y) {
        if (map[y][x] == 1) {
            return true;
        }

        if (map[y][x] == 0) {
            return false;
        }

        return false;
    }

    private static boolean isVisit(int x, int y, boolean[][] check) {
        return check[y][x];
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}