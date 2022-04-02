package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16236
public class Backjun16236 {
    private static final int[] xa = new int[]{0, -1, 1, 0};
    private static final int[] ya = new int[]{-1, 0, 0, 1};
    private static final String[] array = {
            "6\n" +
            "5 4 3 2 3 4\n" +
            "4 3 2 3 4 5\n" +
            "3 2 9 5 6 6\n" +
            "2 1 2 3 4 5\n" +
            "3 2 1 6 5 4\n" +
            "6 6 6 6 6 6",
            "3\n" +
            "0 0 0\n" +
            "0 0 0\n" +
            "0 9 0",
            "3\n" +
            "0 0 1\n" +
            "0 0 0\n" +
            "0 9 0",
            "4\n" +
            "4 3 2 1\n" +
            "0 0 0 0\n" +
            "0 0 9 0\n" +
            "1 2 3 4",
            "6\n" +
            "6 0 6 0 6 1\n" +
            "0 0 0 0 0 2\n" +
            "2 3 4 5 6 6\n" +
            "0 0 0 0 0 2\n" +
            "0 2 0 0 0 0\n" +
            "3 9 3 0 0 1",
            "6\n" +
            "1 1 1 1 1 1\n" +
            "2 2 6 2 2 3\n" +
            "2 2 5 2 2 3\n" +
            "2 2 2 4 6 3\n" +
            "0 0 0 0 0 6\n" +
            "0 0 0 0 0 9"
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
        int[][] board = new int[N][N];
        Pair pair = new Pair(0, 0);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = st.nextToken().charAt(0) - '0';
                if (board[i][j] == 9) {
                    board[i][j] = 0;
                    pair = new Pair(j, i);
                }
            }
        }

        Shark shark = new Shark(2, 0);
        int time = 0;
        while (true) {
            int dist = bfs(pair, N, board, shark);
            if (dist == -1) {
                break;
            }

            shark.expUp();
            time += dist;
        }

        bw.write(String.valueOf(time));
        bw.flush();
    }

    private static int bfs(Pair pair, int N, int[][] board, Shark shark) {
        int l = shark.l;
        Queue<Pair> q = new LinkedList<>();
        int[][] dist = new int[N][N];
        boolean[][] check = new boolean[N][N];
        check[pair.y][pair.x] = true;
        q.offer(pair);
        dist[pair.y][pair.x] = 0;
        ArrayList<Pair> temp = new ArrayList<>();
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int x = p.x;
            int y = p.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + xa[i];
                int ny = y + ya[i];
                if (nx < 0 || ny < 0 || N <= nx || N <= ny) {
                    continue;
                }

                if (!check[ny][nx] && board[ny][nx] <= l) {
                    dist[ny][nx] = dist[y][x] + 1;
                    check[ny][nx] = true;
                    q.offer(new Pair(nx, ny));
                    if (board[ny][nx] != 0 && board[ny][nx] < l) {
                        temp.add(new Pair(nx, ny, dist[y][x] + 1));
                    }
                }
            }
        }

        if (temp.size() == 0) {
            return -1;
        } else {
            Pair best = temp.get(0);
            for (Pair now : temp) {
                if (now.t < best.t) {
                    best = now;
                } else if (now.t == best.t && now.y < best.y) {
                    best = now;
                } else if (now.t == best.t && now.y == best.y && now.x < best.x) {
                    best = now;
                }
            }

            board[best.y][best.x] = 0;
            pair.x = best.x;
            pair.y = best.y;
            return best.t;
        }
    }

    private static class Pair {
        int x;
        int y;
        int t;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    private static class Shark {
        int l;
        int e;
        public Shark(int l, int e) {
            this.l = l;
            this.e = e;
        }

        public void expUp() {
            if (this.e + 1 == this.l) {
                this.l = this.l + 1;
                this.e = 0;
            } else {
                this.e = this.e + 1;
            }
        }
    }
}