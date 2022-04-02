package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/4574
public class Backjun4574 {
    private static final String[] array = {
            "10\n" +
            "6 B2 1 B3\n" +
            "2 C4 9 C3\n" +
            "6 D3 8 E3\n" +
            "7 E1 4 F1\n" +
            "8 B7 4 B8\n" +
            "3 F5 2 F6\n" +
            "7 F7 6 F8\n" +
            "5 G4 9 G5\n" +
            "7 I8 8 I9\n" +
            "7 C9 2 B9\n" +
            "C5 A3 D9 I4 A9 E5 A2 C6 I1\n" +
            "11\n" +
            "5 I9 2 H9\n" +
            "6 A5 7 A6\n" +
            "4 B8 6 C8\n" +
            "3 B5 8 B4\n" +
            "3 C3 2 D3\n" +
            "9 D2 8 E2\n" +
            "3 G2 5 H2\n" +
            "1 A2 8 A1\n" +
            "1 H8 3 I8\n" +
            "8 I3 7 I4\n" +
            "4 I6 9 I7\n" +
            "I5 E6 D1 F2 B3 G9 H7 C9 E5\n" +
            "0"
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
        int index = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            int[][] board = new int[9][9];
            boolean[][] domino = new boolean[10][10];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                char[] LU = st.nextToken().toCharArray();
                int V = Integer.parseInt(st.nextToken());
                char[] LV = st.nextToken().toCharArray();
                domino[U][V] = true;
                domino[V][U] = true;
                board[LU[0] - 'A'][LU[1] - '1'] = U;
                board[LV[0] - 'A'][LV[1] - '1'] = V;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) {
                char[] temp = st.nextToken().toCharArray();
                board[temp[0] - 'A'][temp[1] - '1'] = i + 1;
            }

            recursion(0, 0, 0, board, domino);
            bw.write("Puzzle " + index);
            bw.write("\n");
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    bw.write(String.valueOf(board[i][j]));
                }

                bw.write("\n");
            }

            index++;
        }

        bw.flush();
    }

    private static boolean recursion(int y, int x, int z, int[][] board, boolean[][] domino) {
        if (z == 81) {
            return true;
        }

        if (x == 9) {
            return recursion(y + 1, 0, z, board, domino);
        }

        if (board[y][x] == 0) {
            ArrayList<Integer> cn = check(x, y, board);
            for (int i = 0; i < cn.size(); i++) {
                board[y][x] = cn.get(i);
                // 가로
                if (x + 1 < 9 && board[y][x + 1] == 0) {
                    ArrayList<Integer> nn = check(x + 1, y, board);
                    for (int j = 0; j < nn.size(); j++) {
                        if (domino[cn.get(i)][nn.get(j)]) {
                            continue;
                        }

                        board[y][x + 1] = nn.get(j);
                        domino[cn.get(i)][nn.get(j)] = true;
                        domino[nn.get(j)][cn.get(i)] = true;
                        boolean temp = recursion(y, x + 1, z + 1, board, domino);
                        if (temp) {
                            return true;
                        }

                        board[y][x + 1] = 0;
                        domino[cn.get(i)][nn.get(j)] = false;
                        domino[nn.get(j)][cn.get(i)] = false;
                    }
                }

                // 세로
                if (y + 1 < 9 && board[y + 1][x] == 0) {
                    ArrayList<Integer> nn = check(x, y + 1, board);
                    for (int j = 0; j < nn.size(); j++) {
                        if (domino[cn.get(i)][nn.get(j)]) {
                            continue;
                        }

                        board[y + 1][x] = nn.get(j);
                        domino[cn.get(i)][nn.get(j)] = true;
                        domino[nn.get(j)][cn.get(i)] = true;
                        boolean temp = recursion(y, x + 1, z + 1, board, domino);
                        if (temp) {
                            return true;
                        }

                        board[y + 1][x] = 0;
                        domino[cn.get(i)][nn.get(j)] = false;
                        domino[nn.get(j)][cn.get(i)] = false;
                    }
                }

                board[y][x] = 0;
            }
        } else {
            return recursion(y, x + 1, z + 1, board, domino);
        }

        return false;
    }

    private static ArrayList<Integer> check(int x, int y, int[][] board) {
        boolean[] check = new boolean[10];
        for (int i = 0; i < 9; i++) {
            check[board[i][x]] = true;
            check[board[y][i]] = true;
        }

        int xt = x / 3 * 3;
        int yt = y / 3 * 3;
        for (int i = xt; i < xt + 3; i++) {
            for (int j = yt; j < yt + 3; j++) {
                check[board[j][i]] = true;
            }
        }

        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (!check[i]) {
                a.add(i);
            }
        }

        return a;
    }
}