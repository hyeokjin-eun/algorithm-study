package com.company.java.string;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/14425
public class Backjun14425_trie {
    private static final String[] array = {
            "5 11\n" +
            "baekjoononlinejudge\n" +
            "startlink\n" +
            "codeplus\n" +
            "sundaycoding\n" +
            "codingsh\n" +
            "baekjoon\n" +
            "codeplus\n" +
            "codeminus\n" +
            "startlink\n" +
            "starlink\n" +
            "sundaycoding\n" +
            "codingsh\n" +
            "codinghs\n" +
            "sondaycoding\n" +
            "startrink\n" +
            "icerink"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Trie trie = new Trie();
        int answer = 0;
        for (int i = 0; i < n + m; i++) {
            if (i < n) {
                trie.add(br.readLine());
            } else {
                if (trie.search(br.readLine())) {
                    answer++;
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static class Trie {
        ArrayList<Node> trie;
        int root;

        int init() {
            Node node = new Node();
            trie.add(node);
            return trie.size() - 1;
        }

        public Trie() {
            trie = new ArrayList<>();
            root = init();
        }

        private void add(int node, String s, int index) {
            if (index == s.length()) {
                trie.get(node).valid = true;
                return;
            }

            int c = s.charAt(index) - 'a';
            if (trie.get(node).children[c] == -1) {
                trie.get(node).children[c] = init();
            }

            int child = trie.get(node).children[c];
            add(child, s, index + 1);
        }

        public void add(String s) {
            add(root, s, 0);
        }

        private boolean search(int node, String s, int index) {
            if (node == -1) {
                return false;
            }

            if (index == s.length()) {
                return trie.get(node).valid;
            }

            int c = s.charAt(index) - 'a';
            int child = trie.get(node).children[c];
            return search(child, s, index + 1);
        }

        public boolean search(String s) {
            return search(root, s, 0);
        }

        private class Node {
            int[] children;
            boolean valid;

            public Node() {
                children = new int[26];
                Arrays.fill(children, -1);
                valid = false;
            }
        }
    }
}
