package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16957
// TODO: 2021-11-16 그래프 알고리즘으로 한번더 풀기
public class Backjun16957 {
    private static final int[] xa = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
    private static final int[] ya = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
    private static int R;
    private static int C;
    private static int[][] a;
    private static int[][] c;
    private static int[][][] t;
    private static final String[] array = {
            "3 3\n" +
            "1 3 4\n" +
            "5 6 7\n" +
            "8 9 2",
            "1 6\n" +
            "10 20 3 4 5 6",
            "4 4\n" +
            "20 2 13 1\n" +
            "4 11 10 35\n" +
            "3 12 9 7\n" +
            "30 40 50 5"
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
        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
        a = new int[R][C];
        c = new int[R][C];
        t = new int[R][C][2];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                a[i][j] = stoi(st.nextToken());
                c[i][j] = 1;
                t[i][j][0] = -1;
                t[i][j][1] = -1;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int cx = j;
                int cy = i;
                while (true) {
                    int mx = cx;
                    int my = cy;
                    int min = a[my][mx];
                    boolean ok = true;
                    for (int k = 0; k < 8; k++) {
                        int nx = cx + xa[k];
                        int ny = cy + ya[k];
                        if (0 <= nx && 0 <= ny && nx < C && ny < R) {
                            if (a[ny][nx] < min) {
                                ok = false;
                                min = a[ny][nx];
                                mx = nx;
                                my = ny;
                            }
                        }
                    }

                    cx = mx;
                    cy = my;

                    if (t[my][mx][0] != -1 && t[my][mx][1] != -1) {
                        cx = t[my][mx][0];
                        cy = t[my][mx][1];
                        break;
                    }

                    if (ok) {
                        break;
                    }
                }

                c[i][j]--;
                c[cy][cx]++;
                t[i][j][0] = cx;
                t[i][j][1] = cy;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                bw.write(String.valueOf(c[i][j]));
                if (j != C - 1) {
                    bw.write(" ");
                }
            }

            if (i != R - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }


    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}