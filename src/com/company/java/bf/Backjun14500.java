package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14500
public class Backjun14500 {
private static final String[] array = {
            "4 10\n" +
            "1 2 1 2 1 2 1 2 1 2\n" +
            "2 1 2 1 2 1 2 1 2 1\n" +
            "1 2 1 2 1 2 1 2 1 2\n" +
            "2 1 2 1 2 1 2 1 2 1",
            "5 5\n" +
            "1 2 3 4 5\n" +
            "5 4 3 2 1\n" +
            "2 3 4 5 6\n" +
            "6 5 4 3 2\n" +
            "1 2 1 2 1",
            "4 5\n" +
            "1 2 3 4 5\n" +
            "1 2 3 4 5\n" +
            "1 2 3 4 5\n" +
            "1 2 3 4 5"
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
        Scanner sc = new Scanner(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n][m];
        c = new boolean[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                go(i,j,0,0);
                if (j+2 < m) {
                    int temp = a[i][j] + a[i][j+1] + a[i][j+2];
                    if (i-1 >= 0) {
                        int temp2 = temp + a[i-1][j+1];
                        if (ans < temp2) ans = temp2;
                    }
                    if (i+1 < n) {
                        int temp2 = temp + a[i+1][j+1];
                        if (ans < temp2) ans = temp2;
                    }
                }
                if (i+2 < n) {
                    int temp = a[i][j] + a[i+1][j] + a[i+2][j];
                    if (j+1 < m) {
                        int temp2 = temp + a[i+1][j+1];
                        if (ans < temp2) ans = temp2;
                    }
                    if (j-1 >= 0) {
                        int temp2 = temp + a[i+1][j-1];
                        if (ans < temp2) ans = temp2;
                    }
                }
            }
        }
        System.out.println(ans);
    }

    static int[][] a;
    static boolean[][] c;
    static int n, m;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int ans = 0;
    static void go(int x, int y, int sum, int cnt) {
        if (cnt == 4) {
            if (ans < sum) ans = sum;
            return;
        }
        if (x < 0 || x >= n || y < 0 || y >= m) return;
        if (c[x][y]) return;
        c[x][y] = true;
        for (int k=0; k<4; k++) {
            go(x+dx[k],y+dy[k],sum+a[x][y],cnt+1);
        }
        c[x][y] = false;
    }
}
