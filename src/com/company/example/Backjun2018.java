package com.company.example;

import java.io.*;

public class Backjun2018 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int left = 0;
        int right = 0;
        int answer = 0;
        while (left <= n && right <= n) {
            int sum = sum(left, right);
            if (sum == n) {
                answer++;
                right++;
            } else if (sum > n) {
                left = left + 1;
            } else {
                right = right + 1;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
    }

    private static int sum(int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += i;
        }

        return sum;
    }
}
