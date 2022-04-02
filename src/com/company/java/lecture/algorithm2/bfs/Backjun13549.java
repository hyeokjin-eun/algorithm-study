package com.company.java.lecture.algorithm2.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/13549
public class Backjun13549 {
    private static final String[] array = {
            "5 17",
            "17 5",
            "0 3",
            "0 0",
            "3 0",
            "1 2"
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
        boolean[] check = new boolean[max];
        int answer = bfs(n, k, max, time, check);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int n, int k, int max, int[] time, boolean[] check) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        check[n] = true;
        time[n] = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            if (x * 2 < max && !check[x * 2]) {
                q.add(x * 2);
                check[x * 2] = true;
                time[x * 2] = time[x];
            }

            if (0 <= x - 1 && !check[x - 1]) {
                q.add(x - 1);
                check[x - 1] = true;
                time[x - 1] = time[x] + 1;
            }

            if (x + 1 < max && !check[x + 1]) {
                q.add(x + 1);
                check[x + 1] = true;
                time[x + 1] = time[x] + 1;
            }
        }

        return time[k];
    }
}
