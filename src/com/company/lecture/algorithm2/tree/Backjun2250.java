package com.company.lecture.algorithm2.tree;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2250
public class Backjun2250 {
    private static int width = 0;
    private static final String[] array = {
            "8\n" +
            "1 2 3\n" +
            "2 4 -1\n" +
            "3 8 -1\n" +
            "4 -1 5\n" +
            "5 6 7\n" +
            "6 -1 -1\n" +
            "7 -1 -1\n" +
            "8 -1 -1",
            "19\n" +
            "1 2 3\n" +
            "2 4 5\n" +
            "3 6 7\n" +
            "4 8 -1\n" +
            "5 9 10\n" +
            "6 11 12\n" +
            "7 13 -1\n" +
            "8 -1 -1\n" +
            "9 14 15\n" +
            "10 -1 -1\n" +
            "11 16 -1\n" +
            "12 -1 -1\n" +
            "13 17 -1\n" +
            "14 -1 -1\n" +
            "15 18 -1\n" +
            "16 -1 -1\n" +
            "17 -1 19\n" +
            "18 -1 -1\n" +
            "19 -1 -1"
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
        ArrayList<Node> a = new ArrayList<>(n);
        boolean[] check = new boolean[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            a.add(new Node(c, l, r));
            if (0 <= l) {
                check[l] = true;
            }

            if (0 <= r) {
                check[r] = true;
            }
        }

        a.sort((o1, o2) -> {
            if (o1.c < o2.c) {
                return -1;
            } else if (o1.c == o2.c) {
                return 0;
            } else {
                return 1;
            }
        });

        int root = 0;
        for (int i = 0; i < n; i++) {
            if (!check[i]) {
                root = i;
            }
        }

        inorder(root, a, 1);
        int maxLevel = 0;
        for (int i = 0; i < n; i++) {
            Node node = a.get(i);
            if (maxLevel < node.d) {
                maxLevel = node.d;
            }
        }

        int[][] temp = new int[maxLevel][2];
        for (int i = 0; i < n; i++) {
            Node node = a.get(i);
            if (node.o < temp[node.d - 1][0]) {
                temp[node.d - 1][0] = node.o;
            }

            if (temp[node.d - 1][1] < node.o) {
                temp[node.d - 1][1] = node.o;
            }
        }

        int answer = 0;
        int answerLevel = 0;
        for (int i = 0; i < maxLevel; i++) {
            int ans = temp[i][1] - temp[i][0] + 1;
            if (answer < ans) {
                answer = ans;
                answerLevel = i + 1;
            }
        }

        bw.write(String.valueOf(answerLevel));
        bw.write(" ");
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static void inorder(int c, ArrayList<Node> a, int l) {
        if (c == -2) {
            return;
        }

        Node n = a.get(c);
        inorder(n.l, a, l + 1);
        n.o = ++width;
        n.d = l;
        inorder(n.r, a, l + 1);
    }

    private static class Node {
        int c;
        int l;
        int r;
        int o;
        int d;

        public Node(int c, int l, int r) {
            this.c = c;
            this.l = l;
            this.r = r;
        }
    }
}
