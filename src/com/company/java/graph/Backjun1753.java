package com.company.java.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1753
public class Backjun1753 {
    private static int V;
    private static int E;
    private static int K;
    private static final String[] array = {
            "5 6\n" +
            "1\n" +
            "5 1 1\n" +
            "1 2 2\n" +
            "1 3 3\n" +
            "2 3 4\n" +
            "2 4 5\n" +
            "3 4 6"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        K = stoi(br.readLine()) - 1;
        PriorityQueue<Pair>[] queues = new PriorityQueue[V];
        for (int i = 0; i < V; i++) {
            queues[i] = new PriorityQueue<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken()) - 1;
            int to = stoi(st.nextToken()) - 1;
            int d = stoi(st.nextToken());
            queues[from].offer(new Pair(to, d));
        }

        int[] answer = dijkstra(queues);
        for (int i = 0; i < V; i++) {
            bw.write(answer[i] == -1 ? "INF" : String.valueOf(answer[i]));
            if (i != V - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int[] dijkstra(PriorityQueue<Pair>[] queues) {
        int[] dist = new int[V];
        Arrays.fill(dist, -1);
        dist[K] = 0;
        while (!queues[K].isEmpty()) {
            Pair pair = queues[K].poll();
            if (dist[pair.to] != -1) {
                continue;
            }

            dist[pair.to] = pair.weight;
            for (Pair p : queues[pair.to]) {
                queues[K].offer(new Pair(p.to, p.weight + pair.weight));
            }
        }

        return dist;
    }

    private static class Pair implements Comparable<Pair> {
        int to;
        int weight;
        public Pair(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair o) {
            return this.weight - o.weight;
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}