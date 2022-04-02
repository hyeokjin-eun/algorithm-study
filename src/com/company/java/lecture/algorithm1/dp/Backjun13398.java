package com.company.java.lecture.algorithm1.dp;

import java.io.*;

// link
// https://code.plus/lecture/491
public class Backjun13398 {
    private static final String[] array = {
            "10\n" +
            "10 -4 3 1 5 6 -35 12 21 -1",
            "1\n" +
            "24",
            "3\n" +
            "9 -1 9",
            "3\n" +
            "-1 -1 1",
            "3\n" +
            "-1 -4 -3"
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
        int[] c = new int[index];
        for (int i = 0; i < index; i++) {
            a[i] = Integer.parseInt(nums[i]);
        }

        b[0] = a[0];
        for (int i = 1; i < index; i++) {
            b[i] = a[i];
            int t = b[i] + b[i - 1];
            if (b[i] < t) {
                b[i] = t;
            }
        }

        c[index - 1] = a[index - 1];
        for (int i = index - 2; 0 <= i; i--) {
            c[i] = a[i];
            int t = c[i] + c[i + 1];
            if (c[i] < t) {
                c[i] = t;
            }
        }

        int answer = b[0];
        for (int i = 0; i < index; i++) {
            if (answer < b[i]) {
                answer = b[i];
            }
        }

        for (int i = 1; i < index - 1; i++) {
            int t = b[i - 1] + c[i + 1];
            if (answer < t) {
                answer = t;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
