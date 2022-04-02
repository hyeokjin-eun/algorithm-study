package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16924
public class Backjun16924 {
    private static final String[] array = {
            "6 8\n" +
            "....*...\n" +
            "...**...\n" +
            "..*****.\n" +
            "...**...\n" +
            "....*...\n" +
            "........",
            "5 5\n" +
            ".*...\n" +
            "****.\n" +
            ".****\n" +
            "..**.\n" +
            ".....",
            "5 5\n" +
            ".*...\n" +
            "***..\n" +
            ".*...\n" +
            ".*...\n" +
            ".....",
            "3 3\n" +
            "*.*\n" +
            ".*.\n" +
            "*.*"
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
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] check = new boolean[N][M];
        char[][] a = new char[N][M];
        for (int i = 0; i < N; i++) {
            a[i] = br.readLine().toCharArray();
        }

        ArrayList<Pair> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (a[i][j] == '*') {
                    for (int k = 1;; k++) {
                        if (0 <= i - k && i + k < N && 0 <= j - k && j + k < M) {
                            if (a[i - k][j] == '*' && a[i + k][j] == '*' && a[i][j - k] == '*' && a[i][j + k] == '*') {
                                answer.add(new Pair(i + 1, j + 1, k));
                                check[i][j] = true;
                                check[i - k][j] = true;
                                check[i + k][j] = true;
                                check[i][j - k] = true;
                                check[i][j + k] = true;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        boolean is = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (a[i][j] == '*' && !check[i][j]) {
                    is = false;
                    break;
                }
            }
        }

        if (!is) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(answer.size()));
            bw.write("\n");
            for (int i = 0; i < answer.size(); i++) {
                bw.write(String.valueOf(answer.get(i).x));
                bw.write(" ");
                bw.write(String.valueOf(answer.get(i).y));
                bw.write(" ");
                bw.write(String.valueOf(answer.get(i).s));
                if (i != answer.size() - 1) {
                    bw.write("\n");
                }
            }
        }

        bw.flush();
    }

    private static class Pair {
        int x;
        int y;
        int s;
        public Pair(int x, int y, int s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }
    }
}
