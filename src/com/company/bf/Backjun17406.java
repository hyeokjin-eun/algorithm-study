package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/17406
public class Backjun17406 {
    private static int N;
    private static int M;
    private static final String[] array = {
            "5 6 2\n" +
            "1 2 3 2 5 6\n" +
            "3 8 7 2 1 3\n" +
            "8 2 3 1 4 5\n" +
            "3 4 5 1 1 1\n" +
            "9 3 2 1 4 3\n" +
            "3 4 2\n" +
            "4 2 1"
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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] a = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Pair[] pairs = new Pair[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            pairs[i] = new Pair(r, c, s);
        }

        // bf start
        boolean[] check = new boolean[K];
        int answer = recursion(a, 0, check, pairs);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion(int[][] b, int s, boolean[] check, Pair[] pairs) {
        if (s == pairs.length) {
            int answer = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                int temp = 0;
                for (int k = 0; k < M; k++) {
                    temp += b[j][k];
                }

                answer = Math.min(temp, answer);
            }

            return answer;
        }


        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < pairs.length; i++) {
            if (!check[i]) {
                check[i] = true;
                rightRotation(b, pairs[i]);
                answer = Math.min(recursion(b, s + 1, check, pairs), answer);
                check[i] = false;
                leftRotation(b, pairs[i]);
            }
        }

        return answer;
    }

    private static void rightRotation(int[][] b, Pair pair) {
        int r = pair.r;
        int c = pair.c;
        int s = pair.s;
        for (int i = 1; i <= s; i++) {
            int temp = b[r - i][c - i];
            temp = moveX(r - i, c, i, temp, b, false);  // →
            temp = moveY(c + i, r, i, temp, b, false);  // ↓
            temp = moveX(r + i, c, i, temp, b, true);   // ←
            moveY(c - i, r, i, temp, b, true);          // ↑
        }
    }

    private static void leftRotation(int[][] b, Pair pair) {
        int r = pair.r;
        int c = pair.c;
        int s = pair.s;
        for (int i = 1; i <= s; i++) {
            int temp = b[r - i][c - i];
            temp = moveY(c - i, r, i, temp, b, false);  // ↓
            temp = moveX(r + i, c, i, temp, b, false);  // →
            temp = moveY(c + i, r, i, temp, b, true);   // ↑
            moveX(r - i, c, i, temp, b, true);          // ←
        }
    }

    private static int moveX(int y, int c, int i, int in, int[][] b, boolean f) {
        int temp = in;
        int sx = f ? c + i - 1 : c - i + 1;
        for (int x = sx; c - i <= x && x <= c + i; x += f ? -1 : 1) {
            int t = b[y][x];
            b[y][x] = temp;
            temp = t;
        }

        return temp;
    }

    private static int moveY(int x, int r, int i, int in, int[][] b, boolean f) {
        int temp = in;
        int sy = f ? r + i - 1 : r - i + 1;
        for (int y = sy; r - i <= y && y <= r + i; y += f ? -1 : 1) {
            int t = b[y][x];
            b[y][x] = temp;
            temp = t;
        }

        return temp;
    }

    private static class Pair {
        int r;
        int c;
        int s;
        public Pair(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}