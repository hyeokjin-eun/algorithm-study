package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/4902
public class Backjun4902 {
    private static int answer;
    private static int N;
    private static final String[] array = {
            "3 6 -24 0 12 -10 12 40 -4 6\n" +
            "4 1 1 -1 1 1 -1 1 -1 1 1 -1 1 -1 1 -1 1\n" +
            "0"
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
        for (int T = 0;; T++) {
            String t = br.readLine();
            if (t.equals("0")) {
                break;
            }

            StringTokenizer st = new StringTokenizer(t);
            N = Integer.parseInt(st.nextToken());
            int[][] a = new int[N + 1][N * 2 + 1];
            int[][] s = new int[N + 1][N * 2 + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= 2 * i - 1; j++) {
                    a[i][j] = Integer.parseInt(st.nextToken());
                    s[i][j] = s[i][j - 1] + a[i][j];
                }
            }

            answer = Integer.MIN_VALUE;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= 2 * i - 1; j++) {
                    calc(i, j, j, 0, s);
                }
            }

            bw.write((T + 1) + ". ");
            bw.write(String.valueOf(answer));
            bw.write("\n");
            bw.flush();
        }
    }

    private static void calc(int row, int left, int right, int sum, int[][] s) {
        if (row < 1 || row > N) {
            return;
        }

        if (left < 1 || right > 2 * row - 1) {
            return;
        }

        sum += s[row][right] - s[row][left - 1];
        if (sum > answer){
            answer = sum;
        }

        if (left % 2 == 0) {
            calc(row - 1, left - 2, right, sum, s);
        } else {
            calc(row + 1, left, right + 2, sum, s);
        }
    }
}
