package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14426
// Trie Tree 사용 목적 문자열 검색을 빠르게 해주는 이진 검색 트리 구조. 시간 복잡도 O(|문자열 길이|)
public class Backjun14426 {
    private static final String[] array = {
            "5 10\n" +
            "baekjoononlinejudge\n" +
            "startlink\n" +
            "codeplus\n" +
            "sundaycoding\n" +
            "codingsh\n" +
            "baekjoon\n" +
            "star\n" +
            "start\n" +
            "code\n" +
            "sunday\n" +
            "coding\n" +
            "cod\n" +
            "online\n" +
            "judge\n" +
            "plus"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        Trie trie = new Trie();
        int answer = 0;
        for (int i = 0; i < N + M; i++) {
            if (i < N) {
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

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {
        // Trie Create
        Trie trie = new Trie();
        assert trie.root == 0;
        trie.add("aa");
        assert trie.trie.get(0).children[0] != -1;
        assert trie.trie.get(1).children[0] != -1;
        assert trie.search("aa");
        assert !trie.search("ab");
    }

    private static class Trie {
        ArrayList<Node> trie;
        int root;
        private int init() {
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
                int next = init();
                trie.get(node).children[c] = next;
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
                return true;
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