package com.company.java.lecture.algorithm1.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1676
public class Backjun1676 {
    private static final String[] array = {
            "10",
            "100",
            "500"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int num = Integer.parseInt(br.readLine());
        int count = 0;
        while (num >= 5) {
            count += num / 5;
            num /= 5;
        }

        bw.write(String.valueOf(count));
        bw.flush();
    }
}
