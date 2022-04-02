package com.company.java.math;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/4153
public class Backjun4153 {
    private static final String[] array = {
            "6 8 10\n" +
            "25 52 60\n" +
            "5 12 13\n" +
            "30000 30000 30000\n" +
            "0 0 0"
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
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            int c = stoi(st.nextToken());
            if (a == 0 && b == 0 && c == 0) {
                break;
            }

            boolean ok = false;
            if (!ok && (a * a) + (b * b) == (c * c)) {
                ok = true;
            }

            if (!ok && (a * a) + (c * c) == (b * b)) {
                ok = true;
            }

            if (!ok && (b * b) + (c * c) == (a * a)) {
                ok = true;
            }

            if (ok) {
                bw.write("right");
            } else {
                bw.write("wrong");
            }

            bw.write("\n");
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}