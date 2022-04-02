package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/18352
public class Backjun18352 {
    private static int N;
    private static int M;
    private static int K;
    private static int X;
    private static ArrayList<Integer>[] a;
    private static final String[] array = {
            "4 4 2 1\n" +
            "1 2\n" +
            "1 3\n" +
            "2 3\n" +
            "2 4",
            "4 4 1 1\n" +
            "1 2\n" +
            "1 3\n" +
            "2 3\n" +
            "2 4"
    };

    public static void main (String[] args) throws IOException {
        // TEST
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

    private static void test() {
        N = 4;
        M = 4;
        K = 2;
        X = 1 - 1;
        int[][] temp = new int[][]{
                {1, 2},
                {1, 3},
                {2, 3},
                {2, 4}
        };

        a = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            a[i] = new ArrayList<>();
        }

        for (int[] t : temp) {
            int from = t[0] - 1;
            int to = t[1] - 1;
            a[from].add(to);
        }

        int[] dist = dijkstra();
        Arrays.stream(dist).forEach(System.out::println);
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        X = stoi(st.nextToken()) - 1;
        a = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken()) - 1;
            int to = stoi(st.nextToken()) - 1;
            a[from].add(to);
        }

        int[] dist = dijkstra();
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (dist[i] == K) {
                count++;
            }
        }

        if (count == 0) {
            bw.write(String.valueOf(-1));
        } else {
            for (int i = 0; i < N; i++) {
                if (dist[i] == K) {
                    bw.write(String.valueOf(i + 1));
                    bw.write("\n");
                }
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static int[] dijkstra() {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(X);
        while (!queue.isEmpty()) {
            int c = queue.poll();
            for (int n : a[c]) {
                if (dist[n] > dist[c] + 1) {
                    dist[n] = dist[c] + 1;
                    queue.offer(n);
                }
            }
        }

        return dist;
    }
}