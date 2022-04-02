package com.company.java.programmers;

import java.io.*;
import java.util.*;

// link
// https://programmers.co.kr/learn/courses/30/lessons/43162
public class program_network {
    private static final String[] array = {
            "3\n" +
            "1 1 0\n" +
            "1 1 0\n" +
            "0 0 1"
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
        int[][] a = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<ArrayList<Integer>> t = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            t.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }

                if (a[i][j] == 1) {
                    t.get(i).add(j);
                }
            }
        }

        boolean[] check = new boolean[N];
        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (!check[i]) {
                answer++;
                dfs(i, check, t);
            }
        }

        System.out.println(answer);
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
