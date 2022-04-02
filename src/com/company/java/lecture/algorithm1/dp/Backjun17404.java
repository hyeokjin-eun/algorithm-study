package com.company.java.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/17404
public class Backjun17404 {
    private static final String[] array = {
            "3\n" +
            "26 40 83\n" +
            "49 60 57\n" +
            "13 89 99",
            "6\n" +
            "10 20 30\n" +
            "10 20 30\n" +
            "10 20 30\n" +
            "10 20 30\n" +
            "10 20 30\n" +
            "10 20 30"
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
        int[][] a = new int[index + 1][3];
        int[][] b = new int[index + 1][3];
        for (int i = 1; i <= index; i++) {
            String[] nums = br.readLine().split(" ");
            a[i][0] = Integer.parseInt(nums[0]);
            a[i][1] = Integer.parseInt(nums[1]);
            a[i][2] = Integer.parseInt(nums[2]);
            b[i][0] = a[i][0];
            b[i][1] = a[i][1];
            b[i][2] = a[i][2];
        }

        int answer = 1000 * 1000 + 1;
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                if (k == j) {
                    a[1][j] = b[1][j];
                } else {
                    a[1][j] = 1000 * 1000 + 1;
                }
            }

            for (int i = 2; i <= index; i++) {
                a[i][0] = Math.min(a[i- 1][1], a[i- 1][2]) + b[i][0];
                a[i][1] = Math.min(a[i- 1][0], a[i- 1][2]) + b[i][1];
                a[i][2] = Math.min(a[i- 1][0], a[i- 1][1]) + b[i][2];
            }

            for (int i = 0; i < 3; i++) {
                if (i != k) {
                    answer = Math.min(answer, a[index][i]);
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
