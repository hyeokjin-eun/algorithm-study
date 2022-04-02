package com.company.java.dfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/9376
public class Backjun9376 {
    private static char[][] a;
    private static int H;
    private static int W;
    private static final String[] array = {
            "3\n" +
            "5 9\n" +
            "****#****\n" +
            "*..#.#..*\n" +
            "****.****\n" +
            "*$#.#.#$*\n" +
            "*********\n" +
            "5 11\n" +
            "*#*********\n" +
            "*$*...*...*\n" +
            "*$*.*.*.*.*\n" +
            "*...*...*.*\n" +
            "*********.*\n" +
            "9 9\n" +
            "*#**#**#*\n" +
            "*#**#**#*\n" +
            "*#**#**#*\n" +
            "*#**.**#*\n" +
            "*#*#.#*#*\n" +
            "*$##*##$*\n" +
            "*#*****#*\n" +
            "*.#.#.#.*\n" +
            "*********"
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
        int T = stoi(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = stoi(st.nextToken());
            W = stoi(st.nextToken());
            a = new char[H + 2][W + 2];
            int x1, y1, x2, y2;
            x1 = y1 = x2 = y2 = -1;
            for (int i = 0; i < H + 2; i++) {
                char[] chars = new char[H + 1];
                if (i != 0 && i != H + 1) {
                    chars = br.readLine().toCharArray();
                }

                for (int j = 0; j < W + 2; j++) {
                    if (i == 0 || j == 0 || i == H + 1 || j == W + 1) {
                        a[i][j] = '.';
                    } else {
                        a[i][j] = chars[j - 1];
                        if (a[i][j] == '$') {
                            if (x1 == -1 && y1 == -1) {
                                x1 = j;
                                y1 = i;
                            } else {
                                x2 = j;
                                y2 = i;
                            }
                        }
                    }
                }
            }

            int[][] d0 = bfs(0, 0);
            int[][] d1 = bfs(x1, y1);
            int[][] d2 = bfs(x2, y2);
            int answer = (H + 2) * (W + 2);
            for (int i = 0; i < H + 2; i++) {
                for (int j = 0; j < W + 2; j++) {
                    if (a[i][j] == '*') {
                        continue;
                    }

                    if (d0[i][j] == -1 || d1[i][j] == -1 || d2[i][j] == -1) {
                        continue;
                    }

                    int cur = d0[i][j] + d1[i][j] + d2[i][j];
                    if (a[i][j] == '#') {
                        cur -= 2;
                    }

                    answer = Math.min(answer, cur);
                }
            }

            bw.write(String.valueOf(answer));
            if (t != T - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int[] xa = new int[]{1, 0, -1, 0};
    private static int[] ya = new int[]{0, 1, 0, -1};
    private static int[][] bfs(int x, int y) {
        int[][] dist = new int[H + 2][W + 2];
        for (int i = 0; i < H + 2; i++) {
            Arrays.fill(dist[i], -1);
        }

        ArrayDeque<Pair> deque = new ArrayDeque<>();
        deque.add(new Pair(x, y));
        dist[y][x] = 0;
        while (!deque.isEmpty()) {
            Pair pair = deque.pollFirst();
            for (int i = 0 ; i < 4; i++) {
                int nx = pair.x + xa[i];
                int ny = pair.y + ya[i];
                if (nx < 0 || ny < 0 || H + 2 <= ny || W + 2 <= nx) {
                    continue;
                }

                if (dist[ny][nx] != -1) {
                    continue;
                }

                if (a[ny][nx] == '*') {
                    continue;
                }

                if (a[ny][nx] == '#') {
                    dist[ny][nx] = dist[pair.y][pair.x] + 1;
                    deque.addLast(new Pair(nx, ny));
                } else {
                    dist[ny][nx] = dist[pair.y][pair.x];
                    deque.addFirst(new Pair(nx, ny));
                }
            }
        }

        return dist;
    }
}