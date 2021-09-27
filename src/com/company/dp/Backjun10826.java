package com.company.dp;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10826
public class Backjun10826 {
    private static final String[] array = {
            "10",
            "0",
            "8000",
            "10000"
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
        int n = Integer.parseInt(br.readLine());
        BigInteger[] a = new BigInteger[10001];
        a[0] = BigInteger.valueOf(0);
        a[1] = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++) {
            a[i] = a[i - 1].add(a[i - 2]);
        }

        BigInteger answer = a[n];
        bw.write(String.valueOf(answer.toString()));
        bw.flush();
    }
}
