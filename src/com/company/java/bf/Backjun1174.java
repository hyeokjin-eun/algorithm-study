package com.company.java.bf;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

// link
// https://www.acmicpc.net/problem/1174
public class Backjun1174 {
    private static int N;
    private static ArrayList<Long> answer;
    private static final String[] array = {
            "1",
            "19",
            "500000"
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

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        N = stoi(br.readLine());
        answer = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            recursion(String.valueOf(i), i);
        }

        Collections.sort(answer);
        if (1023 < N) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(answer.get(N - 1)));
        }

        bw.flush();
    }

    private static void test() {
        N = 19;
        answer = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            recursion(String.valueOf(i), i);
        }
    }

    private static void recursion(String s, int num) {
        for (int i = 0; i < num; i++) {
            recursion(s + i, i);
        }

        answer.add(stol(s));
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static long stol(String s) {
        return Long.parseLong(s);
    }
}