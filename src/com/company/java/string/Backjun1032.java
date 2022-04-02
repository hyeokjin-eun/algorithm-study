package com.company.java.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1032
public class Backjun1032 {
    private static final String[] array = {
            "3\n" +
            "config.sys\n" +
            "config.inf\n" +
            "configures",
            "2\n" +
            "contest.txt\n" +
            "context.txt",
            "3\n" +
            "c.user.mike.programs\n" +
            "c.user.nike.programs\n" +
            "c.user.rice.programs",
            "4\n" +
            "a\n" +
            "a\n" +
            "b\n" +
            "b",
            "1\n" +
            "onlyonefile"
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

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int N = Integer.parseInt(br.readLine());
        char[] a = br.readLine().toCharArray();
        for (int i = 1; i < N; i++) {
            char[] t = br.readLine().toCharArray();
            for (int j = 0; j < t.length; j++) {
                if (a[j] != t[j]) {
                    a[j] = '?';
                }
            }
        }

        bw.write(String.valueOf(a));
        bw.flush();
    }
}