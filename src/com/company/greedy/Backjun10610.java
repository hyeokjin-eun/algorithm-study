package com.company.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10610
public class Backjun10610 {
    private static final String[] array = {
            "30",
            "102",
            "2931",
            "80875542"
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
        char[] s = br.readLine().toCharArray();
        int sum = 0;
        for (char c : s) {
            sum += c - '0';
        }

        Arrays.sort(s);
        if (s[0] == '0' && sum % 3 == 0) {
            StringBuilder sb = new StringBuilder();
            for (char c : s) {
                sb.append(c);
            }

            bw.write(sb.reverse().toString());
        } else {
           bw.write(String.valueOf(-1));
        }

        bw.flush();
    }
}
