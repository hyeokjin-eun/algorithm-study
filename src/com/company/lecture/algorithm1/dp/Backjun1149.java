package com.company.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1149
public class Backjun1149 {
    private static final String[] array = {
            "3\n" +
            "26 40 83\n" +
            "49 60 57\n" +
            "13 89 99"
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
        int[][] a = new int[index + 1][4];
        for (int i = 1; i <= index; i++) {
            String[] nums = br.readLine().split(" ");
            a[i][1] = Integer.parseInt(nums[0]);
            a[i][2] = Integer.parseInt(nums[1]);
            a[i][3] = Integer.parseInt(nums[2]);
        }

        for (int i = 2; i <= index; i++) {
            a[i][1] = Math.min(a[i - 1][2], a[i - 1][3]) + a[i][1];
            a[i][2] = Math.min(a[i - 1][1], a[i - 1][3]) + a[i][2];
            a[i][3] = Math.min(a[i - 1][1], a[i - 1][2]) + a[i][3];
        }

        int min = Math.min(a[index][3], Math.min(a[index][1], a[index][2]));
        bw.write(String.valueOf(min));
        bw.flush();
    }
}
