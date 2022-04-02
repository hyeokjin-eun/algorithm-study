package com.company.java.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/11057
public class Backjun11057 {
    private static final String[] array = {
            "1",
            "2",
            "3",
            "1000"
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
        int n = Integer.parseInt(br.readLine());
        long[][] a = new long[n][10];
        for (int i = 0; i < 10; i++) {
            a[0][i] = i + 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    a[i][j] = 1;
                } else {
                    a[i][j] = (a[i][j - 1] + a[i - 1][j]) % 10007;
                }
            }
        }

        bw.write(String.valueOf(a[n - 1][9] % 10007));
        bw.flush();
    }
}
