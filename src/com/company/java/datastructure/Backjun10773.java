package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10773
public class Backjun10773 {
    private static final String[] array = {
            "4\n" +
            "3\n" +
            "0\n" +
            "4\n" +
            "0",
            "10\n" +
            "1\n" +
            "3\n" +
            "5\n" +
            "4\n" +
            "0\n" +
            "0\n" +
            "7\n" +
            "0\n" +
            "0\n" +
            "6"
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
        Stack<Integer> stack = new Stack<>();
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                stack.pop();
            } else {
                stack.push(n);
            }
        }

        int answer = 0;
        while (!stack.isEmpty()) {
            answer += stack.pop();
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
