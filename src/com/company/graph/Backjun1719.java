package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1719
public class Backjun1719 {
    private static final int INF = Integer.MAX_VALUE;
    private static IOBuffered ioBuffered;
    private static int N;
    private static int M;
    private static ArrayList<Route>[] dropOfPoint;
    private static int[][] dist;
    private static int[][] answer;
    private static final String[] array = {
            "6 10\n" +
            "1 2 2\n" +
            "1 3 1\n" +
            "2 4 5\n" +
            "2 5 3\n" +
            "2 6 7\n" +
            "3 4 4\n" +
            "3 5 6\n" +
            "3 6 7\n" +
            "4 6 4\n" +
            "5 6 2"
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
        ioBuffered = IOBuffered.create(input);
        setInputData();
        setAnswer();
        printOutData();
    }

    private static void setInputData() throws IOException {
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        setDropOfPoint();
        setDist();
    }

    private static void setDropOfPoint() throws IOException {
        dropOfPoint = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            dropOfPoint[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            int start = stoi(st.nextToken());
            int end = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            dropOfPoint[start].add(Route.of(end, weight));
            dropOfPoint[end].add(Route.of(start, weight));
        }
    }

    private static void setDist() {
        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }

        answer = new int[N + 1][N + 1];
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        for (int i = 1; i <= N; i++) {
            dijkstra(i);
        }
    }

    private static void dijkstra(int index) {
        PriorityQueue<Route> queue = new PriorityQueue<>();
        queue.offer(Route.of(index, 0));
        dist[index][index] = 0;
        while (!queue.isEmpty()) {
            Route cRoute = queue.poll();
            int cEnd = cRoute.getEnd();
            int cWeight = cRoute.getWeight();
            if (dist[index][cEnd] > cWeight) {
                continue;
            }

            for (Route nRoute : dropOfPoint[cEnd]) {
                int nEnd = nRoute.getEnd();
                int nWeight = nRoute.getWeight();
                if (dist[index][nEnd] > dist[index][cEnd] + nWeight) {
                    dist[index][nEnd] = dist[index][cEnd] + nWeight;
                    answer[index][nEnd] = cEnd;
                    queue.offer(Route.of(nEnd, dist[index][cEnd] + nWeight));
                }
            }
        }
    }

    private static void printOutData() throws IOException {
        ioBuffered.print(answer);
    }

    private static class Route implements Comparable<Route> {
        private final int end;
        private final int weight;

        private Route(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        public static Route of(int end, int weight) {
            return new Route(end, weight);
        }

        public int getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Route o) {
            if (this == o) {
                return 0;
            }

            return this.getWeight() - o.getWeight();
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

        public void print(int[][] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int[][] answer) throws IOException {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    bufferedWriter.write(answer[j][i] == 0 ? "-" : String.valueOf(answer[j][i]));
                    if (j != N) {
                        bufferedWriter.write(" ");
                    }
                }

                if (i != N) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}