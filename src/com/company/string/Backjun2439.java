package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2439
public class Backjun2439 {
    private static int N;
    private static final String[] array = {
            "5",
            "100"
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
        N = stoi(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(" ");
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.delete(0, 1);
            sb.append("*");
            answer.append(sb);
            if (i != N - 1) {
                answer.append("\n");
            }
        }

        bw.write(answer.toString());
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}