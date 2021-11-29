package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/6549
public class Backjun6549 {
    private static int N;

    private static final String[] array = {
            "7 2 1 4 5 1 3 3\n" +
            "4 1000 1000 1000 1000\n" +
            "0"
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

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());
            if (N == 0) {
                break;
            }

            long[] a = new long[N];
            for (int i = 0; i < N; i++) {
                a[i] = stoi(st.nextToken());
            }

            Stack<Pair> stack = new Stack<>();
            long answer = 0;
            for (int i = 0; i < N; i++) {
                while (!stack.isEmpty() && a[i] < stack.peek().num) {
                    Pair pair = stack.pop();
                    long temp = i;
                    if (!stack.isEmpty()) {
                        temp = (i - stack.peek().index - 1);
                    }

                    answer = Math.max(answer, pair.num * temp);
                }

                stack.push(new Pair(a[i], i));
            }

            while (!stack.isEmpty()) {
                Pair pair = stack.pop();
                long temp = N;
                if (!stack.isEmpty()) {
                    temp = (N - stack.peek().index - 1);
                }

                answer = Math.max(answer, pair.num * temp);
            }

            bw.write(String.valueOf(answer));
            bw.write("\n");
        }

        bw.flush();
    }

    private static class Pair {
        long num;
        int index;
        public Pair(long num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {

    }
}