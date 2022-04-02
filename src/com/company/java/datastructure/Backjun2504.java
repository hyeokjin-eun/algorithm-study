package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2504
// TODO: 2021/10/02 재구현 필요
public class Backjun2504 {
    private static final String[] array = {
            "(()[[]])([])"
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
        char[] a = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        int answer = 0;
        int temp = 1;
        for (int i = 0; i < a.length; i++) {
            char c = a[i];
            if (c == '(') {
                stack.push(c);
                temp *= 2;
            } else if (c == '[') {
                stack.push(c);
                temp *= 3;
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    answer = 0;
                    break;
                }

                if (a[i - 1] == '(') {
                    answer += temp;
                }

                stack.pop();
                temp /= 2;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    answer = 0;
                    break;
                }

                if (a[i - 1] == '[') {
                    answer += temp;
                }

                stack.pop();
                temp /= 3;
            }
        }

        if (!stack.isEmpty()) {
            bw.write(String.valueOf(0));
        } else {
            bw.write(String.valueOf(answer));
        }

        bw.flush();
    }
}
