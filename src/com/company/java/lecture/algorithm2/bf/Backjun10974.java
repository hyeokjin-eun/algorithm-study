package com.company.java.lecture.algorithm2.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/10974
public class Backjun10974 {
    private static final String[] array = {
            "3"
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
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
            bw.write(String.valueOf(nums[i - 1]));
            if (i != n) {
                bw.write(" ");
            }
        }

        bw.write("\n");
        while (nextPermutation(nums)) {
            for (int i = 0; i < n; i++) {
                bw.write(String.valueOf(nums[i]));
                if (i != n - 1) {
                    bw.write(" ");
                }
            }

            bw.write("\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i - 1] >= nums[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        int j = nums.length - 1;
        while (nums[j] <= nums[i - 1]) {
            j--;
        }

        int temp = nums[i - 1];
        nums[i - 1] = nums[j];
        nums[j] = temp;

        j = nums.length - 1;
        while (i < j) {
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }

        return true;
    }
}
