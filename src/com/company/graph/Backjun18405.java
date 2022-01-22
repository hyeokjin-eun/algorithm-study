package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/18405
public class Backjun18405 {
    private static int N;
    private static int K;
    private static int S;
    private static int X;
    private static int Y;
    private static int[][] a;
    private static ArrayList<ArrayList<Pair>> temp;
    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static final String[] array = {
            "3 3\n" +
            "1 0 2\n" +
            "0 0 0\n" +
            "3 0 0\n" +
            "2 3 2",
            "3 3\n" +
            "1 0 2\n" +
            "0 0 0\n" +
            "3 0 0\n" +
            "1 2 2"
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
        int QUEUE_SIZE = 3;
        ArrayList<Queue<Pair>> queues = createQueues(QUEUE_SIZE);
        assert queues.size() == QUEUE_SIZE;
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        temp = new ArrayList<>();
        for (int i = 0; i <= K; i++) {
            temp.add(new ArrayList<>());
        }

        a = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                a[i][j] = stoi(st.nextToken());
                if (a[i][j] != 0) {
                    temp.get(a[i][j]).add(new Pair(j, i, a[i][j]));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        S = stoi(st.nextToken());
        X = stoi(st.nextToken());
        Y = stoi(st.nextToken());
        bw.write(String.valueOf(bfs()));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static int bfs() {
        ArrayList<Queue<Pair>> virus = createQueues(K);
        for (int i = 0; i < K; i++) {
            setQueue(virus.get(i), i + 1);
        }

        for (int t = 0; t < S; t++) {
            for (int i = 0; i < virus.size(); i++) {
                Queue<Pair> temp = new LinkedList<>();
                while (!virus.get(i).isEmpty()) {
                    Pair pair = virus.get(i).poll();
                    for (int j = 0; j < 4; j++) {
                        int nx = pair.x + xa[j];
                        int ny = pair.y + ya[j];
                        if (isArrayOutOfIndex(nx, ny)) {
                            continue;
                        }

                        if (isVirusEmpty(nx, ny)) {
                            a[ny][nx] = pair.t;
                            temp.offer(new Pair(nx, ny, pair.t));
                        }
                    }
                }

                resetQueue(virus.get(i), temp);
            }
        }


        return a[X - 1][Y - 1];
    }

    private static void resetQueue(Queue<Pair> a,Queue<Pair> b) {
        while (!b.isEmpty()) {
            a.offer(b.poll());
        }
    }

    private static void setQueue(Queue<Pair> queue, int i) {
        for (int j = 0; j < temp.get(i).size(); j++) {
            queue.offer(temp.get(i).get(j));
        }
    }

    private static ArrayList<Queue<Pair>> createQueues(int size) {
        ArrayList<Queue<Pair>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new LinkedList<>());
        }

        return list;
    }

    private static boolean isArrayOutOfIndex(int x, int y) {
        return x < 0 || y < 0 || N <= x || N <= y;
    }

    private static boolean isVirusEmpty(int x, int y) {
        return a[y][x] == 0;
    }

    private static class Pair {
        int x;
        int y;
        int t;
        public Pair(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
}