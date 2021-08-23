package com.company.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/11055
public class Backjun11055 {
    private static final String[] array = {
            "10\n" +
            "1 100 2 50 60 3 5 6 7 8",
            "1\n" +
            "10",
            "10\n" +
            "102 100 2 3 4 3 5 6 7 8",
            "5\n" +
            "5 1 2 3 10",
            "10\n" +
            "1 100 2 50 60 3 5 6 7 200"
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
        int[] a = new int[index];
        int[] t = new int[index];
        String[] nums = br.readLine().split(" ");
        for (int i = 0; i < index; i++) {
            a[i] = Integer.parseInt(nums[i]);
            t[i] = a[i];
            for (int j = 1; j <= i; j++) {
                int temp = a[i - j] + t[i];
                if (t[i - j] < t[i] && a[i] < temp) {
                    a[i] = temp;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < index; i++) {
            if (answer < a[i]) answer = a[i];
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
