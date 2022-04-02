package com.company.lecture.algorithm2.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16929
public class Backjun16929 {
    private static final String[] array = {
            "3 4\n" +
            "AAAA\n" +
            "ABCA\n" +
            "AAAA",
            "3 4\n" +
            "AAAA\n" +
            "ABCA\n" +
            "AADA",
            "4 4\n" +
            "YYYR\n" +
            "BYBY\n" +
            "BBBY\n" +
            "BBBY",
            "7 6\n" +
            "AAAAAB\n" +
            "ABBBAB\n" +
            "ABAAAB\n" +
            "ABABBB\n" +
            "ABAAAB\n" +
            "ABBBAB\n" +
            "AAAAAB",
            "2 13\n" +
            "ABCDEFGHIJKLM\n" +
            "NOPQRSTUVWXYZ"
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
        char[][] a = new char[n][m];
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                 a[i][j] = line[j];
            }
        }

        boolean answer = false;
        boolean[][] check = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (check[i][j]) {
                    continue;
                }

                answer = dfs(-1, -1, j, i, n, m, a, check);
                if (answer) {
                    break;
                }
            }

            if (answer) {
                break;
            }
        }

        bw.write(answer ? "Yes" : "No");
        bw.flush();
    }

    private static boolean dfs (int bx, int by, int x, int y, int n, int m, char[][] a, boolean[][] check) {
        if (check[y][x]) {
            return true;
        }

        check[y][x] = true;
        int[] xw = new int[]{1, 0, -1, 0};
        int[] yh = new int[]{0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int nx = x + xw[i];
            int ny = y + yh[i];
            if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                if (bx == nx && by == ny) {
                    continue;
                }

                if (a[ny][nx] == a[y][x]) {
                    if (dfs(x, y, nx, ny, n, m, a, check)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
