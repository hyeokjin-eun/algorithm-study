package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14620
public class Backjun14620 {
    private static int N;
    private static int[][] board;
    private static boolean[][] check;
    private static final String[] array = {
            "6\n" +
            "1 0 2 3 3 4\n" +
            "1 1 1 1 1 1\n" +
            "0 0 1 1 1 1\n" +
            "3 9 9 0 1 99\n" +
            "9 11 3 1 0 3\n" +
            "12 3 0 0 0 1",
            "TEST MADE"
    };

    public static void main(String[] args) throws IOException {
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

    private static void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("10").append("\n");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                sb.append("0");

                if (j != 9) {
                    sb.append(" ");
                }
            }

            if (i != 9) {
                sb.append("\n");
            }
        }

        array[1] = sb.toString();
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        N = stoi(br.readLine());
        board = new int[N][N];
        check = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = stoi(st.nextToken());
            }
        }

        bw.write(String.valueOf(recursion(0, 0)));
        bw.flush();
    }

    private static final int[] xp = new int[]{0, 0, 1, 0, -1};
    private static final int[] yp = new int[]{0, -1, 0, 1, 0};

    private static int recursion(int select, int sum) {
        if (select == 3) {
            return sum;
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (!check(j, i)) {
                    continue;
                }

                use(j, i, true);
                answer = Math.min(recursion(select + 1, sum + sum(j, i)), answer);
                use(j, i, false);
            }
        }

        return answer;
    }

    private static boolean check(int x, int y) {
        for (int i = 0; i < 5; i++) {
            int nx = x + xp[i];
            int ny = y + yp[i];
            if (check[ny][nx]) {
                return false;
            }
        }

        return true;
    }

    private static int sum(int x, int y) {
        int temp = 0;
        for (int i = 0; i < 5; i++) {
            int nx = x + xp[i];
            int ny = y + yp[i];
            temp += board[ny][nx];
        }

        return temp;
    }

    private static void use(int x, int y, boolean f) {
        for (int i = 0; i < 5; i++) {
            int nx = x + xp[i];
            int ny = y + yp[i];
            check[ny][nx] = f;
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
