package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1034
public class Backjun1034 {
    private static int N;
    private static int M;
    private static int K;
    private static int[][] board;
    private static final String[] array = {
            "3 2\n" +
            "01\n" +
            "10\n" +
            "10\n" +
            "1",
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
        // Dump Data Setting
        N = 3;
        M = 3;
        board = new int[][]{
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}
        };

        // change Method Test
        change(0);
        assert board[0][0] == 0;
        assert board[1][0] == 1;
        assert board[2][0] == 0;

        change(2);
        assert board[0][2] == 0;
        assert board[1][2] == 1;
        assert board[2][2] == 0;

        // check Method Test
        assert !check(0);
        assert check(1);
        assert !check(2);

        // getCount Method Test
        assert getCount() == 1;

        // recursion Method Test
        K = 1;
        change(0);
        change(1);
        assert recursion(0) == 2;

        // TEST MADE Dump Data Setting
        StringBuilder sb = new StringBuilder();
        sb.append("50").append(" ").append("50\n");
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                sb.append("0");
            }

            sb.append("\n");
        }

        sb.append("1000");
        System.out.println(sb);
        array[1] = sb.toString();
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = chars[j] - '0';
            }
        }

        K = stoi(br.readLine());
        bw.write(String.valueOf(recursion(0)));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void change(int x) {
        for (int i = 0; i < N; i++) {
            board[i][x] = 1 - board[i][x];
        }
    }

    private static boolean check(int y) {
        for (int i = 0; i < M; i++) {
            if (board[y][i] != 1) {
                return false;
            }
        }

        return true;
    }

    private static int getCount() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            if (check(i)) {
                result++;
            }
        }

        return result;
    }

    private static int recursion(int count) {
        if (count == K) {
            return getCount();
        }

        int answer = 0;
        for (int i = 0; i < M; i++) {
            change(i);
            answer = Math.max(answer, recursion(count + 1));
            change(i);
        }

        return answer;
    }
}
