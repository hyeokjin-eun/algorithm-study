package com.company.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/4999
public class Backjun4999 {
    private static final String[] array = {
            "aaah\n" +
            "aaaaah",
            "aaah\n" +
            "ah"
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
        String a = br.readLine();
        String b = br.readLine();
        if (b.length() <= a.length()) {
            bw.write("go");
        } else {
            bw.write("no");
        }

        bw.flush();
    }
}