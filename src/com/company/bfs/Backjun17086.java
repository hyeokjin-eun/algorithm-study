package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/17086
public class Backjun17086 {
    private static int N;
    private static int M;
    private static int[][] a;
    private static final String[] array = {
            "5 4\n" +
            "0 0 1 0\n" +
            "0 0 0 0\n" +
            "1 0 0 0\n" +
            "0 0 0 0\n" +
            "0 0 0 1",
            "7 4\n" +
            "0 0 0 1\n" +
            "0 1 0 0\n" +
            "0 0 0 0\n" +
            "0 0 0 1\n" +
            "0 0 0 0\n" +
            "0 1 0 0\n" +
            "0 0 0 1",
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        a = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                a[i][j] = stoi(st.nextToken());
            }
        }

        int answer = bfs();
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int[] xa = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    private static int[] ya = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[][] check = new boolean[N][M];
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (a[i][j] == 1) {
                    queue.offer(j);
                    queue.offer(i);
                    check[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();
            for (int i = 0; i < 8; i++) {
                int nx = x + xa[i];
                int ny = y + ya[i];
                if (nx < 0 || ny < 0 || M <= nx || N <= ny) {
                    continue;
                }

                if (!check[ny][nx]) {
                    queue.offer(nx);
                    queue.offer(ny);
                    check[ny][nx] = true;
                    dist[ny][nx] = dist[y][x] + 1;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = Math.max(answer, dist[i][j]);
            }
        }

        return answer;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("200 200\n");
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                sb.append("0");
                if (j != 199) {
                    sb.append(" ");
                }
            }

            if (i != 199) {
                sb.append("\n");
            }
        }

        array[2] = sb.toString();
    }
}