package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/9996
public class Backjun9996 {
    private static final String[] array = {
            "3\n" +
            "a*d\n" +
            "abcd\n" +
            "anestonestod\n" +
            "facebook",
            "6\n" +
            "h*n\n" +
            "huhovdjestvarnomozedocisvastan\n" +
            "honijezakon\n" +
            "atila\n" +
            "je\n" +
            "bio\n" +
            "hun"
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
        int n = Integer.parseInt(br.readLine());
        String[] pattern = br.readLine().split("\\*");
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            int sc = s.length();
            int total = pattern[0].length() + pattern[1].length();
            if (sc < total) {
                bw.write("NE");
            } else {
                for (int j = 0; j < pattern[0].length(); j++) {
                    if (pattern[0].charAt(j) == s.charAt(j)) {
                        total--;
                    }
                }

                for (int j = 0; j < pattern[1].length(); j++) {
                    if (pattern[1].charAt(j) == s.charAt(sc - pattern[1].length() + j)) {
                        total--;
                    }
                }

                if (total == 0) {
                    bw.write("DA");
                } else {
                    bw.write("NE");
                }
            }

            bw.write("\n");
        }

        bw.flush();
    }
}
