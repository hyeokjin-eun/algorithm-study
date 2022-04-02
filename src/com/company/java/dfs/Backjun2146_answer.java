package com.company.java.dfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2146
public class Backjun2146_answer {
    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};
    private static final String[] array = {
            "10\n" +
            "1 1 1 0 0 0 0 1 1 1\n" +
            "1 1 1 1 0 0 0 0 1 1\n" +
            "1 0 1 1 0 0 0 0 1 1\n" +
            "0 0 1 1 1 0 0 0 0 1\n" +
            "0 0 0 1 0 0 0 0 0 1\n" +
            "0 0 0 0 0 0 0 0 0 1\n" +
            "0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 1 1 0 0 0 0\n" +
            "0 0 0 0 1 1 1 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0"
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
        int n = Integer.parseInt(br.readLine());
        int[][] a = new int[n][n];
        int[][] d = new int[n][n];
        int[][] g = new int[n][n];
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (a[i][j] == 1 && g[i][j] == 0) {
                    Queue<Pair> q = new LinkedList<Pair>();
                    g[i][j] = ++cnt;
                    q.add(new Pair(i, j));
                    while (!q.isEmpty()) {
                        Pair p = q.remove();
                        int x = p.x;
                        int y = p.y;
                        for (int k=0; k<4; k++) {
                            int nx = x+dx[k];
                            int ny = y+dy[k];
                            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                                if (a[nx][ny] == 1 && g[nx][ny] == 0) {
                                    q.add(new Pair(nx, ny));
                                    g[nx][ny] = cnt;
                                }
                            }
                        }
                    }
                }
            }
        }
        Queue<Pair> q = new LinkedList<Pair>();
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                d[i][j] = -1;
                if (a[i][j] == 1) {
                    q.add(new Pair(i,j));
                    d[i][j] = 0;
                }
            }
        }
        while (!q.isEmpty()) {
            Pair p = q.remove();
            int x = p.x;
            int y = p.y;
            for (int k=0; k<4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (d[nx][ny] == -1) {
                        d[nx][ny] = d[x][y] + 1;
                        g[nx][ny] = g[x][y];
                        q.add(new Pair(nx,ny));
                    }
                }
            }
        }
        int ans = -1;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                for (int k=0; k<4; k++) {
                    int x = i+dx[k];
                    int y = j+dy[k];
                    if (0 <= x && x < n && 0 <= y && y < n) {
                        if (g[i][j] != g[x][y]) {
                            if (ans == -1 || ans > d[i][j] + d[x][y]) {
                                ans = d[i][j] + d[x][y];
                            }
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
