package com.company.implement;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2775
public class Backjun2775 {
    private static final String[] array = {
            "3\n" +
            "1\n" +
            "3\n" +
            "2\n" +
            "3\n" +
            "14\n" +
            "14"
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
        int T = stoi(br.readLine());
        int[][] apartment = create();
        for (int t = 0; t < T; t++) {
            int K = stoi(br.readLine());
            int N = stoi(br.readLine());
            bw.write(String.valueOf(apartment[K][N]));
            if (t != T - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int[][] create() {
        int height = 14;
        int width = 14;
        int[][] apartment = new int[height + 1][width + 1];
        set(height, width, apartment);
        return apartment;
    }

    private static void set(int K, int N, int[][] apartment) {
        for (int i = 0; i <= N; i++) {
            apartment[0][i] = i;
        }

        for (int k = 1; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                apartment[k][n] = apartment[k][n - 1] + apartment[k - 1][n];
            }
        }
    }

    private static int stoi (String s) {
        return Integer.parseInt(s);
    }
}