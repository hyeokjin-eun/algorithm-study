package com.company.example;

import java.io.*;
import java.util.*;

public class Backjun2003 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st =new StringTokenizer(br.readLine());
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(twoPointers(temp, m)));
        bw.flush();
    }

    private static int twoPointers(int[] nums, int k) {
        int count = 0;
        int l = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= k) {
                if (sum == k) {
                    count++;
                }

                sum -= nums[l];
                l++;
            }
        }

        return count;
    }
}
