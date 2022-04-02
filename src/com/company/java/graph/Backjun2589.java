package com.company.java.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2589
public class Backjun2589 {
    private static final String[] array = {
            "5 7\n" +
            "WLLWWWL\n" +
            "LLLWLLL\n" +
            "LWLWLWW\n" +
            "LWLWLLL\n" +
            "WLLWLWW",
            "7 7\n" +
            "WLLLLLW\n" +
            "LWLWLWW\n" +
            "LLLWLWW\n" +
            "LWWWLWW\n" +
            "LLLLLWW\n" +
            "LWWWWWW\n" +
            "WWWWWWW"
    };

    public static void main(String[] args) throws IOException {
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
        char[][] c = new char[N][M];
        for (int i = 0; i < N; i++) {
            char[] s = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                c[i][j] = s[j];
            }
        }


        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (c[i][j] == 'L') {
                    boolean[][] check = new boolean[N][M];
                    int temp = bfs(j, i, N, M, c, check);
                    if (answer < temp) {
                        answer = temp;
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int x, int y, int n, int m, char[][] c, boolean[][] check) {
        Queue<Integer> xq = new LinkedList<>();
        Queue<Integer> yq = new LinkedList<>();
        check[y][x] = true;
        xq.add(x);
        yq.add(y);
        int[] xa = new int[]{0, 1, 0, -1};
        int[] ya = new int[]{-1, 0, 1, 0};
        int[][] dist = new int[n][m];
        int max = 0;
        while (!xq.isEmpty() && !yq.isEmpty()) {
            int cx = xq.poll();
            int cy = yq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];

                if (nx < 0 || ny < 0 || m <= nx || n <= ny) {
                    continue;
                }

                if (!check[ny][nx] && c[ny][nx] == 'L') {
                    max = Math.max(max, dist[cy][cx] + 1);
                    check[ny][nx] = true;
                    dist[ny][nx] = dist[cy][cx] + 1;
                    xq.add(nx);
                    yq.add(ny);
                }
            }
        }

        return max;
    }
}
