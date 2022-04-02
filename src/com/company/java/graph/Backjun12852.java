package com.company.java.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/12852
public class Backjun12852 {
    private static int N;
    private static boolean[] check;
    private static int[] before;
    private static final String[] array = {
            "2",
            "10",
            "10000000",
            "1"
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
        N = 10000000;
        check = new boolean[N + 1];
        before = new int[N + 1];
        System.out.println(bfs());
        Stack<Integer> stack = stack();
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
            if (!stack.isEmpty()) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        N = stoi(br.readLine());
        check = new boolean[N + 1];
        before = new int[N + 1];
        bw.write(String.valueOf(bfs()));
        bw.write("\n");
        Stack<Integer> stack = stack();
        while (!stack.isEmpty()) {
            bw.write(String.valueOf(stack.pop()));
            if (!stack.isEmpty()) {
                bw.write(" ");
            }
        }

        bw.flush();
    }

    private static int bfs() {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(N, 0));
        check[N] = true;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            final int number = pair.number;
            final int count = pair.count;
            if (number == 1) {
                return count;
            }

            if (isDivided(number, 3) && !isVisited(number / 3)) {
                addQueue(queue, number / 3, count + 1, number);
            }

            if (isDivided(number, 2) && !isVisited(number / 2)) {
                addQueue(queue, number / 2, count + 1, number);
            }

            if (!isVisited(number - 1)) {
                addQueue(queue, number - 1, count + 1, number);
            }
        }

        return -1;
    }

    private static boolean isDivided(int target, int divided) {
        return target % divided == 0;
    }

    private static boolean isVisited(int index) {
        return check[index];
    }

    private static void addQueue(Queue<Pair> queue, int number, int count, int target) {
        queue.offer(new Pair(number, count));
        check[number] = true;
        before[number] = target;
    }

    private static Stack<Integer> stack() {
        Stack<Integer> stack = new Stack<>();
        int number = 1;
        while (!isArrayOutOfIndex(number, before)) {
            if (number == 0) {
                break;
            }

            stack.push(number);
            number = before[number];
        }

        return stack;
    }

    private static boolean isArrayOutOfIndex(int number, int[] array) {
        return number < 0 || array.length < number;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static class Pair {
        int number;
        int count;

        public Pair(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }
}