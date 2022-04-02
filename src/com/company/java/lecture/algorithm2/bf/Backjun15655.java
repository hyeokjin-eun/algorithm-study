package com.company.java.lecture.algorithm2.bf;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/15655
public class Backjun15655 {
    private static final boolean[] c = new boolean[10];
    private static final int[] a = new int[10];

    private static final String[] array = {
            "3 1\n" +
            "4 5 2",
            "4 2\n" +
            "9 8 7 1",
            "4 4\n" +
            "1231 1232 1233 1234"
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
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums, 0, n);
        recursion(1, n, m + 1, nums, bw);
        bw.flush();
    }

    private static void recursion (int index, int n, int m, int[] nums, BufferedWriter bw) throws IOException {
        if (index == m) {
            for (int i = 1; i < m; i++) {
                bw.write(String.valueOf(a[i]));
                if (i != m - 1) {
                    bw.write(" ");
                }
            }

            bw.write("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (c[i] || nums[i] <= a[index - 1]) {
                continue;
            }

            c[i] = true;
            a[index] = nums[i];
            recursion(index + 1, n, m, nums, bw);
            c[i] = false;
        }
    }
}
