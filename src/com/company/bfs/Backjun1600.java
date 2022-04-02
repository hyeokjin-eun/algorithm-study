package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1600
public class Backjun1600 {
    private static int K;
    private static int H;
    private static int W;
    private static int[][] a;
    private static StringTokenizer st;
    private static final String[] array = {
            "1\n" +
            "4 4\n" +
            "0 0 0 0\n" +
            "1 0 0 0\n" +
            "0 0 1 0\n" +
            "0 1 0 0",
            "2\n" +
            "5 2\n" +
            "0 0 1 1 0\n" +
            "0 0 1 1 0",
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
        K = stoi(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = stoi(st.nextToken());
        H = stoi(st.nextToken());
        a = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                a[i][j] = stoi(st.nextToken());
            }
        }

        bw.write(String.valueOf(bfs()));
        bw.flush();
    }

    private static int[] xa = new int[]{1, 0, -1, 0, 1, 2, 2, 1, -1, -2, -2, -1};
    private static int[] ya = new int[]{0, 1, 0, -1, -2, -1, 1, 2, 2, 1, -1, -2};
    private static int[] co = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1};
    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        queue.offer(0);
        queue.offer(0);
        boolean[][][] check = new boolean[K + 1][H][W];
        check[0][0][0] = true;
        int[][][] dist = new int[K + 1][H][W];
        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();
            int k = queue.poll();

            for (int i = 0; i < 12; i++) {
                int nx = x + xa[i];
                int ny = y + ya[i];
                int nk = k + co[i];
                if (arrayOverCheck(nx, ny, nk)) {
                    continue;
                }

                if (!check[nk][ny][nx] && a[ny][nx] == 0) {
                    queue.offer(nx);
                    queue.offer(ny);
                    queue.offer(nk);
                    check[nk][ny][nx] = true;
                    dist[nk][ny][nx] = dist[k][y][x] + 1;
                }
            }
        }

        int answer = -1;
        for (int i = 0 ; i <= K; i++) {
            if (!check[i][H - 1][W - 1]) {
                continue;
            }

            if (answer == -1 || dist[i][H - 1][W - 1] < answer) {
                answer = dist[i][H - 1][W - 1];
            }
        }

        return answer;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static boolean arrayOverCheck(int x, int y, int k) {
        return (x < 0 || y < 0 || W <= x || H <= y || k < 0 || K < k);
    }

    private static void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("30\n");
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