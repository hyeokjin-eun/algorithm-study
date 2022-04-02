package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/4889
public class Backjun4889 {
    private static final String[] array = {
            "}{\n" +
            "{}{}{}\n" +
            "{{{}\n" +
            "}}{{\n" +
            "---"
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
        int t = 1;
        while (true) {
            String s = br.readLine();
            if (s.contains("-")) {
                break;
            }

            char[] c = s.toCharArray();
            Stack<Character> stack = new Stack<>();
            int answer = 0;
            for (int i = 0; i < c.length; i++) {
                if (c[i] == '{') {
                    stack.push('{');
                } else {
                    if (stack.isEmpty()) {
                        answer++;
                        stack.push('{');
                    } else {
                        stack.pop();
                    }
                }
            }

            while (!stack.isEmpty()) {
                stack.pop();
                stack.pop();
                answer++;
            }

            bw.write(t + ". ");
            bw.write(String.valueOf(answer));
            bw.write("\n");
            t++;
        }

        bw.flush();
    }
}
