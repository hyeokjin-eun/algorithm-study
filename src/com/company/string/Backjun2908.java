package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2908
public class Backjun2908 {
    private static final String[] array = {
            "734 893",
            "221 231"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = stoi(new StringBuilder(st.nextToken()).reverse().toString());
        int b = stoi(new StringBuilder(st.nextToken()).reverse().toString());
        bw.write(a < b ? String.valueOf(b) : String.valueOf(a));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}