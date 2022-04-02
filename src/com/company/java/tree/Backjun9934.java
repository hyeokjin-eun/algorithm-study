package com.company.java.tree;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/9934
public class Backjun9934 {
    private static final String[] array = {
            "2\n" +
            "2 1 3",
            "3\n" +
            "1 6 4 3 5 2 7"
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
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Queue<Integer>> q = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            q.add(new LinkedList<>());
        }

        inorder(1, k, st, q);
        for (int i = 0; i < k; i++) {
            Queue<Integer> temp = q.get(i);
            while (!temp.isEmpty()) {
                bw.write(String.valueOf(temp.poll()));
                if (!temp.isEmpty()) {
                    bw.write(" ");
                }
            }

            if (i != k - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static void inorder(int index, int k, StringTokenizer st, ArrayList<Queue<Integer>> q) {
        if (index < k) {
            inorder(index + 1, k, st, q);
        }

        q.get(index - 1).add(Integer.parseInt(st.nextToken()));
        if (index < k) {
            inorder(index + 1, k, st, q);
        }
    }
}
