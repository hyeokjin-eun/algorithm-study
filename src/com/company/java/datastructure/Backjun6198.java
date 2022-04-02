package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/6198
public class Backjun6198 {
    private static final String[] array = {
            "6\n" +
            "10\n" +
            "3\n" +
            "7\n" +
            "4\n" +
            "12\n" +
            "2"
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
        int N = Integer.parseInt(br.readLine());
        Stack<Pair> stack = new Stack<>();
        long answer = 0;
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (stack.isEmpty()) {
                stack.push(new Pair(h, i));
            } else {
                while (!stack.isEmpty() && stack.peek().h <= h) {
                    Pair pair = stack.pop();
                    answer += i - pair.i - 1;
                }

                stack.push(new Pair(h, i));
            }
        }

        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            answer += N - pair.i - 1;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static class Pair {
        int h;
        int i;
        public Pair(int h, int i) {
            this.h = h;
            this.i = i;
        }
    }
}


