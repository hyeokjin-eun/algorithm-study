package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/13505
public class Backjun13505 {
    private static final String[] array = {
            "5\n" +
            "1 2 3 4 5"
    };

    public static void main(String[] args) throws IOException {
        //test
        test();
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
        int N = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            int num = stoi(st.nextToken());
            trie.add(num);
            answer = Math.max(answer, trie.query(num) ^ num);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {
        int test = 1;
        System.out.println(Integer.toBinaryString(test));
        System.out.println(Integer.parseInt("101111101011110000100000000", 2));
        System.out.println(String.format("%32s", Integer.toBinaryString(test)).replace(" ", "0"));
    }

    private static class Trie {
        ArrayList<Node> trie = new ArrayList<>();
        int root;
        private int init () {
            trie.add(new Node());
            return trie.size() - 1;
        }

        public Trie() {
            root = init();
        }

        private void add(int node, int num, int bit) {
            if (bit == -1) {
                trie.get(node).valid = true;
                return;
            }

            int c = (num >> bit) & 1;
            if (trie.get(node).children[c] == -1) {
                trie.get(node).children[c] = init();
            }

            add(trie.get(node).children[c], num, bit - 1);
        }

        public void add(int num) {
            add(root, num, 31);
        }

        private int query(int node, int num, int bit) {
            if (bit == -1) {
                return 0;
            }

            int c = 1 - ((num >> bit) & 1);
            if (trie.get(node).children[c] == -1) {
                c = 1 - c;
            }

            if (trie.get(node).children[c] == -1) {
                return 0;
            }

            int next = 0;
            if (c == 1) {
                next = 1 << bit;
            }

            return next | query(trie.get(node).children[c], num, bit - 1);
        }

        public int query(int num) {
            return query(root, num, 31);
        }

        private class Node {
            int[] children;
            boolean valid;
            private Node() {
                children = new int[2];
                Arrays.fill(children, -1);
                valid = false;
            }
        }
    }
}