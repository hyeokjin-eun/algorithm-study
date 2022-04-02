package com.company.lecture.algorithm2.bf;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/10972
public class Backjun10972 {
    private static final String[] array = {
            "4\n" +
            "1 2 3 4",
            "5\n" +
            "5 4 3 2 1",
            "4\n" +
            "2 1 4 3"
    };

    public static void main(String[] args) throws IOException {
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        if (nextPermutation(nums)) {
            for (int i = 0; i < n; i++) {
                bw.write(String.valueOf(nums[i]));
                if (i != n - 1) {
                    bw.write(" ");
                }
            }
        } else {
            bw.write(String.valueOf(-1));
        }

        bw.flush();
    }

    private static boolean nextPermutation(int[] nums) {
        int a = nums.length - 1;
        int b = a;
        while (a > 0 && nums[a - 1] >= nums[a]) {
            a--;
        }

        if (a <= 0) {
            return false;
        }

        while (nums[b] <= nums[a - 1]) {
            b--;
        }

        int temp = nums[a - 1];
        nums[a - 1] = nums[b];
        nums[b] = temp;

        b = nums.length-1;
        while (a < b) {
            temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
            a++;
            b--;
        }

        return true;
    }
}
