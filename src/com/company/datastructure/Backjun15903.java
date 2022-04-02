package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/15903
public class Backjun15903 {
    private static final String[] array = {
            "3 1\n" +
            "3 2 6",
            "4 2\n" +
            "4 2 3 1",
            "3 4\n" +
            "1000000 1000000 1000000"
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
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        PriorityQueue<Long> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            q.offer(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            long a1 = q.poll();
            long a2 = q.poll();
            q.offer(a1 + a2);
            q.offer(a1 + a2);
        }

        int answer = 0;
        while (!q.isEmpty()) {
            answer += q.poll();
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
