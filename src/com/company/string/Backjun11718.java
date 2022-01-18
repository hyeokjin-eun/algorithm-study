package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11718
public class Backjun11718 {
    private static final String[] array = {
            "Hello\n" +
            "Baekjoon\n" +
            "Online Judge"
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
        while(true) {
            String s = br.readLine();
            if (s == null) {
                break;
            }

            bw.write(s);
            bw.write("\n");
        }

        bw.flush();
    }
}