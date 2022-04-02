package com.company.java.implement;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2920
public class Backjun2920 {
    private static final String[] array = {
            "1 2 3 4 5 6 7 8",
            "8 7 6 5 4 3 2 1",
            "8 1 7 2 6 3 5 4"
    };

    public static void main (String[] args) throws IOException {
        // TEST
        test();
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

    private static void test() {

    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] temp = new int[8];
        boolean a = true;
        boolean b = true;
        for (int i = 0; i < 8; i++) {
            temp[i] = stoi(st.nextToken());
        }

        for (int i = 1; i < 8; i++) {
            if (a && temp[i] < temp[i - 1]) {
                a = false;
            }

            if (b && temp[i - 1] < temp[i]) {
                b = false;
            }
        }

        if (!a && !b) {
            bw.write("mixed");
        } else if (a) {
            bw.write("ascending");
        } else {
            bw.write("descending");
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}