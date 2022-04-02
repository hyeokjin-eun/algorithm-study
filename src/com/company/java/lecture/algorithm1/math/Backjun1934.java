package com.company.java.lecture.algorithm1.math;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1934
public class Backjun1934 {
    private static final String[] array = {
           "3\n" +
           "1 45000\n" +
           "6 10\n" +
           "13 17"
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
        int index = Integer.parseInt(br.readLine());
        for (int i = 0; i < index; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int gcd = gcd(a, b);
            int lcm = a * b / gcd(a, b);
            bw.write(String.valueOf(lcm));
            bw.write("\n");
        }

        bw.flush();
    }

    private static int gcd (int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}
