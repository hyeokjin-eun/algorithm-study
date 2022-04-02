package com.company.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/11722
public class Backjun11722 {
    private static final String[] array = {
            "6\n" +
            "10 30 10 20 20 10"
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
        String[] nums = br.readLine().split(" ");
        int[] a = new int[index];
        int[] b = new int[index];
        for (int i = 0; i < index; i++) {
            a[i] = Integer.parseInt(nums[i]);
            b[i] = 1;
            for (int j = 1; j <= i; j++) {
                if (a[i] < a[i - j] && b[i] < b[i - j] + 1) {
                    b[i] = b[i - j] + 1;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < index; i++) {
            if (answer < b[i]) answer = b[i];
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
