package com.company.java.lecture.algorithm2.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16964
// TODO: 2021-09-22 문제 다시 풀어봐야함
public class Backjun16964 {
    private static final String[] array = {
            "4\n" +
            "1 2\n" +
            "1 3\n" +
            "2 4\n" +
            "1 2 3 4",
            "4\n" +
            "1 2\n" +
            "1 3\n" +
            "2 4\n" +
            "1 2 4 3",
            "4\n" +
            "1 2\n" +
            "1 3\n" +
            "2 4\n" +
            "1 3 2 4"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            a.get(from).add(to);
            a.get(to).add(from);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] ac = new int[n];
        boolean[] check = new boolean[n];
        for (int i = 0; i < n; i++) {
            ac[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        ArrayList<Integer> temp = new ArrayList<>();
        boolean answer = true;
        if (ac[0] == 0) {
            dfs(0, check, a, n, ac, temp);
        }

        for (int i = 0; i < n; i++) {
            if (temp.get(i) != ac[i]) {
                answer = false;
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int c, boolean[] check, ArrayList<ArrayList<Integer>> a, int n, int[] ac, ArrayList<Integer> temp) {
        if (check[c]) {
            return;
        }

        temp.add(c);
        check[c] = true;
        for (int i = 0; i < a.get(c).size(); i++) {
            dfs(a.get(c).get(i), check, a, n, ac, temp);
        }
    }
}
