package com.company.lecture.algorithm1.stack;

import java.io.*;
import java.util.Stack;

// link
// https://www.acmicpc.net/problem/17413
public class Backjun17413 {
    private static final String[] array = {
            "baekjoon online judge",
            "<open>tag<close>",
            "<int><max>2147483647<long long><max>9223372036854775807",
            "<ab cd>ef gh<ij kl>",
            "one1 two2 three3 4four 5five 6six",
            "<problem>17413<is hardest>problem ever<end>",
            "<   space   >space space space<    spa   c e>"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr =  new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        char[] charArray = (br.readLine() + "\n").toCharArray();
        boolean tagCheck = true;
        Stack<Character> stack = new Stack<>();
        for (char charInput : charArray) {
            if (charInput == '<') {
                while (!stack.empty()) {
                    bw.write(stack.pop());
                }

                tagCheck = false;
                bw.write(charInput);
            } else if (charInput == '>') {
                tagCheck = true;
                bw.write(charInput);
            } else if (charInput == ' ' || charInput == '\n') {
                if (tagCheck) {
                    while (!stack.empty()) {
                        bw.write(stack.pop());
                    }

                    bw.write(' ');
                } else {
                    bw.write(charInput);
                }
            } else {
                if (tagCheck) {
                    stack.push(charInput);
                } else {
                    bw.write(charInput);
                }
            }
        }

        bw.flush();
    }
}
