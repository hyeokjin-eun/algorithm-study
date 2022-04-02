package com.company.java.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1238
public class Backjun1238 {
    private static int INF = Integer.MAX_VALUE;
    private static IOBuffered ioBuffered;
    private static int N, M, X;
    private static ArrayList<Route>[] students, reverse;
    private static int answer;
    private static final String[] array = {
            "4 8 2\n" +
            "1 2 4\n" +
            "1 3 2\n" +
            "1 4 7\n" +
            "2 1 1\n" +
            "2 3 5\n" +
            "3 1 2\n" +
            "3 4 4\n" +
            "4 2 3"
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
        printOutputData();
    }

    private static void setInputData() throws IOException {
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        X = stoi(st.nextToken()) - 1;
        setArray();
    }

    private static void setArray() throws IOException {
        students = new ArrayList[N];
        reverse = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            students[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            int start = stoi(st.nextToken()) - 1;
            int end = stoi(st.nextToken()) - 1;
            int time = stoi(st.nextToken());
            students[start].add(Route.of(end, time));
            reverse[end].add(Route.of(start, time));
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        int[] dist1 = dijkstra(students);
        int[] dist2 = dijkstra(reverse);

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dist1[i] + dist2[i]);
        }

        answer = max;
    }

    private static int[] dijkstra(ArrayList<Route>[] target) {
        PriorityQueue<Route> queue = new PriorityQueue<>();
        queue.offer(Route.of(X, 0));
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[X] = 0;
        while (!queue.isEmpty()) {
            Route cRoute = queue.poll();
            int cEnd = cRoute.getEnd();
            int cTime = cRoute.getTime();
            if (dist[cEnd] < cTime) {
                continue;
            }

            for (Route nRoute : target[cEnd]) {
                int nEnd = nRoute.getEnd();
                int nTime = nRoute.getTime();
                if (dist[nEnd] > dist[cEnd] + nTime) {
                    dist[nEnd] = dist[cEnd] + nTime;
                    queue.offer(Route.of(nEnd, dist[cEnd] + nTime));
                }
            }
        }

        return dist;
    }

    private static void printOutputData() throws IOException {
        ioBuffered.print(answer);
    }

    private static class Route implements Comparable<Route> {
        private final int end;
        private final int time;

        private Route(int end, int time) {
            this.end = end;
            this.time = time;
        }

        public static Route of(int end, int time) {
            return new Route(end, time);
        }

        public int getEnd() {
            return end;
        }

        public int getTime() {
            return time;
        }

        @Override
        public int compareTo(Route o) {
            if (this == o) {
                return 0;
            }

            return this.getTime() - o.getTime();
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

        public void print(int answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int answer) throws IOException {
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}