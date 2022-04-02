package com.company.java.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1068
public class Backjun1068 {
    private static final String[] array = {
            "5\n" +
            "-1 0 0 1 1\n" +
            "2",
            "5\n" +
            "-1 0 0 1 1\n" +
            "1",
            "5\n" +
            "-1 0 0 1 1\n" +
            "0",
            "9\n" +
            "-1 0 0 2 2 4 4 6 6\n" +
            "4"
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
        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            a.add(new ArrayList<>());
        }

        int root = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (n == -1) {
                root = i;
            } else {
                a.get(n).add(i);
            }
        }

        int r = Integer.parseInt(br.readLine());
        int answer = bfs(root, r, a);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int root, int r, ArrayList<ArrayList<Integer>> a) {
        if (root == r) {
            return 0;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        int answer = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            int cnt = 0;
            for (int i = 0; i < a.get(x).size(); i++) {
                if (a.get(x).get(i) == r) {
                    continue;
                }

                q.add(a.get(x).get(i));
                cnt++;
            }

            if (cnt == 0) {
                answer++;
            }
        }

        return answer;
    }
}
