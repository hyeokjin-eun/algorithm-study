package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/18258
public class Backjun18258 {
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
        int N = stoi(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push" :
                    int num = stoi(st.nextToken());
                    deque.offer(num);
                    break;
                case "pop" :
                    if (deque.isEmpty()) {
                        bw.write(String.valueOf(-1));
                    } else {
                        bw.write(String.valueOf(deque.poll()));
                    }

                    bw.write("\n");
                    break;
                case "size" :
                    bw.write(String.valueOf(deque.size()));
                    bw.write("\n");
                    break;
                case "empty" :
                    if (deque.isEmpty()) {
                        bw.write(String.valueOf(1));
                    } else {
                        bw.write(String.valueOf(0));
                    }

                    bw.write("\n");
                    break;
                case "front" :
                    if (deque.isEmpty()) {
                        bw.write(String.valueOf(-1));
                    } else {
                        bw.write(String.valueOf(deque.peekFirst()));
                    }

                    bw.write("\n");
                    break;
                case "back" :
                    if (deque.isEmpty()) {
                        bw.write(String.valueOf(-1));
                    } else {
                        bw.write(String.valueOf(deque.peekLast()));
                    }

                    bw.write("\n");
                    break;
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}