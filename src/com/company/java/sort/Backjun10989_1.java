package com.company.java.sort;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

// link
// https://www.acmicpc.net/problem/10989
public class Backjun10989_1 {
    private static final String[] array = {
            "10\n" +
            "5\n" +
            "2\n" +
            "3\n" +
            "1\n" +
            "4\n" +
            "2\n" +
            "3\n" +
            "5\n" +
            "1\n" +
            "7"
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
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            a.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(a);
        for (int i = 0; i < N; i++) {
            bw.write(String.valueOf(a.get(i)));
            if (i != N - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }
}
