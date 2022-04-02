package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11279
public class Backjun11279 {
    private static final String[] array = {
            "13\n" +
            "0\n" +
            "1\n" +
            "2\n" +
            "0\n" +
            "0\n" +
            "3\n" +
            "2\n" +
            "1\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0"
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
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (queue.isEmpty()) {
                    bw.write(String.valueOf(0));
                } else {
                    bw.write(String.valueOf(queue.poll()));
                }

                bw.write("\n");
            } else {
                queue.offer(x);
            }
        }

        bw.flush();
    }
}
