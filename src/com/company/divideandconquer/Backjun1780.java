package com.company.divideandconquer;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1780
public class Backjun1780 {
    private static final String[] array = {
            "9\n" +
            "0 0 0 1 1 1 -1 -1 -1\n" +
            "0 0 0 1 1 1 -1 -1 -1\n" +
            "0 0 0 1 1 1 -1 -1 -1\n" +
            "1 1 1 0 0 0 0 0 0\n" +
            "1 1 1 0 0 0 0 0 0\n" +
            "1 1 1 0 0 0 0 0 0\n" +
            "0 1 -1 0 1 -1 0 1 -1\n" +
            "0 -1 1 0 1 -1 0 1 -1\n" +
            "0 1 -1 1 0 -1 0 1 -1"
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

        int[] answer = new int[3];
        solve(answer, a, 0, 0, N, N, N);
        for (int i = 0; i < 3; i++) {
            bw.write(String.valueOf(answer[i]));
            if (i != 2) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static void solve(int[] answer, int[][] a, int sx, int sy, int ex, int ey, int n) {
        int num = a[sy][sx];
        boolean check = true;
        for (int i = sy; i < ey; i++) {
            for (int j = sx; j < ex; j++) {
                if (num != a[i][j]) {
                    check = false;
                    break;
                }
            }

            if (!check) {
                break;
            }
        }

        if (check) {
            if (num == -1) {
                answer[0] += 1;
            } else if (num == 0) {
                answer[1] += 1;
            } else {
                answer[2] += 1;
            }
        } else {
            int temp = n / 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    solve(answer, a, sx + i * temp, sy + j * temp, sx + i * temp + temp, sy + j * temp + temp, temp);
                }
            }
        }
    }
}
