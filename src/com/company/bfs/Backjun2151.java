package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2151
public class Backjun2151 {
    private static int N;
    private static char[][] a;
    private static final String[] array = {
            "5\n" +
            "***#*\n" +
            "*.!.*\n" +
            "*!.!*\n" +
            "*.!.*\n" +
            "*#***",
            "TEST MADE"
    };

    public static void main (String[] args) throws IOException {
        // TEST
        test();
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
        N = stoi(br.readLine());
        a = new char[N][N];
        int sx = -1;
        int sy = -1;
        int ex = -1;
        int ey = -1;
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                a[i][j] = chars[j];
                if (a[i][j] == '#') {
                    if (sx == -1 && sy == -1) {
                        sx = j;
                        sy = i;
                    } else {
                        ex = j;
                        ey = i;
                    }
                }
            }
        }

        int answer = bfs(sx, sy, ex, ey);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static int bfs(int sx, int sy, int ex, int ey) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(sx);
        queue.offer(sy);
        boolean[][] check = new boolean[N][N];
        check[sy][sx] = true;
        int[][] dist = new int[N][N];
        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();
            if (x == ex && y == ey) {
                return dist[y][x] - 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + xa[i];
                int ny = y + ya[i];
                while (0 <= nx && 0 <= ny && nx < N && ny < N) {
                    if (check(nx, ny, check)) {
                        queue.offer(nx);
                        queue.offer(ny);
                        check[ny][nx] = true;
                        dist[ny][nx] = dist[y][x] + 1;
                    } else if (a[ny][nx] == '*') {
                        break;
                    }

                    nx += xa[i];
                    ny += ya[i];
                }
            }
        }

        return -1;
    }

    private static boolean check(int x, int y, boolean[][] check) {
        return !check[y][x] && (a[y][x] == '!' || a[y][x] == '#');
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("50\n");
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (i == 0 && j == 1) {
                    sb.append("#");
                } else if (i == 49 && j == 1) {
                    sb.append("#");
                } else if (i == 25 && 0 < j && j < 48) {
                    sb.append("*");
                } else if (i == 0 || j == 0 || i == 49 || j == 49) {
                    sb.append("*");
                } else {
                    sb.append("!");
                }
            }

            if (i != 49) {
                sb.append("\n");
            }
        }

        System.out.println(sb);
        array[1] = sb.toString();
    }
}