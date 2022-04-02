package com.company.java.lecture.algorithm2.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/11724
public class Backjun11724 {
    private static final String[] array = {
            "6 5\n" +
            "1 2\n" +
            "2 5\n" +
            "5 1\n" +
            "3 4\n" +
            "4 6",
            "6 8\n" +
            "1 2\n" +
            "2 5\n" +
            "5 1\n" +
            "3 4\n" +
            "4 6\n" +
            "5 4\n" +
            "2 4\n" +
            "2 3"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st1.nextToken());
            int v = Integer.parseInt(st1.nextToken());
            a.get(u).add(v);
            a.get(v).add(u);
        }

        int answer = 0;
        boolean[] check = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!check[i]) {
                dfs(i, check, a);
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static void dfs (int cur, boolean[] check, ArrayList<ArrayList<Integer>> a) {
        check[cur] = true;
        for (int i = 0; i < a.get(cur).size(); i++) {
            if (!check[a.get(cur).get(i)]) {
                dfs(a.get(cur).get(i), check, a);
            }
        }
    }
}
