package com.company.programmers;

import java.io.*;
import java.util.*;

public class program_print {
    private static final String[] array = {
            "4\n" +
            "2\n" +
            "2 1 3 2"
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
        int location = Integer.parseInt(br.readLine());
        int[] priorities = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            priorities[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Pair> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.d));
        Queue<Pair> d = new LinkedList<>();
        while (!q.isEmpty()) {


        }
    }

    private static class Pair {
        int i;
        int d;
        public Pair(int i, int d) {
            this.i = i;
            this.d = d;
        }
    }
}
