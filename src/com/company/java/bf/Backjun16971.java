package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16971
public class Backjun16971 {
    private static int N;
    private static int M;
    private static int[][] A;
    private static final String[] array = {
            "3 3\n" +
            "9 8 7\n" +
            "3 2 1\n" +
            "6 5 4",
            "3 4\n" +
            "1 2 1 1\n" +
            "2 1 1 2\n" +
            "2 1 1 1",
            "3 3\n" +
            "1 1 1\n" +
            "1 2 1\n" +
            "1 1 1"
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
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = stoi(st.nextToken());
            }
        }

        int answer = getB();
        if (2 < N) {
            for (int i = 1; i < N - 1; i++) {
                change(true, 0, i);
                answer = Math.max(answer, getB());
                change(true, 0, i);
            }

            for (int i = N - 2; 0 <= i; i--) {
                change(true, N - 1, i);
                answer = Math.max(answer, getB());
                change(true, N - 1, i);
            }
        }

        if (2 < M) {
            for (int i = 1; i < M - 1; i++) {
                change(false, 0, i);
                answer = Math.max(answer, getB());
                change(false, 0, i);
            }

            for (int i = M - 2; 0 <= i; i--) {
                change(false, M - 1, i);
                answer = Math.max(answer, getB());
                change(false, M - 1, i);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int getB() {
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                sum += A[i][j] + A[i + 1][j] + A[i + 1][j + 1] + A[i][j + 1];
            }
        }

        return sum;
    }

    private static void change(boolean f, int n1, int n2) {
        if (f) {
            // 행 교환
            int[] temp = A[n1];
            A[n1] = A[n2];
            A[n2] = temp;
        } else {
            // 열 교환
            for (int i = 0; i < N; i++) {
                int temp = A[i][n1];
                A[i][n1] = A[i][n2];
                A[i][n2] = temp;
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}