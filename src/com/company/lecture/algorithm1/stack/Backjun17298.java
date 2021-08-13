package com.company.lecture.algorithm1.stack;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/17298
public class Backjun17298 {
    private static final String[] array = {
            "4\n" +
            "3 5 2 7",
            "8\n" +
            "4 2 7 8 5 9 3 6"
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
        int arrayLength = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] intArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            intArray[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i< intArray.length; i++) {
            if (stack.empty()) {
                stack.push(i);
                continue;
            }

            if (intArray[stack.peek()] > intArray[i]) {
                stack.push(i);
            } else {
                while (!stack.empty()) {
                    if (intArray[stack.peek()] < intArray[i]) {
                        intArray[stack.pop()] = intArray[i];
                    } else {
                        break;
                    }
                }

                stack.push(i);
            }
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
