package com.company.java.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2583
public class Backjun2583 {
    private static final String[] array = {
            "5 7 3\n" +
            "0 2 4 4\n" +
            "1 1 2 5\n" +
            "4 0 6 2"
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
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] a = new int[m][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    a[y][x] = 1;
                }
            }
        }

        boolean[][] check = new boolean[m][n];
        int answer = 0;
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!check[i][j] && a[i][j] == 0) {
                    answer++;
                    int cnt = bfs(j, i, m, n, check, a);
                    temp.add(cnt);
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.write("\n");
        temp.sort(Comparator.naturalOrder());
        for (int i = 0; i < temp.size(); i++) {
            bw.write(String.valueOf(temp.get(i)));
            if (i != temp.size() - 1) {
                bw.write(" ");
            }
        }

        bw.flush();
    }

    private static int bfs(int x, int y, int m, int n, boolean[][] check, int[][] a) {
        Queue<Integer> xq = new LinkedList<>();
        Queue<Integer> yq = new LinkedList<>();
        xq.add(x);
        yq.add(y);
        check[y][x] = true;
        int[] xt = new int[]{1, 0, -1, 0};
        int[] yt = new int[]{0, 1, 0, -1};
        int cnt = 1;
        while (!xq.isEmpty() && !yq.isEmpty()) {
            int cx = xq.poll();
            int cy = yq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cx + xt[i];
                int ny = cy + yt[i];
                if (nx < 0 || ny < 0 || n <= nx || m <= ny) {
                    continue;
                }

                if (!check[ny][nx] && a[ny][nx] == 0) {
                    xq.add(nx);
                    yq.add(ny);
                    check[ny][nx] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
