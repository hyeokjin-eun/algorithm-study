package com.company.string;

import java.io.*;
import java.math.BigInteger;

// link
// https://www.acmicpc.net/problem/2935
public class Backjun2935 {
    private static final String[] array = {
            "1000\n" +
            "*\n" +
            "100",
            "10000\n" +
            "+\n" +
            "10"
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
        BigInteger na = stoi(br.readLine());
        char oper = br.readLine().charAt(0);
        BigInteger nb = stoi(br.readLine());
        BigInteger answer;
        if (oper == '*') {
            answer = na.multiply(nb);
        } else {
            answer = na.add(nb);
        }

        bw.write(answer.toString());
        bw.flush();
    }

    private static BigInteger stoi(String s) {
        return new BigInteger(s);
    }
}