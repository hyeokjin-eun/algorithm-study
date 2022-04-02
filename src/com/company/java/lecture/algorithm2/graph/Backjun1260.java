package com.company.java.lecture.algorithm2.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1260
public class Backjun1260 {
    private static final String[] array = {
            "4 5 1\n" +
            "1 2\n" +
            "1 3\n" +
            "1 4\n" +
            "2 4\n" +
            "3 4",
            "5 5 3\n" +
            "5 4\n" +
            "5 2\n" +
            "1 2\n" +
            "3 4\n" +
            "3 1",
            "1000 1 1000\n" +
            "999 1000"
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
        int v = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer mst = new StringTokenizer(br.readLine());
            int m1 = Integer.parseInt(mst.nextToken());
            int m2 = Integer.parseInt(mst.nextToken());
            a.get(m1).add(m2);
            a.get(m2).add(m1);
        }

        for (int i = 0; i < n; i++) {
            Collections.sort(a.get(i));
        }

        boolean[] check = new boolean[n + 1];
        StringBuilder dfs = dfs(v, check, a);
        Arrays.fill(check, false);
        StringBuilder bfs = bfs(v, check, a);
        bw.write(dfs.toString());
        bw.write("\n");
        bw.write(bfs.toString());
        bw.flush();
    }

    private static StringBuilder dfs (int cur, boolean[] check, ArrayList<ArrayList<Integer>> a) {
        check[cur] = true;
        StringBuilder sb = new StringBuilder();
        sb.append(cur).append(" ");
        for (int i = 0; i < a.get(cur).size(); i++) {
            if (!check[a.get(cur).get(i)]) {
                sb.append(dfs(a.get(cur).get(i), check, a));
            }
        }

        return sb;
    }

    private static StringBuilder bfs (int v, boolean[] check, ArrayList<ArrayList<Integer>> a) {
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        check[v] = true;
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            v = q.poll();
            sb.append(v).append(" ");
            for (int i = 0; i < a.get(v).size(); i++) {
                if (!check[a.get(v).get(i)]) {
                    q.add(a.get(v).get(i));
                    check[a.get(v).get(i)] = true;
                }
            }
        }

        return sb;
    }
}
