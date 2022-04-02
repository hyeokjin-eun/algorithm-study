package com.company.java.lecture.algorithm2.bf;

import java.io.*;
import java.util.Arrays;

// link
// https://www.acmicpc.net/problem/2309
public class Backjun2309 {
    private static final String[] array = {
            "20\n" +
            "7\n" +
            "23\n" +
            "19\n" +
            "10\n" +
            "15\n" +
            "25\n" +
            "8\n" +
            "13",
            "7\n" +
            "8\n" +
            "10\n" +
            "13\n" +
            "19\n" +
            "20\n" +
            "23\n" +
            "11\n" +
            "12"
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
        int[] a = new int[9];
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            a[i] = Integer.parseInt(br.readLine());
            sum += a[i];
        }

        Arrays.sort(a);
        boolean check = false;
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (sum - a[i] - a[j] == 100) {
                    for (int k = 0; k < 9; k++) {
                        if (k == i || k == j) continue;
                        bw.write(String.valueOf(a[k]));
                        bw.write("\n");
                    }

                    check= true;
                }
            }

            if (check) {
                break;
            }
        }

        bw.flush();
    }
}
