package com.company.java.math;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/3052
public class Backjun3052 {
    private static final String[] array = {
            "1\n" +
            "2\n" +
            "3\n" +
            "4\n" +
            "5\n" +
            "6\n" +
            "7\n" +
            "8\n" +
            "9\n" +
            "10",
            "42\n" +
            "84\n" +
            "252\n" +
            "420\n" +
            "840\n" +
            "126\n" +
            "42\n" +
            "84\n" +
            "420\n" +
            "126",
            "39\n" +
            "40\n" +
            "41\n" +
            "42\n" +
            "43\n" +
            "44\n" +
            "82\n" +
            "83\n" +
            "84\n" +
            "85"
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
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            set.add(stoi(br.readLine()) % 42);
        }

        bw.write(String.valueOf(set.size()));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}