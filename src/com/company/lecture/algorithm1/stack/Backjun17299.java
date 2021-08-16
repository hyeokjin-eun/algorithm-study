package com.company.lecture.algorithm1.stack;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Stack;

// link
// https://www.acmicpc.net/problem/17299
public class Backjun17299 {
    private static final String[] array = {
            "7\n" +
            "1 1 2 3 4 2 1"
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
        int intLength = Integer.parseInt(br.readLine());
        int[] intArray = new int[intLength];
        int[] countArray = new int[1000001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < intLength; i++) {
            int intInput = Integer.parseInt(st.nextToken());
            intArray[i] = intInput;
            countArray[intInput] += 1;
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < intLength; i++) {
            while (!stack.empty() && countArray[intArray[stack.peek()]] < countArray[intArray[i]]) {
                intArray[stack.pop()] = intArray[i];
            }

            stack.push(i);
        }

        while (!stack.empty()) {
            intArray[stack.pop()] = -1;
        }

        for (int num : intArray) {
            bw.write(String.valueOf(num));
            bw.write(" ");
        }

        bw.flush();
    }
}
