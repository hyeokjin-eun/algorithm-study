package com.company.java.lecture.algorithm1.math;

import java.io.*;
import java.util.Stack;

// link
// https://www.acmicpc.net/problem/11576
public class Backjun11576 {
    private static final String[] array = {
            "17 8\n" +
            "2\n" +
            "2 16"
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
        String[] binaryArray = br.readLine().split(" ");
        int a = Integer.parseInt(binaryArray[0]);
        int b = Integer.parseInt(binaryArray[1]);
        int index = Integer.parseInt(br.readLine());
        String[] binaryA = br.readLine().split(" ");
        int decimal = decimal(a, binaryA, index);
        String binaryB = decimalBinaryConverter(b, decimal);
        bw.write(binaryB);
        bw.flush();
    }

    private static int decimal (int binary, String[] input, int index) {
        int decimal = 0;
        for (int i = 0; i < index; i++) {
            decimal = decimal * binary + Integer.parseInt(input[i]);
        }

        return decimal;
    }

    private static String decimalBinaryConverter (int binary, int decimal) {
        Stack<Integer> stack = new Stack<>();
        int temp = decimal;
        while (temp != 0) {
            stack.push(temp % binary);
            temp = temp / binary;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop());
            if (!stack.empty()) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}
