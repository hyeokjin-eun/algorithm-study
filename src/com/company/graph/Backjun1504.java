package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1504
public class Backjun1504 {
    private static final int INF = Integer.MAX_VALUE;
    private static IOBuffered ioBuffered;
    private static int N;
    private static int E;
    private static ArrayList<Edge>[] edgeList;
    private static int P1;
    private static int P2;
    private static int answer;
    private static final String[] array = {
            "4 6\n" +
            "1 2 3\n" +
            "2 3 3\n" +
            "3 4 1\n" +
            "1 3 5\n" +
            "2 4 5\n" +
            "1 4 4\n" +
            "2 3",
            "2 0\n" +
            "1 2",
            "3 3\n" +
            "1 3 20\n" +
            "1 2 15\n" +
            "2 3 6\n" +
            "1 3"
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
        E = stoi(st.nextToken());
        setEdgeList();
        st = new StringTokenizer(ioBuffered.readLine());
        P1 = stoi(st.nextToken()) - 1;
        P2 = stoi(st.nextToken()) - 1;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setEdgeList() throws IOException {
        edgeList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            int start = stoi(st.nextToken()) - 1;
            int end = stoi(st.nextToken()) - 1;
            int weight = stoi(st.nextToken());
            edgeList[start].add(Edge.of(end, weight));
            edgeList[end].add(Edge.of(start, weight));
        }
    }

    private static void setAnswer() {
        int case1 = search(P1, P2);
        int case2 = search(P2, P1);
        if (case1 == INF && case2 == INF) {
            answer = -1;
        } else {
            answer = Math.min(case1, case2);
        }
    }

    private static int search(int p1, int p2) {
        int[] temp = new int[]{0, p1, p2, N - 1};
        int sum = 0;
        for (int i = 0; i < temp.length - 1; i++) {
            int num = dijkstra(temp[i], temp[i + 1]);
            if (num == INF) {
                return INF;
            }

            sum += num;
        }

        return sum;
    }

    private static int dijkstra(int p1, int p2) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(Edge.of(p1, 0));
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[p1] = 0;
        while (!queue.isEmpty()) {
            Edge cEdge = queue.poll();
            int cEnd = cEdge.getEnd();
            int cWeight = cEdge.getWeight();
            if (dist[cEnd] < cWeight) {
                continue;
            }

            for (Edge nEdge : edgeList[cEnd]) {
                int nEnd = nEdge.getEnd();
                int nWeight = nEdge.getWeight();
                if (dist[nEnd] > dist[cEnd] + nWeight) {
                    dist[nEnd] = dist[cEnd] + nWeight;
                    queue.offer(Edge.of(nEnd, nWeight));
                }
            }
        }

        return dist[p2];
    }

    private static void printOutputData() throws IOException {
        ioBuffered.print(answer);
    }

    private static class Edge implements Comparable<Edge> {
        private final int end;
        private final int weight;

        private Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        public static Edge of(int end, int weight) {
            return new Edge(end, weight);
        }

        public int getEnd() {
            return this.end;
        }

        public int getWeight() {
            return this.weight;
        }


        @Override
        public int compareTo(Edge o) {
            if (o == this) {
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