package com.company.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2109
public class Backjun2109 {
    private static final String[] array = {
            "7\n" +
            "20 1\n" +
            "2 1\n" +
            "10 3\n" +
            "100 2\n" +
            "8 2\n" +
            "5 20\n" +
            "50 10",
            "4\n" +
            "20 2\n" +
            "30 2\n" +
            "40 3\n" +
            "40 3",
            "3\n" +
            "1 1\n" +
            "10 2\n" +
            "10 2"
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
        Pair[] pairs = new Pair[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pairs[i] = new Pair(p, d);
        }

        Arrays.sort(pairs);
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int index = 0;
        for (int i = 10000; 1 <= i; i--) {
            while (index < N && pairs[index].d == i) {
                queue.offer(pairs[index].p);
                index++;
            }

            if (!queue.isEmpty()) {
                answer += queue.poll();
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static class Pair implements Comparable<Pair> {
        int p;
        int d;
        public Pair(int p, int d) {
            this.p = p;
            this.d = d;
        }

        @Override
        public int compareTo(Pair o) {
            if (o.d == this.d) return this.p - o.p;
            return o.d - this.d;
        }
    }
}
