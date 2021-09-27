package com.company.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1003
public class Backjun1003 {
    private static final String[] array = {
            "4\n" +
            "0\n" +
            "1\n" +
            "3\n" +
            "40"
    };

    public static void main(String[] args) throws IOException {
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
        int test = Integer.parseInt(br.readLine());
        int max = 0;
        int[] temp = new int[test];
        for (int i = 0; i < test; i++) {
            int n = Integer.parseInt(br.readLine());
            if (max < n) {
                max = n;
            }

            temp[i] = n;
        }

        int[][] a = new int[max + 1][2];
        a[0][0] = 1;
        a[0][1] = 0;
        a[1][0] = 0;
        a[1][1] = 1;
        for (int i = 2; i <= max; i++) {
            a[i][0] = a[i - 1][0] + a[i - 2][0];
            a[i][1] = a[i - 1][1] + a[i - 2][1];
        }

        for (int i = 0; i < test; i++) {
            bw.write(String.valueOf(a[temp[i]][0]));
            bw.write(" ");
            bw.write(String.valueOf(a[temp[i]][1]));
            if (i != test - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }
}
