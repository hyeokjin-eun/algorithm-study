package com.company.lecture.algorithm1.stack;

import java.io.*;
import java.util.Objects;
import java.util.Stack;

// link
// https://www.acmicpc.net/problem/9093
public class Backjun9093 {
    private static final String[] array = {
            "2\n" +
            "I am happy today\n" +
            "We want to win the first prize"
    };

    public static void main (String[] args) throws IOException {
        for (int i = 0; i < array.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            long before = System.currentTimeMillis();
            solution(array[i]);
            long after = System.currentTimeMillis();
            System.out.println("===== Time : " + (after - before) + "   =====");
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static void solution(String input) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        int index = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < index; i++) {
            String inputString = bufferedReader.readLine() + "\n";
            Stack<Character> stack = new Stack<>();
            for (int j = 0; j < inputString.length(); j++) {
                if (Objects.equals(' ',inputString.charAt(j)) || Objects.equals('\n', inputString.charAt(j))) {
                    while (stack.size() > 0) {
                        bufferedWriter.write(stack.pop());
                    }

                    bufferedWriter.write(" ");
                } else {
                    stack.push(inputString.charAt(j));
                }
            }
            bufferedWriter.write("\n");
        }

        bufferedWriter.flush();
    }
}
