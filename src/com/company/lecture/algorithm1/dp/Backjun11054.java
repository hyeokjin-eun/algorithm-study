package com.company.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/11054
public class Backjun11054 {
    private static final String[] array = {
            "10\n" +
            "1 5 2 1 4 3 4 5 2 1"
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
            b[i] = 1;
            for (int j = 1; j <= i; j++) {
                if (a[i - j] < a[i] && b[i] < b[i - j] + 1) {
                    b[i] = b[i - j] + 1;
                }
            }
        }

        for (int i = index - 1; 0 <= i; i--) {
            c[i] = 1;
            for (int j = i + 1; j < index; j++) {
                if (a[j] < a[i] && c[i] < c[j] + 1) {
                    c[i] = c[j] + 1;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < index; i++) {
            int count = b[i] + c[i] - 1;
            if (answer < count) answer = count;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
