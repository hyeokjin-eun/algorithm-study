package com.company.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1343
public class Backjun1343 {
    private static final String[] array = {
            "XXXXXX",
            "XX.XX",
            "XXXX....XXX.....XX",
            "X",
            "XX.XXXXXXXXXX..XXXXXXXX...XXXXXX"
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
        s = s.replaceAll("XXXX", "AAAA");
        s = s.replaceAll("XX", "BB");
        if (s.contains("X")) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(s);
        }

        bw.flush();
    }
}