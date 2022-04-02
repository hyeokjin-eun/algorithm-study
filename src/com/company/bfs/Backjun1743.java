package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1743
public class Backjun1743 {
    private static final String[] array = {
            "3 4 5\n" +
            "3 2\n" +
            "2 2\n" +
            "3 1\n" +
            "2 3\n" +
            "1 1"
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
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] a = new int[n][m];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            a[r][c] = 1;
        }

        int answer = 0;
        boolean[][] check = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!check[i][j] && a[i][j] == 1) {
                    int temp = bfs(j, i, check, a, n, m);
                    if (answer < temp) {
                        answer = temp;
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int x, int y, boolean[][] check, int[][] a, int n, int m) {
        Queue<Integer> xq = new LinkedList<>();
        Queue<Integer> yq = new LinkedList<>();
        int[] xa = new int[]{1, 0, -1, 0};
        int[] ya = new int[]{0, 1, 0, -1};
        xq.add(x);
        yq.add(y);
        check[y][x] = true;
        int answer = 1;
        while (!xq.isEmpty() && !yq.isEmpty()) {
            int xt = xq.poll();
            int yt = yq.poll();
            for (int i = 0; i < 4; i++) {
                int xn = xt + xa[i];
                int yn = yt + ya[i];
                if (xn < 0 || yn < 0 || m <= xn || n <= yn) {
                    continue;
                }

                if (!check[yn][xn] && a[yn][xn] == 1) {
                    xq.add(xn);
                    yq.add(yn);
                    check[yn][xn] = true;
                    answer++;
                }
            }
        }

        return answer;
    }
}
