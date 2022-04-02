package com.company.java.lecture.algorithm2.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1261
public class Backjun1261 {
    private static final String[] array = {
            "3 3\n" +
            "011\n" +
            "111\n" +
            "110",
            "4 2\n" +
            "0001\n" +
            "1000",
            "6 6\n" +
            "001111\n" +
            "010000\n" +
            "001111\n" +
            "110001\n" +
            "011010\n" +
            "100010",
            "1 1\n" +
            "0",
            "3 5\n" +
            "001\n" +
            "101\n" +
            "001\n" +
            "011\n" +
            "000"
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
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] maze = new int[m][n];
        int[][] cnt = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] temp = br.readLine().split("");
            Arrays.fill(cnt[i], -1);
            for (int j = 0; j < n; j++) {
                maze[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int answer = bfs(n, m, maze, cnt);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int n, int m, int[][] maze, int[][] cnt) {
        Queue<Location> q = new LinkedList<>();
        Queue<Location> nq = new LinkedList<>();
        q.add(new Location(0, 0));
        cnt[0][0] = 0;
        int[] xt = new int[]{1, 0, -1, 0};
        int[] yt = new int[]{0, 1, 0, -1};
        while (!q.isEmpty()) {
            Location l = q.poll();
            int x = l.x;
            int y = l.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + xt[i];
                int ny = y + yt[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (cnt[ny][nx] == -1) {
                        if (maze[ny][nx] == 1) {
                            nq.add(new Location(nx, ny));
                            cnt[ny][nx] = cnt[y][x] + 1;
                        } else {
                            q.add(new Location(nx, ny));
                            cnt[ny][nx] = cnt[y][x];
                        }
                    }
                }
            }

            if (q.isEmpty()) {
                q = nq;
                nq = new LinkedList<>();
            }
        }

        return cnt[m - 1][n - 1];
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
