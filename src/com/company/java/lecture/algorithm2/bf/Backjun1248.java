package com.company.java.lecture.algorithm2.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1248
public class Backjun1248 {
    private static final int[] a = new int[10];
    private static final String[] array = {
            "4\n" +
            "-+0++++--+"
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

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int n = Integer.parseInt(br.readLine());
        int[][] s = new int[n][n];
        char[] t = br.readLine().toCharArray();
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = 0;
                if (t[count] == '0') {
                    temp = 0;
                } else if (t[count] == '-') {
                    temp = -1;
                } else {
                    temp = 1;
                }

                s[i][j] = temp;
                count++;
            }
        }

        dp(0, n, s);
        for (int i = 0; i < n; i++) {
            bw.write(String.valueOf(a[i]));
            if (i != n - 1) {
                bw.write(" ");
            }
        }

        bw.flush();
    }

    private static boolean dp (int index, int n, int[][] s) {
        if (index == n) {
            return true;
        }

        if (s[index][index] == 0) {
            a[index] = 0;
            return check(s, index) && dp(index + 1, n, s);
        }

        for (int i = 1; i <= 10; i++) {
            a[index] = s[index][index] * i;
            if (check(s, index) && dp(index + 1, n, s)) {
                return true;
            }
        }

        return false;
    }

    private static boolean check (int[][] s, int index) {
        int temp = 0;
        for (int i = index; i >= 0; i --) {
            temp += a[i];
            if (s[i][index] == 0 && temp != 0) {
                return false;
            }

            if (s[i][index] == -1 && temp >= 0) {
                return false;
            }

            if (s[i][index] == 1 && temp <= 0) {
                return false;
            }
        }

        return true;
    }
}
