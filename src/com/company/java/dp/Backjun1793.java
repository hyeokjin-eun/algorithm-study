package com.company.java.dp;

import java.io.*;
import java.math.BigInteger;

// link
// https://www.acmicpc.net/problem/1793
public class Backjun1793 {
    private static final String[] array = {
            "2\n" +
            "8\n" +
            "12\n" +
            "100\n" +
            "200"
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
        while (true) {
            String s = br.readLine();
            if (s == null) {
                break;
            }

            int n = stoi(s);
            bw.write(dp(n).toString());
            bw.write("\n");
        }

        bw.flush();
    }

    private static BigInteger dp(int n) {
        BigInteger[] bigIntegers = new BigInteger[251];
        bigIntegers[0] = BigInteger.ONE;
        bigIntegers[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            bigIntegers[i] = bigIntegers[i - 1].add(bigIntegers[i - 2].multiply(new BigInteger("2")));
        }

        return bigIntegers[n];
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}