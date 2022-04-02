package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1051
public class Backjun1051 {
    private static final String[] array = {
            "3 5\n" +
            "42101\n" +
            "22100\n" +
            "22101"
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
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] nums = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(nums[j]);
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int temp = bf(j, i, n, m , a);
                if (answer < temp) {
                    answer = temp;
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bf(int x, int y, int n, int m, int[][] a) {
        int l = a[y][x];
        int result = 1;
        int temp = 1;
        while (y + temp < n && x + temp < m) {
            if (l == a[y][x + temp] && l == a[y + temp][x] && l == a[y + temp][x + temp]) {
                if (result < (temp + 1) * (temp + 1)) {
                    result = (temp + 1) * (temp + 1);
                }
            }

            temp++;
        }

        return result;
    }
}
