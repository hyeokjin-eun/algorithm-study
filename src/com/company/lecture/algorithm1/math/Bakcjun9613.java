package com.company.lecture.algorithm1.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/9613
public class Bakcjun9613 {
    private static final String[] array = {
            "3\n" +
            "4 10 20 30 40\n" +
            "3 7 5 12\n" +
            "3 125 15 25"
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
            String[] nums = br.readLine().split(" ");
            int numIndex = Integer.parseInt(nums[0]);
            long gcdSum = 0;
            for (int j = 1; j <= numIndex; j++) {
                for (int k = j + 1; k <= numIndex; k++) {
                    gcdSum += gcd(Integer.parseInt(nums[j]), Integer.parseInt(nums[k]));
                }
            }

            bw.write(String.valueOf(gcdSum));
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
