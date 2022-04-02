package com.company.java.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/9095
public class Backjun9095 {
    private static final String[] array = {
            "5\n" +
            "1\n" +
            "4\n" +
            "7\n" +
            "10\n" +
            "11"
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
            int num = dp(Integer.parseInt(br.readLine()));
            bw.write(String.valueOf(num));
            bw.write("\n");
        }

        bw.flush();
    }

    private static int dp (int num) {
        // d[n] = d[n - 1] + d[n - 2] + d[n - 3]
        int[] temp = new int[12];
        temp[0] = 1;
        temp[1] = 1;
        temp[2] = 2;
        if (num < 3) {
            return temp[num];
        }

        for (int i = 3; i <= num; i++) {
            temp[i] = temp[i - 1] + temp[i - 2] + temp[i - 3];
        }

        return temp[num];
    }
}
