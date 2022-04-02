package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1021
public class Backjun1021 {
    private static final String[] array = {
            "10 3\n" +
            "1 2 3"
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
        LinkedList<Integer> deque = new LinkedList<>();
        int answer = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            deque.offer(i);
        }

        int[] seq = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            int ti = deque.indexOf(seq[i]);
            int hi;
            if (deque.size() % 2 == 0) hi = deque.size() / 2 - 1;
            else hi = deque.size() / 2;
            if (ti <= hi) {
                for (int j = 0; j < ti; j++) {
                    int temp = deque.pollFirst();
                    deque.offerLast(temp);
                    answer++;
                }
            } else {
                for (int j = 0; j < deque.size() - ti; j++) {
                    int temp = deque.pollLast();
                    deque.offerFirst(temp);
                    answer++;
                }
            }

            deque.pollFirst();
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}