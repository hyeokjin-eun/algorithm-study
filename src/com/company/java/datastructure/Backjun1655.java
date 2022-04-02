package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1655
public class Backjun1655 {
    private static int N;
    private static final String[] array = {
            "7\n" +
            "1\n" +
            "5\n" +
            "2\n" +
            "10\n" +
            "-99\n" +
            "7\n" +
            "5"
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
        N = stoi(br.readLine());
        PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();
        while (N-- != 0) {
            int num = stoi(br.readLine());
            if (max.isEmpty() || min.isEmpty()) {
                max.offer(num);
            } else {
                if (num <= max.peek()) {
                    max.offer(num);
                } else if (num >= min.peek()) {
                    min.offer(num);
                } else {
                    max.offer(num);
                }
            }

            while (!(max.size() == min.size() || max.size() == min.size() + 1)) {
                if (max.size() > min.size()) {
                    min.offer(max.poll());
                } else {
                    max.offer(min.poll());
                }
            }

            bw.write(String.valueOf(max.peek()));
            if (N != 0) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}