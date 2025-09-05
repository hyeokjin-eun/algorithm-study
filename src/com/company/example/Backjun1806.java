package com.company.example;

import java.io.*;
import java.util.*;

public class Backjun1806 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] temp = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            temp[i] = Integer.parseInt(st.nextToken());
        }

        int count = solve(n, s, temp);

        bw.write(count + "\n");
        bw.flush();
    }

    private static int solve(int n, int s, int[] temp) {
        int count = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;
        while (right < n) {
            sum += temp[right];
            right++;

            while (sum >= s) {
                count = Math.min(count, right - left);
                sum -= temp[left++];
            }
        }

        return count == Integer.MAX_VALUE ? 0 : count;
    }
}
