package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2606
// TODO: 2021-10-03 확인 필요
public class Backjun2606 {
    private static final String[] array = {
            "7\n" +
            "6\n" +
            "1 2\n" +
            "2 3\n" +
            "1 5\n" +
            "5 2\n" +
            "5 6\n" +
            "4 7"
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
        int n = Integer.parseInt(br.readLine());
        int line = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < line; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            a.get(from).add(to);
            a.get(to).add(from);
        }

        int[] time = new int[n];
        boolean[] check = new boolean[n];
        int answer = bfs(check, time, n, a);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(boolean[] check, int[] time, int n, ArrayList<ArrayList<Integer>> a) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        check[0] = true;
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int i = 0; i < a.get(x).size(); i++) {
                if (!check[a.get(x).get(i)]) {
                    check[a.get(x).get(i)] = true;
                    time[a.get(x).get(i)] = time[x] + 1;
                    q.add(a.get(x).get(i));
                }
            }
        }

        int answer = 0;
        for (int i = 1; i < n; i++) {
            if (0 < time[i]) {
                answer++;
            }
        }

        return answer;
    }
}
