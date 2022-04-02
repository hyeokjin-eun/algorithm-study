package com.company.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2902
public class Backjun2902 {
    private static final String[] array = {
            "Knuth-Morris-Pratt",
            "Mirko-Slavko",
            "Pasko-Patak"
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
        String[] ss = stoArray(br.readLine());
        for (int i = 0; i < ss.length; i++) {
            bw.write(ss[i].charAt(0));
        }

        bw.flush();
    }

    private static String[] stoArray(String s) {
        return s.split("-");
    }
}