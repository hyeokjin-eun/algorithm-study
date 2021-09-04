package com.company.lecture.algorithm2.bf;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/10971
public class Backjun10971 {
    private static final String[] array = {
            "4\n" +
            "0 10 15 20\n" +
            "5 0 9 10\n" +
            "6 13 0 12\n" +
            "8 8 9 0",
            "4\n" +
            "0 1 0 0\n" +
            "0 0 1 0\n" +
            "0 0 0 1\n" +
            "1 0 0 0",
            "1\n" +
            "2"
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
        int[][] price = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                price[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] city = new int[n];
        for (int i = 0; i < n; i++) {
            city[i] = i;
        }

        int answer = Integer.MAX_VALUE;
        do {
            if (city[0] != 0) {
                break;
            }

            boolean check = true;
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                if (price[city[i]][city[i + 1]] != 0) {
                    sum += price[city[i]][city[i + 1]];
                } else {
                    check = false;
                }
            }

            if (check && price[city[n - 1]][city[0]] != 0) {
                sum += price[city[n - 1]][city[0]];
                if (answer > sum) {
                    answer = sum;
                }
            }
        } while (nextPermutation(city));

        bw.write(String.valueOf(answer));
        bw.flush();
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
