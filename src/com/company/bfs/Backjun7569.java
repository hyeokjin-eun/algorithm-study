package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/7569
public class Backjun7569 {
    // 가로
    private static int M;
    // 세로
    private static int N;
    // 높이
    private static int H;
    // 창고
    private static int[][][] storage;
    // queue
    private static Queue<Point> queue;
    // time
    private static int[][][] time;

    private static final String[] array = {
            "5 3 2\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 1 0 0\n" +
            "0 0 0 0 0",
            "5 3 1\n" +
            "0 -1 0 0 0\n" +
            "-1 -1 0 1 1\n" +
            "0 0 0 1 1",
            "4 3 2\n" +
            "1 1 1 1\n" +
            "1 1 1 1\n" +
            "1 1 1 1\n" +
            "1 1 1 1\n" +
            "-1 -1 -1 -1\n" +
            "1 1 1 -1"
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
        M = stoi(st.nextToken());
        N = stoi(st.nextToken());
        H = stoi(st.nextToken());
        storage = new int[H][N][M];
        queue = new LinkedList<>();
        time = new int[H][N][M];
        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < M; x++) {
                    storage[z][y][x] = stoi(st.nextToken());
                    if (storage[z][y][x] == 1) {
                        queue.add(new Point(x, y, z));
                        time[z][y][x] = 0;
                    } else {
                        time[z][y][x] = -1;
                    }
                }
            }
        }

        int answer = tomatoRipe();
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int[] px = new int[]{0, 0, 0, 0, -1, 1};
    private static int[] py = new int[]{0, 0, -1, 1, 0, 0};
    private static int[] pz = new int[]{1, -1, 0, 0, 0, 0};

    // bfs algorithm use
    private static int tomatoRipe() {
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            for (int i = 0; i < 6; i++) {
                int nx = point.x + px[i];
                int ny = point.y + py[i];
                int nz = point.z + pz[i];
                if (nz < 0 || H <= nz || ny < 0 || N <= ny || nx < 0 || M <= nx) {
                    continue;
                }

                if (time[nz][ny][nx] == -1 && storage[nz][ny][nx] == 0) {
                    queue.add(new Point(nx, ny, nz));
                    time[nz][ny][nx] = time[point.z][point.y][point.x] + 1;
                }
            }
        }

        int max = 0;
        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (time[z][y][x] == -1 && storage[z][y][x] == 0) {
                        return -1;
                    }

                    if (storage[z][y][x] == 0) {
                        max = Math.max(max, time[z][y][x]);
                    }
                }
            }
        }

        return max;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static class Point {
        int z;
        int y;
        int x;
        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}