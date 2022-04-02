package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16956
public class Backjun16956 {
    private static int R;
    private static int C;
    private static final String[] array = {
            "6 6\n" +
            "..S...\n" +
            "..S.W.\n" +
            ".S....\n" +
            "..W...\n" +
            "...W..\n" +
            "......",
            "1 2\n" +
            "SW",
            "TEST MADE"
    };

    public static void main (String[] args) throws IOException {
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

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
        char[][] a = new char[R][C];
        for (int i = 0; i < R; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                char t = c[j];
                if (t == '.') {
                    t = 'D';
                }

                a[i][j] = t;
            }
        }

        boolean ok = true;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (a[i][j] == 'W') {
                    for (int k = 0; k < 4; k++) {
                        int nx = j + xa[k];
                        int ny = i + ya[k];
                        if (nx < 0 || ny < 0 || R <= ny || C <= nx) {
                            continue;
                        }

                        if (a[ny][nx] == 'S') {
                            ok = false;
                            break;
                        }
                    }
                }

                if (!ok) {
                    break;
                }
            }

            if (!ok) {
                break;
            }
        }

        if (ok) {
            bw.write(String.valueOf(1));
            bw.write("\n");
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    bw.write(String.valueOf(a[i][j]));
                }

                if (i != R - 1) {
                    bw.write("\n");
                }
            }
        } else {
            bw.write(String.valueOf(0));
        }

        bw.flush();
    }

    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("500 500\n");
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                sb.append("S");
            }

            if (i != 499) {
                sb.append("\n");
            }
        }

        array[2] = sb.toString();
    }
}