package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/3015
public class Backjun3015 {
    private static int N;
    private static final String[] array = {
            "7\n" +
            "2\n" +
            "4\n" +
            "1\n" +
            "2\n" +
            "2\n" +
            "5\n" +
            "1"
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
        N = stoi(br.readLine());
        long[] a = new long[N];
        for (int i = 0; i < N; i++) {
            a[i] = stol(br.readLine());
        }

        long answer = 0;
        Stack<Pair> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int cnt = 1;
            while (!stack.isEmpty()) {
                if (stack.peek().h <= a[i]) {
                    answer += stack.peek().cnt;
                    if (stack.peek().h == a[i]) {
                        cnt += stack.peek().cnt;
                    }

                    stack.pop();
                } else {
                    break;
                }
            }

            if (!stack.isEmpty()) {
                answer++;
            }

            stack.push(new Pair(a[i], cnt));
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static class Pair {
        long h;
        long cnt;
        public Pair(long h, long cnt) {
            this.h = h;
            this.cnt = cnt;
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static long stol(String s) {
        return Long.parseLong(s);
    }
}