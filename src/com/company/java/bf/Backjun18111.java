package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/18111
public class Backjun18111 {
    private static int N;
    private static int M;
    private static int B;
    private static int[][] land;
    private static int answer = Integer.MAX_VALUE;
    private static int most = -1;
    private static final String[] array = {
            "3 4 99\n" +
            "0 0 0 0\n" +
            "0 0 0 0\n" +
            "0 0 0 1",
            "3 4 1\n" +
            "64 64 64 64\n" +
            "64 64 64 64\n" +
            "64 64 64 63",
            "3 4 0\n" +
            "64 64 64 64\n" +
            "64 64 64 64\n" +
            "64 64 64 63"
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
        B = stoi(st.nextToken());
        land = new int[N][M];
        int min = 0;
        int max = 0;
        answer = Integer.MAX_VALUE;
        most = -1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                land[i][j] = stoi(st.nextToken());
                min = Math.min(min, land[i][j]);
                max = Math.max(max, land[i][j]);
            }
        }

        bf(min, max);
        bw.write(String.valueOf(answer));
        bw.write(" ");
        bw.write(String.valueOf(most));
        bw.flush();
    }

    private static void bf(int min, int max) {
        for (int h = min; h <= max; h++) {
            int build = 0;
            int remove = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int height = land[i][j] - h;
                    if (height < 0) {
                        build -= height;
                    }

                    if (height > 0) {
                        remove += height;
                    }
                }
            }

            if (remove + B >= build) {
                if (answer >= remove * 2 + build) {
                    answer = remove * 2 + build;
                    most = h;
                }
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
