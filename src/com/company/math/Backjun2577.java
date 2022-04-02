package com.company.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2577
public class Backjun2577 {
    private static final String[] array = {
            "150\n" +
            "266\n" +
            "427"
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
        int total = 1;
        for (int i = 0; i < 3; i++) {
            total *= stoi(br.readLine());
        }

        int[] count = new int[10];
        String s = itos(total);
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - '0']++;
        }

        for (int i = 0; i < 10; i++) {
            bw.write(String.valueOf(count[i]));
            if (i != 9) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static String itos(int n) {
        return String.valueOf(n);
    }
}