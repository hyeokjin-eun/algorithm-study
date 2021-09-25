package com.company.lecture.algorithm2.tree;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1167
public class Backjun1167 {
    private static final String[] array = {
            "5\n" +
            "1 3 2 -1\n" +
            "2 4 4 -1\n" +
            "3 1 2 4 3 -1\n" +
            "4 2 4 3 3 5 6 -1\n" +
            "5 4 6 -1",
            "6\n" +
            "1 2 3 -1\n" +
            "2 1 3 5 3 3 5 -1\n" +
            "3 2 5 4 7 -1\n" +
            "4 3 7 -1\n" +
            "5 2 3 6 5 -1\n" +
            "6 5 5 -1",
            "4\n" +
            "1 2 7 3 2 -1\n" +
            "2 1 7 -1\n" +
            "3 1 2 4 3 -1\n" +
            "4 3 3 -1"
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
        int v = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Node>> a = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            while (true) {
                int to = Integer.parseInt(st.nextToken()) - 1;
                if (to == -2) {
                    break;
                }

                int distance = Integer.parseInt(st.nextToken());
                a.get(from).add(new Node(to, distance));
            }
        }

        int answer = 0;
        for (int i = 0; i < v; i++) {
            boolean[] check = new boolean[v];
            int[] distance = new int[v];
            int temp = bfs(i, check, a, distance, v);
            if (answer < temp) {
                answer = temp;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int root, boolean[] check, ArrayList<ArrayList<Node>> a, int[] distance, int v) {
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        check[root] = true;

        int answer = 0;
        while (!q.isEmpty()) {
            int x  = q.poll();
            if (0 < a.get(x).size()) {
                Node temp = new Node(0, 0);
                for (int i = 1; i < a.get(x).size(); i++) {
                     Node n = a.get(x).get(i);
                     if (!check[n.c] && temp.d < n.d) {
                         temp = n;
                     }
                }
            }
        }

        return answer;
    }

    private static class Node {
        int c;
        int d;

        public Node(int c, int d) {
            this.c = c;
            this.d = d;
        }
    }
}
