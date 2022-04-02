package com.company.bf;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1034
public class Backjun1034_2 {
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

        // count Method Test
        assert count(board[0]) == 1;
        assert count(board[1]) == 2;

        // same Method Test
        assert same(board[0]) == 2;
        assert same(board[1]) == 1;

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
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int zero = count(board[i]);
            if (zero <= K && K % 2 == zero % 2) {
                answer = Math.max(same(board[i]), answer);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static int count(int[] index) {
        int zero = 0;
        for (int i = 0; i < index.length; i++) {
            if (index[i] == 0) {
                zero++;
            }
        }

        return zero;
    }

    private static int same(int[] a) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (Arrays.equals(a, board[i])) {
                cnt++;
            }
        }

        return cnt;
    }
}