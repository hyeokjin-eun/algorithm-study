package com.company.java.lecture.algorithm1.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/11653
public class Backjun11653 {
    private static final String[] array = {
            "72",
            "9991"
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
        int temp = Integer.parseInt(br.readLine());
        for (int i = 2; i * i <= temp; i++) {
            while (temp % i == 0) {
                bw.write(String.valueOf(i));
                bw.write("\n");
                temp /= i;
            }
        }

        if (temp != 1) {
            bw.write(String.valueOf(temp));
            bw.write("\n");
        }

        bw.flush();
    }
}
