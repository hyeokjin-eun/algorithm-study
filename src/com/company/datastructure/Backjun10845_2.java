package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10845
public class Backjun10845_2 {
    private static Deque<Integer> queue;
    private static final String[] array = {
            "15\n" +
            "push 1\n" +
            "push 2\n" +
            "front\n" +
            "back\n" +
            "size\n" +
            "empty\n" +
            "pop\n" +
            "pop\n" +
            "pop\n" +
            "size\n" +
            "empty\n" +
            "pop\n" +
            "push 3\n" +
            "empty\n" +
            "front"
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
        queue = new LinkedList<>();
        int N = stoi(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            if (s[0].equals("push")) {
                queue.offer(stoi(s[1]));
            } else if (s[0].equals("pop")) {
                if (queue.isEmpty()) {
                    bw.write(String.valueOf(-1));
                } else {
                    bw.write(String.valueOf(queue.pollFirst()));
                }

                bw.write("\n");
            } else if (s[0].equals("size")) {
                bw.write(String.valueOf(queue.size()));
                bw.write("\n");
            } else if (s[0].equals("empty")) {
                if (queue.isEmpty()) {
                    bw.write(String.valueOf(1));
                } else {
                    bw.write(String.valueOf(0));
                }

                bw.write("\n");
            } else if (s[0].equals("front")) {
                if (queue.isEmpty()) {
                    bw.write(String.valueOf(-1));
                } else {
                    bw.write(String.valueOf(queue.peekFirst()));
                }

                bw.write("\n");
            } else if (s[0].equals("back")) {
                if (queue.isEmpty()) {
                    bw.write(String.valueOf(-1));
                } else {
                    bw.write(String.valueOf(queue.peekLast()));
                }

                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}