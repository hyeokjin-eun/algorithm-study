package com.company.java.lecture.algorithm2.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16940
public class Backjun16940 {
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

        int[] ac = new int[n];
        int[] pre = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ac[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        boolean[] check = new boolean[n];
        bw.write(bfs(check, a, ac, n, pre) ? "1" : "0");
        bw.flush();
    }

    private static boolean bfs (boolean[] check, ArrayList<ArrayList<Integer>> a, int[] ac, int n, int[] pre) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        check[0] = true;
        int m = 1;
        for (int i = 0; i < n; i++) {
            if (q.isEmpty()) {
                return false;
            }

            int x = q.poll();
            if (x != ac[i]) {
                return false;
            }

            int cnt = 0;
            for (int j = 0; j < a.get(x).size(); j++) {
                if (!check[a.get(x).get(j)]) {
                    pre[a.get(x).get(j)] = x;
                    cnt++;
                }
            }

            for (int j = 0; j < cnt; j++) {
                if (m + j >= n || pre[ac[m + j]] != x) {
                    return false;
                }

                q.add(ac[m + j]);
                check[ac[m + j]] = true;
            }

            m += cnt;
        }

        return true;
    }
}
