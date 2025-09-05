package com.company.example;

import java.io.*;
import java.util.*;

public class Backjun4963 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int w = stoi(st.nextToken());
            int h = stoi(st.nextToken());
            if (w == 0 && h == 0) {
                break;
            }

            int[][] land = new int[w][h];
            boolean[][] visited = new boolean[w][h];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < w; j++) {
                    land[j][i] = stoi(st.nextToken());
                    visited[j][i] = false;
                }
            }

            int count = 0;
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    if (land[i][j] == 1 && !visited[i][j]) {
                        count++;
                        bfs(w, h, i, j, land, visited);
                    }
                }
            }

            out.write(count + "\n");
            out.flush();
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    public static int[] xa = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
    public static int[] ya = new int[]{0, 1, 0, -1, 1, 1, -1, -1};

    private static void bfs(int w, int h, int x, int y, int[][] land, boolean[][] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        queue.add(y);
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int cx = queue.poll();
            int cy = queue.poll();

            for (int i = 0; i < 8; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];

                if (isOutOfIndex(w, h, nx, ny)) {
                    continue;
                }

                if (isNotLand(nx, ny, land, visited)) {
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(nx);
                queue.add(ny);
            }
        }
    }

    private static boolean isOutOfIndex(int w, int h, int x, int y) {
        return x < 0 || x >= w || y < 0 || y >= h;
    }

    private static boolean isNotLand(int x, int y, int[][] land, boolean[][] visited) {
        return land[x][y] == 0 || visited[x][y];
    }
}
