package com.company.lecture.algorithm2.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/13913
public class Backjun13913 {
    private static final String[] array = {
            "5 17"
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
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int max = Math.max(n, k) + 2;
        int[] time = new int[max];
        int[] pre = new int[max];
        boolean[] check = new boolean[max];
        bw.write(String.valueOf(bfs(n, k, max, time, pre, check)));
        bw.write("\n");
        Stack<Integer> answer = new Stack<>();
        for (int i = k; i != n; i = pre[i]) {
            answer.push(i);
        }

        answer.push(n);
        while (!answer.isEmpty()) {
            bw.write(String.valueOf(answer.pop()));
            bw.write(" ");
        }

        bw.flush();
    }

    private static int bfs(int n, int k, int max, int[] time, int[] pre, boolean[] check) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        time[n] = 0;
        check[n] = true;
        while (!q.isEmpty()) {
            int x = q.poll();
            int[] temp = new int[]{x - 1, x + 1, x * 2};
            for (int i = 0; i < 3; i++) {
                if (0 <= temp[i] && temp[i] < max && !check[temp[i]]) {
                    q.add(temp[i]);
                    time[temp[i]] = time[x] + 1;
                    check[temp[i]] = true;
                    pre[temp[i]] = x;
                }
            }
        }

        return time[k];
    }
}
