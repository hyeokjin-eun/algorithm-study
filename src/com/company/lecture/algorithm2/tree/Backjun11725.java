package com.company.lecture.algorithm2.tree;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11725
// TODO: 2021-10-03 확인 필요
public class Backjun11725 {
    private static final String[] array = {
            "7\n" +
            "1 6\n" +
            "6 3\n" +
            "3 5\n" +
            "4 1\n" +
            "2 4\n" +
            "4 7",
            "12\n" +
            "1 2\n" +
            "1 3\n" +
            "2 4\n" +
            "3 5\n" +
            "3 6\n" +
            "4 7\n" +
            "4 8\n" +
            "5 9\n" +
            "5 10\n" +
            "6 11\n" +
            "6 12"
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
        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        ArrayList<Node> b = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new ArrayList<>());
            b.add(new Node(i, 0));
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            a.get(from).add(to);
            a.get(to).add(from);
        }

        boolean[] check = new boolean[n];
        dfs(0, check, a, b);
        for (int i = 1; i < n; i++) {
            bw.write(String.valueOf(b.get(i).p + 1));
            if (i != n - 1) {
                bw.write(" ");
            }
        }

        bw.flush();
    }

    private static void dfs(int c, boolean[] check, ArrayList<ArrayList<Integer>> a, ArrayList<Node> b) {
        check[c] = true;
        for (int i = 0; i < a.get(c).size(); i++) {
            if (!check[a.get(c).get(i)] && c != a.get(c).get(i)) {
                b.get(a.get(c).get(i)).p = c;
                dfs(a.get(c).get(i), check, a, b);
            }
        }
    }

    private static class Node {
        int c;
        int p;

        public Node(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }
}
