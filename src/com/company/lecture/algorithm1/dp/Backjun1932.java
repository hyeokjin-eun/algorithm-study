package com.company.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1932
public class Backjun1932 {
    private static final String[] array = {
            "5\n" +
            "7\n" +
            "3 8\n" +
            "8 1 0\n" +
            "2 7 4 4\n" +
            "4 5 2 6 5"
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
        int[][] t = new int[index][index];
        for (int i = 0; i < index; i++) {
            String[] nums = br.readLine().split(" ");
            for (int j = 0; j <= i; j++) {
                int num = Integer.parseInt(nums[j]);
                if (i == 0) {
                    t[i][j] = num;
                } else if (j == 0) {
                    t[i][j] = t[i - 1][j] + num;
                } else {
                    t[i][j] = Math.max(t[i - 1][j - 1], t[i - 1][j]) + num;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < index; i++) {
            int num = t[index - 1][i];
            if (answer < num) {
                answer = num;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
