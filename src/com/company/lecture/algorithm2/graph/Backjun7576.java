package com.company.lecture.algorithm2.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/7576
public class Backjun7576 {
    private static final String[] array = {
            "6 4\n" +
            "0 0 0 0 0 0\n" +
            "0 0 0 0 0 0\n" +
            "0 0 0 0 0 0\n" +
            "0 0 0 0 0 1",
            "6 4\n" +
            "0 -1 0 0 0 0\n" +
            "-1 0 0 0 0 0\n" +
            "0 0 0 0 0 0\n" +
            "0 0 0 0 0 1",
            "6 4\n" +
            "1 -1 0 0 0 0\n" +
            "0 -1 0 0 0 0\n" +
            "0 0 0 0 -1 0\n" +
            "0 0 0 0 -1 1",
            "5 5\n" +
            "-1 1 0 0 0\n" +
            "0 -1 -1 -1 0\n" +
            "0 -1 -1 -1 0\n" +
            "0 -1 -1 -1 0\n" +
            "0 0 0 0 0",
            "2 2\n" +
            "1 -1\n" +
            "-1 1"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] warehouse = new int[n][m];
        int[][] days = new int[n][m];
        Queue<Location> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer tomato = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                warehouse[i][j] = Integer.parseInt(tomato.nextToken());
                days[i][j] = -1;
                if (warehouse[i][j] == 1) {
                    q.add(new Location(j, i));
                    days[i][j] = 0;
                }
            }
        }

        bfs(days, warehouse, n, m, q);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (answer < days[i][j]) {
                    answer = days[i][j];
                }

                if (warehouse[i][j] == 0 && days[i][j] == -1) {
                    answer = -1;
                    break;
                }
            }

            if (answer == -1) {
                break;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static void bfs (int[][] days, int[][] warehouse, int n, int m, Queue<Location> q) {
        int[] xt = new int[]{1, 0, -1, 0};
        int[] yt = new int[]{0, 1, 0, -1};
        while (!q.isEmpty()) {
            Location l = q.poll();
            int x = l.x;
            int y = l.y;
            for (int i = 0; i < 4; i++) {
                if (0 <= x + xt[i] && x + xt[i] < m && 0 <= y + yt[i] && y + yt[i] < n) {
                    if (warehouse[y +yt[i]][x + xt[i]] == 0 && days[y + yt[i]][x + xt[i]] == -1) {
                        q.add(new Location(x + xt[i], y + yt[i]));
                        days[y + yt[i]][x + xt[i]] = days[y][x] + 1;
                    }
                }
            }
        }
    }

    private static class Location {
        int x;
        int y;
        public Location (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
