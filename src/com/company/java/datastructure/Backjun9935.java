package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/9935
public class Backjun9935 {
    private static final String[] array = {
            "mirkovC4nizCC44\n" +
            "C4",
            "12ab112ab2ab\n" +
            "12ab"
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
        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();
        boolean[] check = new boolean[s1.length];
        if (s2.length == 1) {
            for (int i = 0; i < s1.length; i++) {
                if (s1[i] == s2[0]) {
                    check[i] = true;
                }
            }
        } else {
            Stack<Integer> stack1 = new Stack<>();
            Stack<Integer> stack2 = new Stack<>();
            for (int i = 0; i < s1.length; i++) {
                if (s1[i] == s2[0]) {
                    stack1.push(i);
                    stack2.push(0);
                } else {
                    if (stack2.isEmpty()) {
                        continue;
                    }

                    if (s1[i] == s2[stack2.peek() + 1]) {
                        stack1.push(i);
                        stack2.push(stack2.peek() + 1);
                        if (stack2.peek() == s2.length - 1) {
                            for (int j = 0; j < s2.length; j++) {
                                check[stack1.pop()] = true;
                                stack2.pop();
                            }
                        }
                    } else {
                        while (!stack1.isEmpty()) {
                            stack1.pop();
                        }

                        while (!stack2.isEmpty()) {
                            stack2.pop();
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s1.length; i++) {
            if (!check[i]) {
                sb.append(s1[i]);
            }
        }

        if (sb.toString().length() == 0) {
            bw.write("FRULA");
        } else {
            bw.write(sb.toString());
        }

        bw.flush();
    }
}