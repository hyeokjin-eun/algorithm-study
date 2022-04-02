package com.company.lecture.algorithm1.math;

import java.io.*;
import java.util.Stack;

// link
// https://www.acmicpc.net/problem/11005
public class Backjun11005 {
    private static final char[] temp = {
            '0','1','2','3','4','5',
            '6','7','8','9','A','B',
            'C','D','E','F','G','H',
            'I','J','K','L','M','N',
            'O','P','Q','R','S','T',
            'U','V','W','X','Y','Z'
    };

    private static final String[] array = {
            "60466175 36"
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
        String[] nums = br.readLine().split(" ");
        int num = Integer.parseInt(nums[0]);
        int mod = Integer.parseInt(nums[1]);
        Stack<Character> stack = new Stack<>();
        while (num != 0) {
            stack.push(temp[num % mod]);
            num = num / mod;
        }

        while (!stack.empty()) {
            bw.write(stack.pop());
        }

        bw.flush();
    }
}
