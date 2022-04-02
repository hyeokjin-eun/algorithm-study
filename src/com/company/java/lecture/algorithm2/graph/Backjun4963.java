package com.company.java.lecture.algorithm2.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/4963
public class Backjun4963 {
    private static final String[] array = {
            "1 1\n" +
            "0\n" +
            "2 2\n" +
            "0 1\n" +
            "1 0\n" +
            "3 2\n" +
            "1 1 1\n" +
            "1 1 1\n" +
            "5 4\n" +
            "1 0 1 0 0\n" +
            "1 0 0 0 0\n" +
            "1 0 1 0 1\n" +
            "1 0 0 1 0\n" +
            "5 4\n" +
            "1 1 1 0 1\n" +
            "1 0 1 0 1\n" +
            "1 0 1 0 1\n" +
            "1 0 1 1 1\n" +
            "5 5\n" +
            "1 0 1 0 1\n" +
            "0 0 0 0 0\n" +
            "1 0 1 0 1\n" +
            "0 0 0 0 0\n" +
            "1 0 1 0 1\n" +
            "0 0"
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
        while (true) {
            StringTokenizer wh = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(wh.nextToken());
            int h = Integer.parseInt(wh.nextToken());
            if (w == 0 && h == 0) {
                break;
            }

            int[][] a = new int[h][w];
            for (int i = 0; i < h; i++) {
                StringTokenizer land = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    a[i][j] = Integer.parseInt(land.nextToken());
                }
            }

            int answer = 0;
            boolean[][] check = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (a[i][j] == 1 && !check[i][j]) {
                        dfs(j, i, w, h, check, a);
                        answer++;
                    }
                }
            }

            bw.write(String.valueOf(answer));
            bw.write("\n");
        }

        bw.flush();
    }

    private static void dfs (int x, int y, int w, int h, boolean[][] check, int[][] a) {
        check[y][x] = true;
        int[] xw = new int[]{1, 0, -1 ,0, 1, 1, -1, -1};
        int[] yh = new int[]{0, 1, 0, -1, -1, 1, 1, -1};
        for (int i = 0; i < 8; i++) {
            if (0 <= x + xw[i] && x + xw[i] < w && 0 <= y + yh[i] && y + yh[i] < h) {
                if (a[y + yh[i]][x + xw[i]] == 1 && !check[y + yh[i]][x + xw[i]]) {
                    dfs(x + xw[i], y + yh[i], w, h, check, a);
                }
            }
        }
    }
}
