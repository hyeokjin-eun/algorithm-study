package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/5430
public class Backjun5430 {
    private static final String[] array = {
            "4\n" +
            "RDD\n" +
            "4\n" +
            "[1,2,3,4]\n" +
            "DD\n" +
            "1\n" +
            "[42]\n" +
            "RRD\n" +
            "6\n" +
            "[1,1,2,3,5,8]\n" +
            "D\n" +
            "0\n" +
            "[]"
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
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            char[] c = br.readLine().toCharArray();
            int N = Integer.parseInt(br.readLine());
            Deque<Integer> deque = new ArrayDeque<>();
            String[] s = br.readLine().split("[^0-9]");
            for (int i = 1; i <= N; i++) {
                deque.offer(Integer.parseInt(s[i]));
            }

            int toggle = 0;
            boolean check = true;
            for (int i = 0; i < c.length; i++) {
                if (c[i] == 'R') {
                    toggle = toggle == 0 ? 1 : 0;
                } else {
                    if (deque.isEmpty()) {
                        check = false;
                        break;
                    }

                    if (toggle == 0) {
                        deque.pollFirst();
                    } else {
                        deque.pollLast();
                    }
                }
            }

            if (check) {
                bw.write("[");
                while (!deque.isEmpty()) {
                    if (toggle == 0) {
                        bw.write(String.valueOf(deque.pollFirst()));
                    } else {
                        bw.write(String.valueOf(deque.pollLast()));
                    }

                    if (!deque.isEmpty()) {
                        bw.write(",");
                    }
                }

                bw.write("]");
            } else {
                bw.write("error");
            }

            if (t != T - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }
}