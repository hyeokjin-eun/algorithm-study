package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1012
public class Backjun1012 {
    private static final String[] array = {
            "2\n" +
            "10 8 17\n" +
            "0 0\n" +
            "1 0\n" +
            "1 1\n" +
            "4 2\n" +
            "4 3\n" +
            "4 5\n" +
            "2 4\n" +
            "3 4\n" +
            "7 4\n" +
            "8 4\n" +
            "9 4\n" +
            "7 5\n" +
            "8 5\n" +
            "9 5\n" +
            "7 6\n" +
            "8 6\n" +
            "9 6\n" +
            "10 10 1\n" +
            "5 5",
            "1\n" +
            "5 3 6\n" +
            "0 2\n" +
            "1 2\n" +
            "2 2\n" +
            "3 2\n" +
            "4 2\n" +
            "4 0"
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
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[][] a = new int[n][m];
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                a[y][x] = 1;
            }

            boolean[][] check = new boolean[n][m];
            int answer = 0;
            for (int j = 0; j < n; j++) {
                for (int s = 0; s < m; s++) {
                    if (!check[j][s] && a[j][s] == 1) {
                        bfs(s, j, m, n, a, check);
                        answer++;
                    }
                }
            }

            bw.write(String.valueOf(answer));
            if (i != t - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static void bfs(int x, int y, int m, int n, int[][] a, boolean[][] check) {
        Queue<Location> q = new LinkedList<>();
        q.add(new Location(x, y));
        check[y][x] = true;
        int[] xa = new int[]{1, 0, -1, 0};
        int[] ya = new int[]{0, 1, 0, -1};
        while (!q.isEmpty()) {
            Location l = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = l.x + xa[i];
                int ny = l.y + ya[i];
                if (nx < 0 || ny < 0 || m <= nx || n <= ny) {
                    continue;
                }

                if (!check[ny][nx] && a[ny][nx] == 1) {
                    q.add(new Location(nx, ny));
                    check[ny][nx] = true;
                }
            }
        }
    }

    private static class Location {
        int x;
        int y;
        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
