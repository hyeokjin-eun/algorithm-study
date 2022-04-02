package com.company.java.divideandconquer;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2630
public class Backjun2630 {
    private static final String[] array = {
            "8\n" +
            "1 1 0 0 0 0 1 1\n" +
            "1 1 0 0 0 0 1 1\n" +
            "0 0 0 0 1 1 0 0\n" +
            "0 0 0 0 1 1 0 0\n" +
            "1 0 0 0 1 1 1 1\n" +
            "0 1 0 0 1 1 1 1\n" +
            "0 0 1 1 1 1 1 1\n" +
            "0 0 1 1 1 1 1 1"
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
        int N = Integer.parseInt(br.readLine());
        int[][] a = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] answer = new int[2];
        recursion(answer, a, 0, 0, N, N);
        bw.write(String.valueOf(answer[0]));
        bw.write("\n");
        bw.write(String.valueOf(answer[1]));
        bw.flush();
    }

    private static void recursion(int[] answer, int[][] a, int sx, int sy, int ex, int ey) {
        int num = a[sy][sx];
        boolean check = true;
        for (int i = sy; i < ey; i++) {
            for (int j = sx; j < ex; j++) {
                if (num != a[i][j]) {
                    check = false;
                    break;
                }
            }

            if (!check) break;
        }

        if (check) {
            if (num == 0) answer[0] += 1;
            else answer[1] += 1;
        } else {
            int sub = (ex - sx) / 2;
            recursion(answer, a, sx, sy, ex - sub, ey - sub);
            recursion(answer, a, ex - sub, sy, ex, ey - sub);
            recursion(answer, a, sx, ey - sub, ex - sub, ey);
            recursion(answer, a, ex - sub, ey - sub, ex, ey);
        }
    }
}
