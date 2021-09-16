package com.company.lecture.algorithm2.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2178
public class Backjun2178 {
    private static final String[] array = {
            "4 6\n" +
            "101111\n" +
            "101010\n" +
            "101011\n" +
            "111011",
            "4 6\n" +
            "110110\n" +
            "110110\n" +
            "111111\n" +
            "111101",
            "2 25\n" +
            "1011101110111011101110111\n" +
            "1110111011101110111011101",
            "7 7\n" +
            "1011111\n" +
            "1110001\n" +
            "1000001\n" +
            "1000001\n" +
            "1000001\n" +
            "1000001\n" +
            "1111111"
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
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] load = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(load[j]);
            }
        }

        int[][] street = new int[n][m];
        boolean[][] check = new boolean[n][m];
        int answer = bfs(a, street, check, n, m);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs (int[][] a, int[][] street, boolean[][] check, int n, int m) {
        Queue<Location> q = new LinkedList<>();
        q.add(new Location(0, 0));
        street[0][0] = 1;
        check[0][0] = true;
        int[] xt = new int[]{1, 0, -1, 0};
        int[] yt = new int[]{0, 1, 0, -1};
        while (!q.isEmpty()) {
            Location l = q.poll();
            int x = l.x;
            int y = l.y;
            for (int i = 0; i < 4; i++) {
                if (0 <= x + xt[i] && x + xt[i] < m && 0 <= y + yt[i] && y + yt[i] < n) {
                    if (a[y + yt[i]][x + xt[i]] == 1 && !check[y + yt[i]][x + xt[i]]) {
                        q.add(new Location(x + xt[i], y + yt[i]));
                        street[y + yt[i]][x + xt[i]] = street[y][x] + 1;
                        check[y + yt[i]][x + xt[i]] = true;
                    }
                }
            }
        }

        return street[n - 1][m - 1];
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


