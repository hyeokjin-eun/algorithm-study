package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/17141
public class Backjun17141 {
    private static int N;
    private static int M;
    private static StringTokenizer st;
    private static int[][] a;
    private static final String[] array = {
            "7 3\n" +
            "2 0 0 0 1 1 0\n" +
            "0 0 1 0 1 2 0\n" +
            "0 1 1 0 1 0 0\n" +
            "0 1 0 0 0 0 0\n" +
            "0 0 0 2 0 1 1\n" +
            "0 1 0 0 0 0 0\n" +
            "2 1 0 0 0 0 2",
            "7 3\n" +
            "2 0 2 0 1 1 0\n" +
            "0 0 1 0 1 2 0\n" +
            "0 1 1 2 1 0 0\n" +
            "2 1 0 0 0 0 2\n" +
            "0 0 0 2 0 1 1\n" +
            "0 1 0 0 0 0 0\n" +
            "2 1 0 0 2 0 2",
            "7 4\n" +
            "2 0 2 0 1 1 0\n" +
            "0 0 1 0 1 2 0\n" +
            "0 1 1 2 1 0 0\n" +
            "2 1 0 0 0 0 2\n" +
            "0 0 0 2 0 1 1\n" +
            "0 1 0 0 0 0 0\n" +
            "2 1 0 0 2 0 2",
            "7 5\n" +
            "2 0 2 0 1 1 0\n" +
            "0 0 1 0 1 2 0\n" +
            "0 1 1 2 1 0 0\n" +
            "2 1 0 0 0 0 2\n" +
            "0 0 0 2 0 1 1\n" +
            "0 1 0 0 0 0 0\n" +
            "2 1 0 0 2 0 2",
            "7 3\n" +
            "2 0 2 0 1 1 0\n" +
            "0 0 1 0 1 0 0\n" +
            "0 1 1 1 1 0 0\n" +
            "2 1 0 0 0 0 2\n" +
            "1 0 0 0 0 1 1\n" +
            "0 1 0 0 0 0 0\n" +
            "2 1 0 0 2 0 2",
            "7 2\n" +
            "2 0 2 0 1 1 0\n" +
            "0 0 1 0 1 0 0\n" +
            "0 1 1 1 1 0 0\n" +
            "2 1 0 0 0 0 2\n" +
            "1 0 0 0 0 1 1\n" +
            "0 1 0 0 0 0 0\n" +
            "2 1 0 0 2 0 2",
            "5 1\n" +
            "2 2 2 1 1\n" +
            "2 1 1 1 1\n" +
            "2 1 1 1 1\n" +
            "2 1 1 1 1\n" +
            "2 2 2 1 1",
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
        st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        a = new int[N][N];
        int size = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                a[i][j] = stoi(st.nextToken());
                if (a[i][j] == 2) {
                    size++;
                }
            }
        }

        Pair[] virus = new Pair[size];
        int index = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (a[i][j] == 2) {
                    virus[index++] = new Pair(j, i);
                }
            }
        }

        int[] seq = new int[size];
        for (int i = 0; i < M; i++) {
            seq[i] = 1;
        }

        Arrays.sort(seq);
        int answer = -1;
        do {
            Pair[] v = new Pair[M];
            int vi = 0;
            for (int i = 0; i < size; i++) {
                if (seq[i] == 1) {
                    a[virus[i].y][virus[i].x] = 2;
                    v[vi++] = new Pair(virus[i].x, virus[i].y);
                } else {
                    a[virus[i].y][virus[i].x] = 0;
                }
            }

            int temp = bfs(v);
            if (temp != -1) {
                if (answer == -1 || temp < answer) {
                    answer = temp;
                }
            }
        } while (nextPermutation(seq));

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};

    private static int bfs(Pair[] virus) {
        Queue<Pair> queue = new LinkedList<>();
        boolean[][] check = new boolean[N][N];
        int[][] dist = new int[N][N];
        for (Pair pair : virus) {
            queue.offer(pair);
        }

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int x = pair.x;
            int y = pair.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + xa[i];
                int ny = y + ya[i];
                if (nx < 0 || ny < 0 || N <= nx || N <= ny) {
                    continue;
                }

                if (!check[ny][nx] && a[ny][nx] == 0) {
                    queue.offer(new Pair(nx, ny));
                    check[ny][nx] = true;
                    dist[ny][nx] = dist[y][x] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (a[i][j] == 0 && !check[i][j]) {
                    return -1;
                }

                max = Math.max(max, dist[i][j]);
            }
        }

        return max;
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

    private static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("50 10\n");
        int m = 10;
        for (int i = 0 ; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (i == 0 && j % 2 == 0 && 0 < m--) {
                    sb.append("2");
                } else {
                    sb.append("0");
                }

                if (j != 49) {
                    sb.append(" ");
                }
            }

            if (i != 49) {
                sb.append("\n");
            }
        }

        array[7] = sb.toString();
    }
}