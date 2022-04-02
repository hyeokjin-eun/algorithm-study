package com.company.java.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1202
public class Backjun1202 {
    private static final String[] array = {
            "2 1\n" +
            "5 10\n" +
            "100 100\n" +
            "11",
            "3 2\n" +
            "1 65\n" +
            "5 23\n" +
            "2 99\n" +
            "10\n" +
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 보석 갯수 : N
        int N = Integer.parseInt(st.nextToken());
        // 가방 갯수 : K     * 가방엔 최대 1개의 보석만 담기 가능
        int K = Integer.parseInt(st.nextToken());

        Pair[] temp = new Pair[N + K];
        // 보석 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            temp[i] = new Pair(w, p, 0);
        }

        // 가방 정보
        for (int i = 0; i < K; i++) {
            int w = Integer.parseInt(br.readLine());
            temp[N + i] = new Pair(w, 0, 1);
        }

        // 정렬 (t == 가방 ? 1 : 0)
        Arrays.sort(temp, (o1, o2) -> {
            if (o1.w == o2.w) {
                return o1.t - o2.t;
            }

            return o1.w - o2.w;
        });

        long answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (Pair pair : temp) {
            if (pair.t == 1) {
                if (!queue.isEmpty()) {
                    answer += queue.poll();
                }
            } else {
                queue.offer(pair.p);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static class Pair {
        // 무게
        int w;
        // 가격
        int p;
        // 타입
        int t;
        public Pair(int w, int p, int t) {
            this.w = w;
            this.p = p;
            this.t = t;
        }
    }
}