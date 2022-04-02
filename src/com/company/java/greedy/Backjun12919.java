package com.company.java.greedy;

import java.io.*;

// link
// https://www.acmicpc.net/problem/12919
public class Backjun12919 {
    private static final String[] array = {
            "A\n" +
            "BABA",
            "BAAAAABAA\n" +
            "BAABAAAAAB",
            "A\n" +
            "ABBA"
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
        String s = br.readLine();
        String e = br.readLine();
        bw.write(recursion(s, e) ? "1" : "0");
        bw.flush();
    }

    private static boolean recursion(String s, String e) {
        if (s.equals(e)) {
            return true;
        }

        if (e.length() > 0) {
            if (e.charAt(e.length() - 1) == 'A' && recursion(s, e.substring(0, e.length() - 1))) {
                return true;
            }

            StringBuilder sb = new StringBuilder(e);
            sb.reverse();
            sb.deleteCharAt(e.length() - 1);
            if (e.charAt(0) == 'B' && recursion(s, sb.toString())) {
                return true;
            }
        }

        return false;
    }
}