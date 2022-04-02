package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1939
public class Backjun1939 {
    private static final String[] array = {
            "3 3\n" +
            "1 2 2\n" +
            "3 1 3\n" +
            "2 3 2\n" +
            "1 3"
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
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Pair>> a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            a.add(new ArrayList<>());
        }

        int max = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());
            a.get(A).add(new Pair(B, C));
            a.get(B).add(new Pair(A, C));
            max = Math.max(max, C);
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()) - 1;
        int e = Integer.parseInt(st.nextToken()) - 1;
        int answer = binarySearch(a, s, e, max);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int binarySearch(ArrayList<ArrayList<Pair>> a, int s, int e, int max) {
        int left = 1;
        int right = max;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (bfs(a, s, e, mid)) {
                answer = Math.max(mid, answer);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static boolean bfs(ArrayList<ArrayList<Pair>> a, int s, int e, int w) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        boolean[] check = new boolean[a.size()];
        check[s] = true;
        while (!queue.isEmpty()) {
            int c = queue.poll();
            if (e == c) {
                return true;
            }

            for (int i = 0; i < a.get(c).size(); i++) {
                Pair pair = a.get(c).get(i);
                int to = pair.to;
                int weight = pair.weight;
                if (!check[to] && w <= weight) {
                    queue.offer(to);
                    check[to] = true;
                }
            }
        }

        return false;
    }

    private static class Pair {
        int to;
        int weight;
        public Pair(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}

