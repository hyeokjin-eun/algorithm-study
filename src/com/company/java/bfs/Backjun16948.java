package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16948
public class Backjun16948 {
    private static final String[] array = {
            "7\n" +
            "6 6 0 1",
            "6\n" +
            "5 1 0 5",
            "7\n" +
            "0 3 4 3"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int ex = Integer.parseInt(st.nextToken());
        int ey = Integer.parseInt(st.nextToken());
        boolean[][] check = new boolean[N][N];
        int answer = bfs(sx, sy, ex, ey, N, check);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int sx, int sy, int ex, int ey, int N, boolean[][] check) {
        Queue<Integer> xq = new LinkedList<>();
        Queue<Integer> yq = new LinkedList<>();
        Queue<Integer> cq = new LinkedList<>();
        xq.add(sx);
        yq.add(sy);
        cq.add(0);
        check[sy][sx] = true;
        int[] xa = new int[]{-2, -2, 0, 0, 2, 2};
        int[] ya = new int[]{-1, 1, -2, 2, -1, 1};
        while (!xq.isEmpty() && !yq.isEmpty()) {
            int cx = xq.poll();
            int cy = yq.poll();
            int cc = cq.poll();
            if (cx == ex && cy == ey) {
                return cc;
            }

            for (int i = 0; i < 6; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (nx < 0 || ny < 0 || N <= nx || N <= ny) {
                    continue;
                }

                if (!check[ny][nx]) {
                    check[ny][nx] = true;
                    xq.add(nx);
                    yq.add(ny);
                    cq.add(cc + 1);
                }
            }
        }

        return -1;
    }
}