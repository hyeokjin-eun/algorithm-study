package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/13460
// TODO: 2021-10-12 비트마스크로 다시 풀어보기
public class Backjun13460 {
    private static final String[] array = {
            "5 5\n" +
            "#####\n" +
            "#..B#\n" +
            "#.#.#\n" +
            "#RO.#\n" +
            "#####",
            "7 7\n" +
            "#######\n" +
            "#...RB#\n" +
            "#.#####\n" +
            "#.....#\n" +
            "#####.#\n" +
            "#O....#\n" +
            "#######",
            "7 7\n" +
            "#######\n" +
            "#..R#B#\n" +
            "#.#####\n" +
            "#.....#\n" +
            "#####.#\n" +
            "#O....#\n" +
            "#######",
            "10 10\n" +
            "##########\n" +
            "#R#...##B#\n" +
            "#...#.##.#\n" +
            "#####.##.#\n" +
            "#......#.#\n" +
            "#.######.#\n" +
            "#.#....#.#\n" +
            "#.#.#.#..#\n" +
            "#...#.O#.#\n" +
            "##########"
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
        char[][] board = new char[N][M];
        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;
        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = temp[j];
                if (temp[j] == 'R') {
                    rx = j;
                    ry = i;
                }

                if (temp[j] == 'B') {
                    bx = j;
                    by = i;
                }
            }
        }

        int answer = recursion(rx, ry, bx, by, -1, -1, -1, -1, 0, board, 0);
        System.out.println();
    }

    private static int recursion(int rx, int ry, int bx, int by, int prx, int pry, int pbx, int pby, int toggle, char[][] board, int cnt) {
        boolean fail1 = board[ry][rx] == 'O';
        boolean fail2 = board[by][bx] == 'O';
        if (fail1 && !fail2) {
            return cnt;
        }

        if (fail1 || fail2) {
            return -1;
        }

        if (cnt == 10) {
            return -1;
        }

        char[] move = new char[]{'U', 'D', 'L', 'R'};
        int answer = -1;
        for (int i = 0; i < 4; i++) {
            int[] rp;
            int[] bp;
            if (toggle == 0) {
                if (move[i] == 'U') {
                    if (ry < by) {
                        rp = movePoint(rx, ry, 'U', board);
                        bp = movePoint(bx, by, 'U', board);
                    } else {
                        bp = movePoint(bx, by, 'U', board);
                        rp = movePoint(rx, ry, 'U', board);
                    }
                } else {
                    if (ry < by) {
                        bp = movePoint(bx, by, 'D', board);
                        rp = movePoint(rx, ry, 'D', board);
                    } else {
                        rp = movePoint(rx, ry, 'D', board);
                        bp = movePoint(bx, by, 'D', board);
                    }
                }
            } else {
                if (move[i] == 'L') {
                    if (rx < bx) {
                        rp = movePoint(rx, ry, 'L', board);
                        bp = movePoint(bx, by, 'L', board);
                    } else {
                        bp = movePoint(bx, by, 'L', board);
                        rp = movePoint(rx, ry, 'L', board);
                    }
                } else {
                    if (rx < bx) {
                        bp = movePoint(bx, by, 'R', board);
                        rp = movePoint(rx, ry, 'R', board);

                    } else {
                        rp = movePoint(rx, ry, 'R', board);
                        bp = movePoint(bx, by, 'R', board);
                    }
                }
            }

            if (rx == rp[0] && ry == rp[1] && bx == bp[0] && by == bp[1]) {
                continue;
            }

            if (rp[0] == prx && rp[1] == pry && bp[0] == pbx && bp[1] == pby) {
                continue;
            }

            int temp = recursion(rp[0], rp[1], bp[0], bp[1], rx, ry, bx, by, toggle == 0 ? 1 : 0, board, cnt + 1);
            if (answer == -1 || temp < answer) {
                answer = temp;
            }
        }

        return answer;
    }

    // U : 위, D : 아래, L : 왼쪽, R : 오른쪽
    private static int[] movePoint(int x, int y, char move, char[][] board) {
        int[] point = new int[2];
        if (move == 'U') {
            for (int i = y; 0 <= i; i--) {
                if (board[i][x] == '.' || board[i][x] == 'O') {
                    point[0] = x;
                    point[1] = i;
                    if (board[i][x] == 'O') {
                        break;
                    }
                } else if (board[i][x] == board[y][x]) {
                    point[0] = x;
                    point[1] = i;
                }
            }
        } else if (move == 'D') {
            for (int i = y; i < board.length; i++) {
                if (board[i][x] == '.' || board[i][x] == 'O') {
                    point[0] = x;
                    point[1] = i;
                    if (board[i][x] == 'O') {
                        break;
                    }
                } else if (board[i][x] == board[y][x]) {
                    point[0] = x;
                    point[1] = i;
                }
            }
        } else if (move == 'L') {
            for (int i = x; 0 <= i; i--) {
                if (board[y][i] == '.' || board[y][i] == 'O') {
                    point[0] = i;
                    point[1] = y;
                    if (board[y][i] == 'O') {
                        break;
                    }
                } else if (board[y][i] == board[y][x]) {
                    point[0] = x;
                    point[1] = i;
                }
            }
        } else {
            for (int i = x; i < board[y].length; i++) {
                if (board[y][i] == '.' || board[y][i] == 'O') {
                    point[0] = i;
                    point[1] = y;
                    if (board[y][i] == 'O') {
                        break;
                    }
                } else if (board[y][i] == board[y][x]) {
                    point[0] = x;
                    point[1] = i;
                }
            }
        }

        return point;
    }
}
