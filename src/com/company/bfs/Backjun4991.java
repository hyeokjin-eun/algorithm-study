package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/4991
public class Backjun4991 {
    private static int W;
    private static int H;
    private static char[][] a;
    private static int[][] d;
    private static final String[] array = {
            "7 5\n" +
            ".......\n" +
            ".o...*.\n" +
            ".......\n" +
            ".*...*.\n" +
            ".......\n" +
            "15 13\n" +
            ".......x.......\n" +
            "...o...x....*..\n" +
            ".......x.......\n" +
            ".......x.......\n" +
            ".......x.......\n" +
            "...............\n" +
            "xxxxx.....xxxxx\n" +
            "...............\n" +
            ".......x.......\n" +
            ".......x.......\n" +
            ".......x.......\n" +
            "..*....x....*..\n" +
            ".......x.......\n" +
            "10 10\n" +
            "..........\n" +
            "..o.......\n" +
            "..........\n" +
            "..........\n" +
            "..........\n" +
            ".....xxxxx\n" +
            ".....x....\n" +
            ".....x.*..\n" +
            ".....x....\n" +
            ".....x....\n" +
            "0 0"
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
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = stoi(st.nextToken());
            H = stoi(st.nextToken());
            if (W == 0 && H == 0) {
                break;
            }

            a = new char[H][W];
            int max = 0;
            int sx = 0;
            int sy = 0;
            for (int i = 0; i < H; i++) {
                char[] chars = br.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                    a[i][j] = chars[j];
                    if (chars[j] == '*') {
                        max++;
                    } else if (chars[j] == 'o') {
                        max++;
                        sx = j;
                        sy = i;
                    }
                }
            }

            Pair[] pairs = new Pair[max];
            pairs[0] = new Pair(sx, sy);
            int index = 1;
            for (int i = 0 ; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (a[i][j] == '*') {
                        pairs[index++] = new Pair(j, i);
                    }
                }
            }

            d = new int[max][max];
            boolean ok = true;
            for (int i = 0; i < max; i++) {
                int[][] dist = bfs(pairs[i].x, pairs[i].y);
                for (int j = 0; j < max; j++) {
                    d[i][j] = dist[pairs[j].y][pairs[j].x];
                    if (d[i][j] == -1) {
                        ok = false;
                    }
                }
            }

            int answer = -1;
            if (ok) {
                int[] order = new int[max];
                for (int i = 0; i < max; i++) {
                    order[i] = i;
                }

                do {
                    int temp = d[0][order[0]];
                    for (int i = 0; i < max - 1; i++) {
                        temp += d[order[i]][order[i + 1]];
                    }

                    if (answer == -1 || temp < answer) {
                        answer = temp;
                    }
                } while (nextPermutation(order));
            }

            bw.write(String.valueOf(answer));
            bw.write("\n");
        }

        bw.flush();
    }

    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};

    private static int[][] bfs(int sx, int sy) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(sx, sy));
        boolean[][] check = new boolean[H][W];
        check[sy][sx] = true;
        int[][] dist = new int[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(dist[i], -1);
        }

        dist[sy][sx] = 0;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int x = pair.x;
            int y = pair.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + xa[i];
                int ny = y + ya[i];
                if (overCheck(nx, ny)) {
                    continue;
                }

                if (!check[ny][nx] && a[ny][nx] != 'x') {
                    queue.offer(new Pair(nx, ny));
                    check[ny][nx] = true;
                    dist[ny][nx] = dist[y][x] + 1;
                }
            }
        }

        return dist;
    }

    private static boolean overCheck(int x, int y) {
        return x < 0 || y < 0 || W <= x || H <= y;
    }

    private static boolean nextPermutation(int[] a) {
        int i = a.length - 1;
        while (i > 0 && a[i - 1] >= a[i]) {
            i--;
        }

        if (i < 1) {
            return false;
        }

        int j = a.length - 1;
        while (a[i - 1] >= a[j]) {
            j--;
        }

        int temp = a[i - 1];
        a[i - 1] = a[j];
        a[j] = temp;
        j = a.length - 1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }

        return true;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("7 5\n" +
                ".......\n" +
                ".o...*.\n" +
                ".......\n" +
                ".*...*.\n" +
                ".......\n" +
                "15 13\n" +
                ".......x.......\n" +
                "...o...x....*..\n" +
                ".......x.......\n" +
                ".......x.......\n" +
                ".......x.......\n" +
                "...............\n" +
                "xxxxx.....xxxxx\n" +
                "...............\n" +
                ".......x.......\n" +
                ".......x.......\n" +
                ".......x.......\n" +
                "..*....x....*..\n" +
                ".......x.......\n" +
                "10 10\n" +
                "..........\n" +
                "..o.......\n" +
                "..........\n" +
                "..........\n" +
                "..........\n" +
                ".....xxxxx\n" +
                ".....x....\n" +
                ".....x.*..\n" +
                ".....x....\n" +
                ".....x....\n");
        sb.append("20 20\n" +
                "o*.................*\n" +
                "*..................*\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "....................\n" +
                "*..................*\n" +
                "**................**\n");
        sb.append("0 0");
        array[0] = sb.toString();
    }
}