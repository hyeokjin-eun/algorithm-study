package com.company.java.lecture.algorithm1.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2745
public class Backjun2745 {
    private static final String[] array = {
            "ZZZZZ 36"
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
        String[] nums = br.readLine().split(" ");
        char[] chars=  nums[0].toCharArray();
        int mod = Integer.parseInt(nums[1]);
        int answer = 0;
        for (char inputChar : chars) {
            answer = answer * mod + convert(inputChar);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int convert (char input) {
        if (input >= '0' && input <= '9') {
            return input - 48;
        } else {
            return input - 55;
        }
    }
}
