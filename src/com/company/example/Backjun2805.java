package com.company.example;

import java.io.*;
import java.util.StringTokenizer;

public class Backjun2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long max = 0;
        long[] trees = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, trees[i]);
        }

        long l = 0;
        long r = max;
        long answer = 0;
        while (l < r) {
            long mid = (l + r) / 2;
            long sum = calculate(trees, n, mid);
            if (m <= sum) {
                answer = Math.max(answer, mid);
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static long calculate(long[] trees, long n, long mid) {
        long count = 0;
        for (int i = 0; i < n; i++) {
            count += Math.max(trees[i] - mid, 0);
        }

        return count;
    }
}
