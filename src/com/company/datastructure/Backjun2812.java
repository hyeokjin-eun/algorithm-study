package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2812
public class Backjun2812 {
    private static final String[] array = {
            "4 2\n" +
            "1924",
            "7 3\n" +
            "1231234",
            "10 4\n" +
            "4177252841",
            "4 2\n" +
            "4444"
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
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String[] s = br.readLine().split("");
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(s[i]);
        }

        boolean[] check = new boolean[N];
        Stack<Pair> stack = new Stack<>();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (stack.isEmpty()) {
                stack.push(new Pair(i, a[i]));
            } else {
                while (cnt != K && !stack.isEmpty() && stack.peek().n < a[i]) {
                    check[stack.peek().i] = true;
                    stack.pop();
                    cnt++;
                }

                stack.push(new Pair(i, a[i]));
            }
        }

        while (cnt != K) {
            check[stack.peek().i] = true;
            stack.pop();
            cnt++;
        }

        for (int i = 0; i < N; i++) {
            if (!check[i]) {
                bw.write(String.valueOf(a[i]));
            }
        }

        bw.flush();
    }

    private static class Pair {
        int i;
        int n;
        public Pair(int i, int n) {
            this.i = i;
            this.n = n;
        }
    }
}
