package com.company.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2744
public class Backjun2744 {
    private static final String[] array = {
            "WrongAnswer"
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
        char[] chars = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (65 <= chars[i] && chars[i] <= 90) {
                sb.append((char) (chars[i] - 'A' + 'a'));
            } else if (97 <= chars[i] && chars[i] <= 122) {
                sb.append((char) (chars[i] - 'a' + 'A'));
            } else {
                sb.append(chars[i]);
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }
}