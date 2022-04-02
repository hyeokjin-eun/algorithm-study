package com.company.java.lecture.algorithm1.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1373
public class Backjun1373 {
    private static final String[] array = {
            "11001100",
            "1"
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
        char[] num = br.readLine().toCharArray();
        int index = num.length;
        if (index % 3 == 1) {
            bw.write(String.valueOf(num[0] - '0'));
        } else if (index % 3 == 2) {
            bw.write(String.valueOf((num[0] - '0') * 2 + (num[1] - '0')));
        }

        for (int i = index % 3; i < index; i += 3) {
            bw.write(String.valueOf((num[i] - '0') * 4 + (num[i + 1] - '0') * 2 + (num[i + 2] - '0')));
        }

        bw.flush();
    }
}
