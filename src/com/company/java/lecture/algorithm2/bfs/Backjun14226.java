package com.company.java.lecture.algorithm2.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14226
public class Backjun14226 {
    private static final String[] array = {
            "2",
            "4",
            "6",
            "18"
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
        int s = Integer.parseInt(br.readLine());
        int max = s + 1;
        int[][] time = new int[max][max];
        for (int i = 0; i <= s; i++) {
            Arrays.fill(time[i], -1);
        }

        int answer = bfs(s, time);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int s, int[][] time) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(1, 0));
        time[1][0] = 0;
        int answer = 0;
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int v = p.v;
            int c = p.c;
            if (v == s) {
                answer = time[v][c];
                break;
            }

            int[] vt = new int[]{v, v + c, v - 1};
            int[] ct = new int[]{v, c, c};
            for (int i = 0; i < 3; i++) {
                if (0 <= vt[i] && vt[i] <= s && time[vt[i]][ct[i]] == -1) {
                    q.add(new Pair(vt[i], ct[i]));
                    time[vt[i]][ct[i]] = time[v][c] + 1;
                }
            }
        }

        return answer;
    }

    private static class Pair {
        int v;
        int c;

        public Pair(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }
}
