package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16932
public class Backjun16932 {
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] a;
    private static int[][] group;
    private static ArrayList<Integer> dist;
    private static final String[] array = {
            "3 3\n" +
            "0 1 1\n" +
            "0 0 1\n" +
            "0 1 0",
            "TEST MADE",
            "5 4\n" +
            "1 1 0 0\n" +
            "1 0 1 0\n" +
            "1 0 1 0\n" +
            "0 1 1 0\n" +
            "1 0 0 1",
            "3 4\n" +
            "0 1 0 1\n" +
            "0 0 0 1\n" +
            "1 1 0 1"
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
        a = new int[N][M];
        dist = new ArrayList<>();
        group = new int[N][M];
        int index = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                a[i][j] = stoi(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (a[i][j] == 1 && group[i][j] == 0) {
                    bfs(j, i, index++);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (a[i][j] == 0) {
                    HashSet<Integer> set = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int nx = j + xa[k];
                        int ny = i + ya[k];
                        if (nx < 0 || ny < 0 || N <= ny || M <= nx) {
                            continue;
                        }

                        if (group[ny][nx] != 0) {
                            set.add(group[ny][nx] - 1);
                        }
                    }

                    int temp = 1;
                    for (int num : set) {
                        temp += dist.get(num);
                    }

                    answer = Math.max(answer, temp);
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int[] xa = new int[]{1, 0, -1, 0};
    private static int[] ya = new int[]{0, 1, 0, -1};
    private static void bfs(int x, int y, int g) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        queue.offer(y);
        group[y][x] = g + 1;
        int cnt = 1;
        while (!queue.isEmpty()) {
            int cx = queue.poll();
            int cy = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (nx < 0 || ny < 0 || N <= ny || M <= nx) {
                    continue;
                }

                if (group[ny][nx] == 0 && a[ny][nx] == 1) {
                    group[ny][nx] = g + 1;
                    queue.offer(nx);
                    queue.offer(ny);
                    cnt++;
                }
            }
        }

        dist.add(cnt);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("1000 1000 \n");
        for (int i = 1; i <= 1000; i++) {
            for (int j = 1; j <= 1000; j++) {
                sb.append("0");
                if (j != 1000) {
                    sb.append(" ");
                }
            }

            if (i != 1000) {
                sb.append("\n");
            }
        }

        array[1] = sb.toString();
    }
}