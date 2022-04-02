package com.company.java.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/12851
public class Backjun12851 {
    private static IOBuffered ioBuffered;
    private static int N;
    private static int K;
    private static Graph graph;
    private static final String[] array = {
            "5 17"
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
        ioBuffered = IOBuffered.create(input);
        setInputData();
        setAnswer();
        printOutData();
    }

    private static void setInputData() throws IOException {
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        graph = Graph.create();
        graph.bfs(N);
    }

    private static void printOutData() throws IOException {
        if (N >= K) {
            ioBuffered.print(N - K, 1);
        } else {
            ioBuffered.print(graph.getMin(), graph.getCount());
        }
    }

    private static class Graph {
        private final Queue<Integer> queue;
        private final int[] time;
        private int count;
        private int min;

        private Graph() {
            queue = new LinkedList<>();
            time = new int[100001];
            count = 0;
            min = Integer.MAX_VALUE;
        }

        public static Graph create() {
            return new Graph();
        }

        public int getCount() {
            return count;
        }

        public int getMin() {
            return min;
        }

        public void bfs(int point) {
            queue.offer(point);
            time[point] = 1;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (min < time[cur]) {
                   return;
                }

                move(cur, cur + 1);
                move(cur, cur - 1);
                move(cur, cur * 2);
            }
        }

        private void move(int cur, int next) {
            if (isOutTime(next)) {
                return;
            }

            if (isPoint(next)) {
                min = time[cur];
                count++;
            }

            if (isNotMove(cur, next)) {
                queue.offer(next);
                time[next] = time[cur] + 1;
            }
        }

        private boolean isOutTime(int next) {
            return next < 0 || 100001 <= next;
        }

        private boolean isPoint(int next) {
            return next == K;
        }

        private boolean isNotMove(int cur, int next) {
            return time[next] == 0 || time[next] == time[cur] + 1;
        }
    }

    private static class IOBuffered {
        private final BufferedReader bufferedReader;
        private final BufferedWriter bufferedWriter;

        private IOBuffered(String input) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
            InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }

        public static IOBuffered create(String input) {
            return new IOBuffered(input);
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print(int pointMin, int pointMinCount) throws IOException {
            write(pointMin, pointMinCount);
            flush();
        }

        private void write(int pointMin, int pointMinCount) throws IOException {
            bufferedWriter.write(String.valueOf(pointMin));
            bufferedWriter.write("\n");
            bufferedWriter.write(String.valueOf(pointMinCount));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}