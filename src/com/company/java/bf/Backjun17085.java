package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/17085
public class Backjun17085 {
    private static int N;
    private static int M;
    private static final String[] array = {
            "5 6\n" +
            "######\n" +
            "#...#.\n" +
            "######\n" +
            "##..#.\n" +
            "######",
            "6 6\n" +
            ".#..#.\n" +
            "######\n" +
            ".#..#.\n" +
            "######\n" +
            ".#..#.\n" +
            ".#..#.",
            "5 8\n" +
            "..#..#..\n" +
            "..#..#..\n" +
            "########\n" +
            "..#..#..\n" +
            "..#..#.."
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

    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] a = new char[N][M];
        for (int i = 0; i < N; i++) {
            a[i] = br.readLine().toCharArray();
        }

        int answer = 0;
        for (int y1 = 0; y1 < N; y1++) {
            for (int x1 = 0; x1 < M; x1++) {
                if (a[y1][x1] != '#') {
                    continue;
                }

                for (int r1 = 0;; r1++) {
                    boolean ok1 = true;
                    for (int i = 0; i < 4; i++) {
                        int nx1 = x1 + (xa[i] * r1);
                        int ny1 = y1 + (ya[i] * r1);
                        if (nx1 < 0 || ny1 < 0 || M <= nx1 || N <= ny1) {
                            ok1 = false;
                            break;
                        }

                        if (a[ny1][nx1] != '#') {
                            ok1 = false;
                            break;
                        }
                    }

                    if (!ok1) {
                        break;
                    }

                    for (int i = 0; i < 4; i++) {
                        int nx1 = x1 + (xa[i] * r1);
                        int ny1 = y1 + (ya[i] * r1);
                        a[ny1][nx1] = '*';
                    }

                    for (int y2 = 0; y2 < N; y2++) {
                        for (int x2 = 0; x2 < M; x2++) {
                            if (a[y2][x2] != '#') {
                                continue;
                            }

                            for (int r2 = 0;; r2++) {
                                boolean ok2 = true;
                                for (int j = 0; j < 4; j++) {
                                    int nx2 = x2 + (xa[j] * r2);
                                    int ny2 = y2 + (ya[j] * r2);
                                    if (nx2 < 0 || ny2 < 0 || M <= nx2 || N <= ny2) {
                                        ok2 = false;
                                        break;
                                    }

                                    if (a[ny2][nx2] != '#') {
                                        ok2 = false;
                                        break;
                                    }
                                }

                                if (!ok2) {
                                    break;
                                }

                                answer = Math.max(answer, (4 * r1 + 1) * (4 * r2 + 1));
                            }
                        }
                    }
                }

                for (int r1 = 0;; r1++) {
                    boolean ok1 = true;
                    for (int i = 0; i < 4; i++) {
                        int nx1 = x1 + (xa[i] * r1);
                        int ny1 = y1 + (ya[i] * r1);
                        if (nx1 < 0 || ny1 < 0 || M <= nx1 || N <= ny1) {
                            ok1 = false;
                            break;
                        }

                        if (a[ny1][nx1] != '*') {
                            ok1 = false;
                            break;
                        }
                    }

                    if (!ok1) {
                        break;
                    }

                    for (int i = 0; i < 4; i++) {
                        int nx1 = x1 + (xa[i] * r1);
                        int ny1 = y1 + (ya[i] * r1);
                        a[ny1][nx1] = '#';
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
