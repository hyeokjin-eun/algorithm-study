package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/17070
public class Backjun17070 {
    private static int N;
    private static final ArrayList<int[][]> t = new ArrayList<>(Arrays.asList(
            new int[][]{{0, 1, 0}, {1, 1, 2}},
            new int[][]{{1, 0, 1}, {1, 1, 2}},
            new int[][]{{0, 1, 0}, {1, 0, 1}, {1, 1, 2}}
    ));

    private static final String[] array = {
            "3\n" +
            "0 0 0\n" +
            "0 0 0\n" +
            "0 0 0",
            "4\n" +
            "0 0 0 0\n" +
            "0 0 0 0\n" +
            "0 0 0 0\n" +
            "0 0 0 0",
            "5\n" +
            "0 0 1 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0",
            "6\n" +
            "0 0 0 0 0 0\n" +
            "0 1 0 0 0 0\n" +
            "0 0 0 0 0 0\n" +
            "0 0 0 0 0 0\n" +
            "0 0 0 0 0 0\n" +
            "0 0 0 0 0 0"
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
        N = Integer.parseInt(br.readLine());
        int[][] a = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = recursion(1, 0, a, 0);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion(int x, int y, int[][] a, int type) {
        if (x == N - 1 && y == N - 1) {
            return 1;
        }

        int temp = 0;
        for (int i = 0; i < t.get(type).length; i++) {
            int nx = x + t.get(type)[i][1];
            int ny = y + t.get(type)[i][0];
            int nt = t.get(type)[i][2];
            if (0 <= nx && 0 <= ny && nx < N && ny < N) {
                if (nt == 2 && a[ny][nx] != 1 && a[ny - 1][nx] != 1 && a[ny][nx - 1] != 1) {
                    a[ny][nx - 1] = 1;
                    a[ny - 1][nx] = 1;
                    a[ny][nx] = 1;
                    temp += recursion(nx, ny, a, nt);
                    a[ny][nx - 1] = 0;
                    a[ny - 1][nx] = 0;
                    a[ny][nx] = 0;
                } else if (nt != 2 && a[ny][nx] != 1) {
                    a[ny][nx] = 1;
                    temp += recursion(nx, ny, a, nt);
                    a[ny][nx] = 0;
                }
            }
        }

        return temp;
    }
}