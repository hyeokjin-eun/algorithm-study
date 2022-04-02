package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1325
public class Backjun1325 {
    private static final String[] array = {
            "5 4\n" +
            "3 1\n" +
            "3 2\n" +
            "4 3\n" +
            "5 3"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Integer>[] a = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            a[from].add(to);
        }

        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            boolean[] check = new boolean[N];
            check[i] = true;
            dfs(i, a, check, ans);
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, ans[i]);
        }

        for (int i = 0; i < N; i++) {
            if (max == ans[i]) {
                bw.write(String.valueOf(i + 1));
                if (i != N - 1) {
                    bw.write(" ");
                }
            }
        }

        bw.flush();
    }

    private static void dfs(int c, List<Integer>[] a, boolean[] check, int[] ans) {
        for (int i = 0; i < a[c].size(); i++) {
            if (!check[a[c].get(i)]) {
                check[a[c].get(i)] = true;
                ans[a[c].get(i)]++;
                dfs(a[c].get(i), a, check, ans);
            }
        }
    }
}
