package com.company.lecture.algorithm1.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/17087
public class Backjun17087 {
    private static final String[] array = {
            "3 3\n" +
            "1 7 11",
            "3 81\n" +
            "33 105 57",
            "1 1\n" +
            "1000000000"
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
        String[] firstNums = br.readLine().split(" ");
        String[] secondNums = br.readLine().split(" ");
        int n = Integer.parseInt(firstNums[0]);
        int s = Integer.parseInt(firstNums[1]);
        int gcd = distance(s, Integer.parseInt(secondNums[0]));
        for (int i = 1; i < n; i++) {
            int distance = distance(s, Integer.parseInt(secondNums[i]));
            gcd = gcd(gcd, distance);
        }

        bw.write(String.valueOf(gcd));
        bw.flush();
    }

    private static int distance (int a, int b) {
        return a - b > 0 ? a - b : (a - b) * -1;
    }

    private static int gcd (int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
