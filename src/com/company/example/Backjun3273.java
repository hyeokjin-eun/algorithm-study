package com.company.example;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjun3273 {

    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        int n = stio(bufferedReader.readLine());
        int[] temp = new int[n];
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            temp[i] = stio(st.nextToken());
        }

        Arrays.sort(temp);
        int target = stio(bufferedReader.readLine());
        bufferedWriter.write(String.valueOf(solve(n, temp, target)));
        bufferedWriter.flush();
    }

    private static int solve(int n, int[] cur, int target) {
        int count = 0;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int sum = cur[left] + cur[right];
            if (sum == target) {
                count++;
            }

            if (sum <= target) {
                left++;
            } else {
                right--;
            }
        }

        return count;
    }

    private static int stio(String s) {
        return Integer.parseInt(s);
    }
}
