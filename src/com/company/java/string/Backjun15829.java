package com.company.java.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/15829
public class Backjun15829 {
    private static final int R = 31;
    private static final int M = 1234567891;
    private static final String[] array = {
            "5\n" +
            "abcde"
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
        int L = stoi(br.readLine());
        String S = br.readLine();
        long sum = 0;
        long temp = 1;
        for (int i = 0; i < L; i++) {
            sum = (sum + (S.charAt(i) - 'a' + 1) * temp) % M;
            temp = (temp * R) % M;
        }

        bw.write(String.valueOf(sum));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}