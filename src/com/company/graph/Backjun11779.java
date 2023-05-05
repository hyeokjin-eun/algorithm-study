package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11779
public class Backjun11779 {

    private static final String[] array = {
            "5\n" +
            "8\n" +
            "1 2 2\n" +
            "1 3 3\n" +
            "1 4 1\n" +
            "1 5 10\n" +
            "2 4 2\n" +
            "3 4 1\n" +
            "3 5 1\n" +
            "4 5 3\n" +
            "1 5"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int M;
    private static ArrayList<ArrayList<Node>> graph;
    private static int start;
    private static int end;
    private static int min;
    private static int[] pre;

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
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setData() throws IOException {
        N = Util.stoi(ioBuffered.readLine());
        M = Util.stoi(ioBuffered.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
            int a = Util.stoi(stringTokenizer.nextToken());
            int b = Util.stoi(stringTokenizer.nextToken());
            int l = Util.stoi(stringTokenizer.nextToken());
            graph.get(a).add(new Node(b, l));
        }

        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        start = Util.stoi(stringTokenizer.nextToken());
        end = Util.stoi(stringTokenizer.nextToken());
    }

    private static void setAnswer() {
        dijkstra();
    }

    private static void dijkstra() {
        pre = new int[N + 1];
        int[] dist = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        dist[start] = 0;
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int index = cur.getIndex();
            int cost = cur.getCost();
            if (dist[index] < cost) {
                continue;
            }

            for (Node next : graph.get(index)) {
                if (dist[next.getIndex()] > dist[index] + next.getCost()) {
                    dist[next.getIndex()] = dist[index] + next.getCost();
                    pre[next.getIndex()] = index;
                    queue.add(new Node(next.getIndex(), dist[next.getIndex()]));
                }
            }
        }

        min = dist[end];
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(min, pre, end);
    }

    private static class Node implements Comparable<Node> {
        private final int index;
        private final int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        public int getIndex() {
            return index;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    private static class Util {
        public static int stoi(String s) {
            return Integer.parseInt(s);
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

        public static IOBuffered create(String input) throws IOException {
            return new IOBuffered(input);
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print(int min, int[] pre, int end) throws IOException {
            write(min, pre, end);
            flush();
        }

        private void write(int min, int[] pre, int end) throws IOException {
            Stack<Integer> stack = new Stack<>();
            stack.push(end);
            int count = 0;
            while (pre[end] != 0) {
                count++;
                stack.push(pre[end]);
                end = pre[end];
            }

            bufferedWriter.write(String.valueOf(min));
            bufferedWriter.write("\n");
            bufferedWriter.write(String.valueOf(count + 1));
            bufferedWriter.write("\n");
            while (!stack.isEmpty()) {
                bufferedWriter.write(String.valueOf(stack.pop()));
                if (count != 0) {
                    bufferedWriter.write(" ");
                }

                count--;
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
