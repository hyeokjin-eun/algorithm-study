package com.company.java.lecture.algorithm2.tree;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1991
public class Backjun1991 {
    private static final String[] array = {
            "7\n" +
            "A B C\n" +
            "B D .\n" +
            "C E F\n" +
            "E . .\n" +
            "F . G\n" +
            "D . .\n" +
            "G . ."
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
        ArrayList<Node> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = st.nextToken().charAt(0) - 'A';
            int l = st.nextToken().charAt(0) - 'A';
            int r = st.nextToken().charAt(0) - 'A';
            a.add(new Node(c, l, r));
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

        StringBuilder preorder = preorder(a.get(0), a);
        StringBuilder inorder = inorder(a.get(0), a);
        StringBuilder postorder = postorder(a.get(0), a);
        bw.write(preorder.toString());
        bw.write("\n");
        bw.write(inorder.toString());
        bw.write("\n");
        bw.write(postorder.toString());
        bw.flush();
    }

    private static StringBuilder preorder(Node n, ArrayList<Node> a) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) (n.c + 'A'));
        if (0 <= n.l) {
            sb.append(preorder(a.get(n.l), a));
        }

        if (0 <= n.r) {
            sb.append(preorder(a.get(n.r), a));
        }

        return sb;
    }

    private static StringBuilder inorder(Node n, ArrayList<Node> a) {
        StringBuilder sb = new StringBuilder();
        if (0 <= n.l) {
            sb.append(inorder(a.get(n.l), a));
        }

        sb.append((char) (n.c + 'A'));
        if (0 <= n.r) {
            sb.append(inorder(a.get(n.r), a));
        }

        return sb;
    }

    private static StringBuilder postorder(Node n, ArrayList<Node> a) {
        StringBuilder sb = new StringBuilder();
        if (0 <= n.l) {
            sb.append(postorder(a.get(n.l), a));
        }

        if (0 <= n.r) {
            sb.append(postorder(a.get(n.r), a));
        }

        sb.append((char) (n.c + 'A'));
        return sb;
    }

    private static class Node {
        int c;
        int l;
        int r;

        public Node(int c, int l, int r) {
            this.c = c;
            this.l = l;
            this.r = r;
        }
    }
}
