package com.company.tree;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/5639
public class Backjun5639 {
    private static final String[] array = {
            "50\n" +
            "30\n" +
            "24\n" +
            "5\n" +
            "28\n" +
            "45\n" +
            "98\n" +
            "52\n" +
            "60"
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
        Node a = new Node(Integer.parseInt(br.readLine()), null, null);
        while (true) {
            String str = br.readLine();
            if (str == null || str.equals("")) {
                break;
            }

            Node n = new Node(Integer.parseInt(str), null, null);
            a.add(n);
        }

        StringBuilder sb = postOrder(a);
        bw.write(sb.toString());
        bw.flush();
    }

    private static StringBuilder postOrder(Node n) {
        StringBuilder sb = new StringBuilder();
        if (n.l != null) {
            sb.append(postOrder(n.l));
        }

        if (n.r != null) {
            sb.append(postOrder(n.r));
        }

        sb.append(n.c);
        sb.append("\n");
        return sb;
    }

    private static class Node {
        int c;
        Node l;
        Node r;
        public Node(int c, Node l, Node r) {
            this.c = c;
            this.l = l;
            this.r = r;
        }

        public void add(Node n) {
            if (n.c < this.c) {
                if (l == null) {
                    this.l = n;
                } else {
                    this.l.add(n);
                }
            } else {
                if (r == null) {
                    this.r = n;
                } else {
                    this.r.add(n);
                }
            }
        }
    }
}
