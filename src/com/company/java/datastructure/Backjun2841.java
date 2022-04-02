package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2841
public class Backjun2841 {
    private static final String[] array = {
            "5 15\n" +
            "2 8\n" +
            "2 10\n" +
            "2 12\n" +
            "2 10\n" +
            "2 5",
            "7 15\n" +
            "1 5\n" +
            "2 3\n" +
            "2 5\n" +
            "2 7\n" +
            "2 4\n" +
            "1 5\n" +
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
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        ArrayList<PriorityQueue<Integer>> a = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            a.add(new PriorityQueue<>(Comparator.reverseOrder()));
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken()) - 1;
            int flat = Integer.parseInt(st.nextToken());
            if (a.get(key).isEmpty()) {
                answer++;
                a.get(key).add(flat);
            } else {
                PriorityQueue<Integer> q = a.get(key);
                while (!q.isEmpty() && flat < q.peek()) {
                    answer++;
                    q.poll();
                }

                if (q.isEmpty()) {
                    answer++;
                    q.add(flat);
                } else {
                    if (flat != q.peek()) {
                        answer++;
                        q.add(flat);
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
