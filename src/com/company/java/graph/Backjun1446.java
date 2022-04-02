package com.company.java.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1446
// TODO: 2021-12-26 다익스트라를 큐 형식으로 풀어보기 (재귀 지양)
public class Backjun1446 {
    private static int N;
    private static int D;
    private static ArrayList<Pair>[] a;
    private static int[] dist;
    private static final String[] array = {
            "5 150\n" +
            "0 50 10\n" +
            "0 50 20\n" +
            "50 100 10\n" +
            "100 151 10\n" +
            "110 140 90",
            "2 100\n" +
            "10 60 40\n" +
            "50 90 20",
            "8 900\n" +
            "0 10 9\n" +
            "20 60 45\n" +
            "80 190 100\n" +
            "50 70 15\n" +
            "160 180 14\n" +
            "140 160 14\n" +
            "420 901 5\n" +
            "450 900 0"
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
        N = stoi(st.nextToken());
        D = stoi(st.nextToken());
        a = new ArrayList[10001];
        dist = new int[10001];
        for (int i = 0; i <= 10000; i++) {
            dist[i] = i;
            a[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int w = stoi(st.nextToken());
            a[from].add(new Pair(to, w));
        }

        dijkstra(0);
        bw.write(String.valueOf(dist[D]));
        bw.flush();
    }

    private static void dijkstra(int start) {
        if (start > D) {
            return;
        }

        if (dist[start + 1] > dist[start] + 1) {
            dist[start + 1] = dist[start] + 1;
        }

        for (int i = 0; i < a[start].size(); i++) {
            if (dist[start] + a[start].get(i).w < dist[a[start].get(i).to]) {
                dist[a[start].get(i).to] = dist[start] + a[start].get(i).w;
            }
        }

        dijkstra(start + 1);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static class Pair {
        int to;
        int w;
        public Pair(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}