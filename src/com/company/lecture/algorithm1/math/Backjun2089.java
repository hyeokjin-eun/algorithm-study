package com.company.lecture.algorithm1.math;

import java.io.*;
import java.util.Stack;

// link
// https://www.acmicpc.net/problem/2089
public class Backjun2089 {
    private static final String[] array = {
            "-13"
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
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        Stack<Integer> stack = new Stack<>();
        int num = Integer.parseInt(br.readLine());
        if (num == 0) {
            bw.write(String.valueOf(num));
        }

        while (num != 0) {
            stack.push(num % -2 > 0 ? (num % -2) : (num % -2) * -1);
            num = (int) Math.ceil((double) num / -2);
        }

        while (!stack.empty()) {
            bw.write(String.valueOf(stack.pop()));
        }

        bw.flush();
    }
}
