package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1966
public class Backjun1966 {
    private static final String[] array = {
            "3\n" +
            "1 0\n" +
            "5\n" +
            "4 2\n" +
            "1 2 3 4\n" +
            "6 0\n" +
            "1 1 9 1 1 1"
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
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < 3; i++) {
            priorityQueue.offer(new Pair(i, i));
        }

        assert !priorityQueue.isEmpty();
        assert priorityQueue.poll().index == 2;
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int T = stoi(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = stoi(st.nextToken());
            int M = stoi(st.nextToken());
            Queue<Pair> queue = new LinkedList<>();
            PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int num = stoi(st.nextToken());
                Pair pair = new Pair(i, num);
                queue.offer(pair);
                priorityQueue.offer(pair);
            }

            int count = 0;
            while (!queue.isEmpty() && !priorityQueue.isEmpty()) {
                boolean check = false;
                Pair pair = queue.poll();
                if (isImportance(pair, priorityQueue)) {
                    queue.offer(pair);
                } else {
                    Queue<Pair> temp = print(priorityQueue, pair);
                    reset(temp, priorityQueue);
                    count++;
                    if (pair.index == M) {
                        check = true;
                    }
                }

                if (check) {
                    break;
                }
            }

            bw.write(String.valueOf(count));
            if (t != T - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static Queue<Pair> print(PriorityQueue<Pair> priorityQueue, Pair pair) {
        Queue<Pair> temp = new LinkedList<>();
        while (!priorityQueue.isEmpty()) {
            if (pair.index == priorityQueue.peek().index) {
                priorityQueue.poll();
                break;
            }

            temp.offer(priorityQueue.poll());
        }

        return temp;
    }

    private static boolean isImportance(Pair pair, PriorityQueue<Pair> priorityQueue) {
        if (priorityQueue.isEmpty()) {
            return false;
        }

        return pair.importance < priorityQueue.peek().importance;
    }

    private static void reset(Queue<Pair> queue, PriorityQueue<Pair> priorityQueue) {
        while (!queue.isEmpty()) {
            priorityQueue.offer(queue.poll());
        }
    }

    private static class Pair implements Comparable<Pair>{
        int index;
        int importance;
        public Pair(int index, int importance) {
            this.index = index;
            this.importance = importance;
        }

        @Override
        public int compareTo(Pair o) {
            if (o.importance == this.importance) {
                return this.index - o.index;
            }

            return o.importance - this.importance;
        }
    }
}