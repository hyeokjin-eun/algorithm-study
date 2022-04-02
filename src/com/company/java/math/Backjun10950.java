package com.company.java.math;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10950
public class Backjun10950 {
    private static final String[] array = {
            "5\n" +
            "1 1\n" +
            "2 3\n" +
            "3 4\n" +
            "9 8\n" +
            "5 2"
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
        int T = stoi(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int answer = stoi(st.nextToken()) + stoi(st.nextToken());
            bw.write(String.valueOf(answer));
            if (t != T - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}