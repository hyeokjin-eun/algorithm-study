package com.company.lecture.algorithm1.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1929
public class Backjun1929 {
    private static BufferedReader br;
    private static BufferedWriter bw;

    private static final String[] array = {
            "3 16"
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
        br = new BufferedReader(isr);
        bw = new BufferedWriter(osw);
        String[] nums = br.readLine().split(" ");
        int start = Integer.parseInt(nums[0]);
        int end = Integer.parseInt(nums[1]);
        primes(start, end);
        bw.flush();
    }

    private static void primes (int start, int end) throws IOException {
        boolean[] check = new boolean[end + 1];
        for (int i = 2; i <= end; i++) {
            if (!check[i]) {
                if (start <= i) {
                    bw.write(String.valueOf(i));
                    bw.write("\n");
                }

                for (int j = i + i; j <= end; j += i) {
                    check[j] = true;
                }
            }
        }
    }
}
