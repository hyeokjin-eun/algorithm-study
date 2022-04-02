package com.company.java.lecture.algorithm2.bf;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/6603
public class Backjun6603 {
    private static final String[] array = {
            "7 1 2 3 4 5 6 7\n" +
            "8 1 2 3 5 8 13 21 34\n" +
            "0"
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
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }

            int[] nums = new int[n];
            int[] selected = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                if (i < 6) {
                    selected[i] = 1;
                } else {
                    selected[i] = 0;
                }
            }

            do {
                for (int i = 0; i < n; i++) {
                    if (selected[i] == 1) {
                        bw.write(String.valueOf(nums[i]));
                        if (i != n - 1) {
                            bw.write(" ");
                        }
                    }
                }

                bw.write("\n");
            } while (prevPermutation(selected));

            bw.write("\n");
        }

        bw.flush();
    }

    private static boolean prevPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i - 1] <= nums[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        int j = nums.length - 1;
        while (nums[j] >= nums[i - 1]) {
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
