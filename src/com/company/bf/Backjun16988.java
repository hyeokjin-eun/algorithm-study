package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16988
public class Backjun16988 {
    private static int N;
    private static int M;
    private static ArrayList<Pair> pairs;
    private static final String[] array = {
            "3 4\n" +
            "2 0 0 0\n" +
            "0 0 0 0\n" +
            "0 0 0 2",
            "5 4\n" +
            "0 0 0 0\n" +
            "0 2 2 0\n" +
            "0 2 0 0\n" +
            "2 2 0 0\n" +
            "2 2 0 0",
            "8 4\n" +
            "0 0 2 0\n" +
            "0 1 2 2\n" +
            "0 0 1 1\n" +
            "2 0 0 0\n" +
            "0 1 0 0\n" +
            "2 0 1 0\n" +
            "2 0 0 0\n" +
            "0 0 0 0",
            "3 3\n" +
            "2 2 2\n" +
            "2 2 2\n" +
            "0 2 0",
            "8 6\n" +
            "0 0 1 2 2 2\n" +
            "0 0 1 2 2 2\n" +
            "0 1 1 0 2 2\n" +
            "1 2 2 0 1 1\n" +
            "1 2 2 1 0 0\n" +
            "1 2 1 0 2 0\n" +
            "1 1 0 0 0 1\n" +
            "0 1 0 0 0 0",
            "7 7\n" +
            "0 0 0 0 1 0 0\n" +
            "2 0 1 1 2 1 0\n" +
            "2 1 2 0 2 2 1\n" +
            "2 1 2 2 0 1 0\n" +
            "2 1 2 1 0 0 0\n" +
            "2 1 2 1 0 0 0\n" +
            "2 2 1 0 0 0 0",
            "7 5\n" +
            "0 0 1 1 1\n" +
            "0 1 2 2 2\n" +
            "2 1 2 1 1\n" +
            "2 1 2 0 2\n" +
            "0 1 2 0 1\n" +
            "0 1 2 2 2\n" +
            "0 0 1 1 1"
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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    pairs.add(new Pair(j, i));
                }
            }
        }

        // 로직 시작
        int answer = 0;
        for (int y1 = 0; y1 < N; y1++) {
            for (int x1 = 0; x1 < M; x1++) {
                // 첫번째 위치 찾기
                if (board[y1][x1] != 0) {
                    continue;
                }

                board[y1][x1] = 1;
                for (int y2 = 0; y2 < N; y2++) {
                    for (int x2 = 0; x2 < M; x2++) {
                        // 두번째 위치 찾기
                        if (board[y2][x2] != 0) {
                            continue;
                        }

                        board[y2][x2] = 1;
                        boolean[][] check = new boolean[N][M];
                        int temp = 0;
                        // bfs로 제거 가능 돌 찾기
                        for (int i = 0; i < pairs.size(); i++) {
                            Pair pair = pairs.get(i);
                            if (!check[pair.y][pair.x]) {
                                temp += bfs(pair, check, board);
                            }
                        }

                        answer = Math.max(answer, temp);
                        board[y2][x2] = 0;
                    }
                }

                board[y1][x1] = 0;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static int bfs(Pair pair, boolean[][] check, int[][] board) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(pair);
        check[pair.y][pair.x] = true;
        boolean ok = true;
        int answer = 1;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + xa[i];
                int ny = p.y + ya[i];
                if (nx < 0 || ny < 0 || M <= nx || N <= ny) {
                    continue;
                }

                if (board[ny][nx] == 1) {
                    continue;
                }

                if (board[ny][nx] == 0) {
                    ok = false;
                    continue;
                }

                if (!check[ny][nx] && board[ny][nx] == 2) {
                    check[ny][nx] = true;
                    queue.offer(new Pair(nx, ny));
                    answer++;
                }
            }
        }

        return ok ? answer : 0;
    }

    private static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
