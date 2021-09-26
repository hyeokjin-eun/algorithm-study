package com.company.lecture.algorithm2.tree;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1967
public class Backjun1967 {
    private static final String[] array = {
            "12\n" +
            "1 2 3\n" +
            "1 3 2\n" +
            "2 4 5\n" +
            "3 5 11\n" +
            "3 6 9\n" +
            "4 7 1\n" +
            "4 8 7\n" +
            "5 9 15\n" +
            "5 10 4\n" +
            "6 11 6\n" +
            "6 12 10"
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
        ArrayList<ArrayList<Node>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int dist = Integer.parseInt(st.nextToken());
            a.get(from).add(new Node(to, dist));
            a.get(to).add(new Node(from, dist));
        }

        int root = 0;
        int[] dist = bfs(root, n, a);
        for (int i = 0; i< n; i++) {
            if (dist[i] > dist[root]) {
                root = i;
            }
        }

        dist = bfs(root, n, a);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (answer < dist[i]) {
                answer = dist[i];
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int[] bfs(int root, int n, ArrayList<ArrayList<Node>> a) {
        int[] dist = new int[n];
        boolean[] check = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        check[root] = true;
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int i = 0; i < a.get(x).size(); i++) {
                Node node = a.get(x).get(i);
                if (!check[node.c]) {
                    dist[node.c] = dist[x] + node.d;
                    check[node.c] = true;
                    q.add(node.c);
                }
            }
        }

        return dist;
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
