package com.company.java.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1085
public class Backjun5567 {
    private static int N;
    private static int M;
    private static ArrayList<ArrayList<Integer>> a;
    private static final String[] array = {
            "6\n" +
            "5\n" +
            "1 2\n" +
            "1 3\n" +
            "3 4\n" +
            "2 3\n" +
            "4 5",
            "6\n" +
            "5\n" +
            "2 3\n" +
            "3 4\n" +
            "4 5\n" +
            "5 6\n" +
            "2 5"
    };

    public static void main (String[] args) throws IOException {
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
        N = stoi(br.readLine());
        M = stoi(br.readLine());
        a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken()) - 1;
            int to = stoi(st.nextToken()) - 1;
            a.get(from).add(to);
            a.get(to).add(from);
        }

        bw.write(String.valueOf(bfs()));
        bw.flush();
    }

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        queue.offer(0);
        boolean[] check = new boolean[N];
        check[0] = true;
        int answer = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int index = queue.poll();
            if (index < 2) {
                for (Integer n : a.get(cur)) {
                    if (check[n]) {
                        continue;
                    }

                    queue.offer(n);
                    queue.offer(index + 1);
                    check[n] = true;
                    answer++;
                }
            }
        }

        return answer;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}