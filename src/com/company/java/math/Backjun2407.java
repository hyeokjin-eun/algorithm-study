package com.company.java.math;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2407
public class Backjun2407 {
    private static final String[] array = {
            "100 6"
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
        BigInteger temp = factorial(100);
        System.out.println(temp.toString());
        BigInteger temp2 = permutation(100, 100);
        System.out.println(temp2.toString());
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        BigInteger answer = permutation(N, M).divide(factorial(M));
        bw.write(answer.toString());
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static BigInteger permutation(int n, int m) {
        BigInteger temp = BigInteger.ONE;
        for (int i = 0; i < m; i++) {
            temp = temp.multiply(BigInteger.valueOf(n - i));
        }

        return temp;
    }

    private static BigInteger factorial(int m) {
        BigInteger temp = BigInteger.ONE;
        for (long i = 2; i <= m; i++) {
            temp = temp.multiply(BigInteger.valueOf(i));
        }

        return temp;
    }
}