package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1987
// TODO: 2021-10-12 BF로 풀었지만 BFS로 시간 단축 가능
public class Backjun1987 {
    private static final String[] array = {
            "2 4\n" +
            "CAAB\n" +
            "ADCB",
            "3 6\n" +
            "HFDFFB\n" +
            "AJHGDH\n" +
            "DGAGEH",
            "5 5\n" +
            "IEFCJ\n" +
            "FHFKC\n" +
            "FFALF\n" +
            "HFGCF\n" +
            "HMCHH"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        boolean[][] check = new boolean[R][C];
        boolean[] alpha = new boolean[26];
        check[0][0] = true;
        alpha[board[0][0] - 'A'] = true;
        int answer = recursion(0, 0, R, C, check, alpha, 1, board);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion(int x, int y, int R, int C, boolean[][] check, boolean[] alpha, int cnt, char[][] board) {
        int[] xa = new int[]{1, 0, -1, 0};
        int[] ya = new int[]{0, 1, 0, -1};
        int answer = cnt;
        for (int i = 0; i < 4; i++) {
            int nx = x + xa[i];
            int ny = y + ya[i];
            if (nx < 0 || C <= nx || ny < 0 || R <= ny) {
                continue;
            }

            if (alpha[board[ny][nx] - 'A']) {
                continue;
            }

            check[y][x] = true;
            alpha[board[ny][nx] - 'A'] = true;
            answer = Math.max(recursion(nx, ny, R, C, check, alpha, cnt + 1, board), answer);
            check[y][x] = false;
            alpha[board[ny][nx] - 'A'] = false;
        }

        return answer;
    }
}