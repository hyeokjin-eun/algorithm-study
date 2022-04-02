package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16958
public class Backjun16958 {
    private static int N;
    private static int T;
    private static Pair[] city;
    private static final String[] array = {
            "6 3\n" +
            "0 1 2\n" +
            "0 5 1\n" +
            "1 3 3\n" +
            "1 1 5\n" +
            "0 3 5\n" +
            "1 7 5\n" +
            "5\n" +
            "1 2\n" +
            "1 5\n" +
            "1 6\n" +
            "3 4\n" +
            "4 2"
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
        T = stoi(st.nextToken());
        city = new Pair[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = stoi(st.nextToken());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            city[i] = new Pair(t, x, y);
        }

        int M = stoi(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = stoi(st.nextToken()) - 1;
            int to = stoi(st.nextToken()) - 1;
            int answer = 0;
            if (city[from].t == 1 && city[to].t == 1) {
                answer = getDistance(from, to);
            } else if (city[from].t == 1 || city[to].t == 1) {
                answer = Math.min(getTelpoPoint(city[from].t == 1 ? to : from) + T, getDistance(from, to));
            } else {
                answer = Math.min(getTelpoPoint(from) + getTelpoPoint(to) + T, getDistance(from, to));
            }

            bw.write(String.valueOf(answer));
            if (i != M - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static int getDistance(int a, int b) {
        int dist = Math.abs(city[a].x - city[b].x) + Math.abs(city[a].y - city[b].y);
        if (city[a].t == 1 && city[b].t == 1) return Math.min(dist, T);
        return dist;
    }

    private static int getTelpoPoint(int p) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (city[i].t == 0) {
                continue;
            }

            min = Math.min(min, getDistance(i, p));
        }

        return min;
    }

    private static class Pair {
        int t;
        int x;
        int y;
        public Pair(int t, int x, int y) {
            this.t = t;
            this.x = x;
            this.y = y;
        }
    }
}