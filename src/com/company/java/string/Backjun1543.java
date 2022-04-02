package com.company.java.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1543
public class Backjun1543 {
    private static final String[] array = {
            "ababababa\n" +
            "aba",
            "a a a a a\n" +
            "a a",
            "ababababa\n" +
            "ababa",
            "aaaaaaa\n" +
            "aa"
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
        String s = br.readLine();
        String p = br.readLine();
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(p, i) != -1) {
                answer++;
                i = s.indexOf(p, i) - 1 + p.length();
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}