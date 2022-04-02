package com.company.java.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2873
public class Backjun2873 {
    private static final String[] array = {
            "3 3\n" +
            "5 1 3\n" +
            "2 4 8\n" +
            "1 1 2",
            "2 3\n" +
            "2 2 2\n" +
            "3 3 3",
            "3 2\n" +
            "3 2\n" +
            "2 3\n" +
            "3 2",
            "2 2\n" +
            "2 1\n" +
            "3 4",
            "6 6\n" +
            "2 2 3 3 5 5\n" +
            "2 2 2 2 5 5\n" +
            "3 3 1 2 2 2\n" +
            "4 4 4 4 4 4\n" +
            "2 2 2 2 2 2\n" +
            "6 6 6 6 6 6"
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
        // 행
        int Y = Integer.parseInt(st.nextToken());
        // 열
        int X = Integer.parseInt(st.nextToken());
        StringBuilder answer;
        if (Y % 2 == 1) {
            StringBuilder sb = new StringBuilder();
            int toggle = 0;
            for (int i = 0; i < Y; i++) {
                if (toggle == 0) {
                    append(sb, X - 1,"R");
                    toggle = 1 - toggle;
                } else {
                    append(sb, X - 1,"L");
                    toggle = 1 - toggle;
                }

                if (i != Y - 1) {
                    append(sb, 1, "D");
                }
            }

            answer = sb;
        } else if (X % 2 == 1) {
            StringBuilder sb = new StringBuilder();
            int toggle = 0;
            for (int i = 0; i < X; i++) {
                if (toggle == 0) {
                    append(sb, Y - 1,"D");
                    toggle = 1 - toggle;
                } else {
                    append(sb, Y - 1,"U");
                    toggle = 1 - toggle;
                }

                if (i != X - 1) {
                    append(sb, 1, "R");
                }
            }

            answer = sb;
        } else {
            int[][] num = new int[Y][X];
            StringBuilder sb = new StringBuilder();
            int x = 0;
            int y = 1;
            int max = Integer.MAX_VALUE;
            for (int i = 0; i < Y; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < X; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    num[i][j] = n;
                }
            }

            for (int i = 0; i < Y; i++) {
                for (int j = 0; j < X; j++) {
                    if ((i + j) % 2 == 1) {
                        if (num[y][x] > num[i][j]) {
                            x = j;
                            y = i;
                        }
                    }
                }
            }

            int sx = 0;
            int sy = 0;
            int ex = X - 1;
            int ey = Y - 1;
            StringBuilder s = new StringBuilder();
            StringBuilder e = new StringBuilder();
            while ((sy != y && sy + 1 != y) || (ey != y && ey - 1 != y)) {
                if (sy != y && sy + 1 != y) {
                    append(s, X - 1, "R");
                    append(s, 1, "D");
                    append(s, X - 1, "L");
                    append(s, 1, "D");
                    sy += 2;
                }

                if (ey != y && ey - 1 != y) {
                    append(e, X - 1, "R");
                    append(e, 1, "D");
                    append(e, X - 1, "L");
                    append(e, 1, "D");
                    ey -= 2;
                }
            }

            while ((sx != x && sx + 1 != x) || (ex != x && ex - 1 != x)) {
                if (sx != x && sx + 1 != x) {
                    append(s, 1, "D");
                    append(s, 1, "R");
                    append(s, 1, "U");
                    append(s, 1, "R");
                    sx += 2;
                }

                if (ex != x && ex - 1 != x) {
                    append(e, 1, "D");
                    append(e, 1, "R");
                    append(e, 1, "U");
                    append(e, 1, "R");
                    ex -= 2;
                }
            }

            if (sx == x) {
                append(s, 1, "R");
                append(s, 1, "D");
            } else {
                append(s, 1, "D");
                append(s, 1, "R");
            }

            e.reverse();
            s.append(e);
            answer = s;
        }

        bw.write(answer.toString());
        bw.flush();
    }

    private static void append(StringBuilder sb, int n, String s) {
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
    }
}