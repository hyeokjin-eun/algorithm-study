package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/9205
public class Backjun9205 {
    private static final String[] array = {
            "2\n" +
            "2\n" +
            "0 0\n" +
            "1000 0\n" +
            "1000 1000\n" +
            "2000 1000\n" +
            "2\n" +
            "0 0\n" +
            "1000 0\n" +
            "2000 1000\n" +
            "2000 2000",
            "1\n" +
            "2\n" +
            "0 0\n" +
            "-450 -250\n" +
            "-600 500\n" +
            "-600 1000",
            "3\n" +
            "0\n" +
            "1000 1000\n" +
            "1000 1001\n" +
            "1\n" +
            "0 0\n" +
            "1000 0\n" +
            "0 2000\n" +
            "2\n" +
            "0 0\n" +
            "10000 0\n" +
            "0 1000\n" +
            "0 2000"
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
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] temp = new int[n + 2][2];
            for (int j = 0; j < n + 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                temp[j][0] = x;
                temp[j][1] = y;
            }

            ArrayList<ArrayList<Integer>> a = new ArrayList<>();
            for (int j = 0; j < n + 2; j++) {
                a.add(new ArrayList<>());
            }

            for (int j = 0; j < n + 2; j++) {
                for (int k = 0; k < n + 2; k++) {
                    if (j == k) {
                        continue;
                    }

                    int num = Math.abs(temp[j][0] - temp[k][0]) + Math.abs(temp[j][1] - temp[k][1]);
                    if (num <= 1000 && 0 <= num) {
                        a.get(j).add(k);
                    }
                }
            }

            boolean[] check = new boolean[n + 2];
            dfs(0, check, a);
            if (check[n + 1]) {
                bw.write("happy");
            } else {
                bw.write("sad");
            }

            if (i != t - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static void dfs(int c, boolean[] check, ArrayList<ArrayList<Integer>> a) {
        check[c] = true;
        for (int i = 0; i < a.get(c).size(); i++) {
            if (!check[a.get(c).get(i)]) {
                dfs(a.get(c).get(i), check, a);
            }
        }
    }
}
