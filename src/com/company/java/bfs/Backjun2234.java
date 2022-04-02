package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2234
public class Backjun2234 {
    private static int N;
    private static int M;
    private static int[][] a;
    private static int[][] b;
    private static final String[] array = {
            "7 4\n" +
            "11 6 11 6 3 10 6\n" +
            "7 9 6 13 5 15 5\n" +
            "1 10 12 7 13 7 5\n" +
            "13 11 10 8 10 12 13"
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
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        a = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                a[i][j] = stoi(st.nextToken());
            }
        }

        int[] groups = new int[50 * 50 + 1];
        b = new int[M][N];
        int group = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (b[i][j] == 0) {
                    group++;
                    groups[group] = bfs(j, i, group);
                }
            }
        }

        bw.write(String.valueOf(group));
        bw.write("\n");
        int answer = 0;
        for (int i = 1; i <= group; i++) {
            if (answer < groups[i]) {
                answer = groups[i];
            }
        }

        bw.write(String.valueOf(answer));
        bw.write("\n");
        answer = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int x = j;
                int y = i;
                for (int k = 0; k < 4; k++) {
                    int nx = x + xa[k];
                    int ny = y + ya[k];
                    if (nx < 0 || ny < 0 || N <= nx || M <= ny) {
                        continue;
                    }

                    if (b[y][x] == b[ny][nx]) {
                        continue;
                    }

                    if ((a[y][x] & (1 << i)) > 0) {
                        if (answer < groups[b[y][x]] + groups[b[ny][nx]]) {
                            answer = groups[b[y][x]] + groups[b[ny][nx]];
                        }
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static final int[] xa = new int[]{-1, 0, 1, 0};
    private static final int[] ya = new int[]{0, -1, 0, 1};

    private static int bfs(int x, int y, int group) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        queue.offer(y);
        b[y][x] = group;
        int cnt = 0;
        while (!queue.isEmpty()) {
            int cx = queue.poll();
            int cy = queue.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (nx < 0 || ny < 0 || N <= nx || M <= ny) {
                    continue;
                }

                if (b[ny][nx] != 0) {
                    continue;
                }

                if ((a[cy][cx] & (1 << i)) > 0) {
                    continue;
                }

                queue.offer(nx);
                queue.offer(ny);
                b[ny][nx] = group;
            }
        }

        return cnt;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {

    }
}