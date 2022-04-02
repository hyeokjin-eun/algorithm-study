package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/5014
public class Backjun5014 {
    private static int F;
    private static int S;
    private static int G;
    private static int U;
    private static int D;
    private static final String[] array = {
            "10 1 10 2 1",
            "100 2 1 1 0",
            "1000000 1 1000000 1 1"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = stoi(st.nextToken());
        S = stoi(st.nextToken());
        G = stoi(st.nextToken());
        U = stoi(st.nextToken());
        D = stoi(st.nextToken());
        int answer = bfs();
        bw.write(String.valueOf(answer == -1 ? "use the stairs" : answer));
        bw.flush();
    }

    private static int bfs() {
        boolean[] check = new boolean[F + 1];
        int[] dist = new int[F + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(S);
        check[S] = true;
        dist[S] = 0;
        int answer = -1;
        while (!queue.isEmpty()) {
            int c = queue.poll();
            if (c == G) {
                return dist[c];
            }

            if (c + U <= F && !check[c + U]) {
                check[c + U] = true;
                dist[c + U] = dist[c] + 1;
                queue.offer(c + U);
            }

            if (0 < c - D && !check[c - D]) {
                check[c - D] = true;
                dist[c - D] = dist[c] + 1;
                queue.offer(c - D);
            }
        }

        return answer;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}