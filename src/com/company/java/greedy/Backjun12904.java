package com.company.java.greedy;

import java.io.*;

public class Backjun12904 {
    private static final String[] array = {
            "B\n" +
            "ABBA",
            "AB\n" +
            "ABB",
            "A\n" +
            "BBAB",
            "A\n" +
            "BABB"
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
        StringBuilder temp = new StringBuilder(e);
        for (int i = e.length() - 1; s.length() <= i; i--) {
            char end = temp.toString().charAt(i);
            if (end == 'B') {
                temp.deleteCharAt(i);
                temp.reverse();
            } else {
                temp.deleteCharAt(i);
            }
        }

        if (s.equals(temp.toString())) {
            bw.write(String.valueOf(1));
        } else {
            bw.write(String.valueOf(0));
        }

        bw.flush();
    }
}
