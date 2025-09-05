package com.company.example;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjun2110 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] items = new int[n];
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(items);
        int l = 0;
        int r = items[n - 1] - items[0];
        int answer = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            int count = check(items, mid);
            if (count >= c) {
                answer = Math.max(answer, mid);
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        bw.write(answer + "");
        bw.flush();
    }

    private static int check(int[] items, int mid) {
        int count = 1;
        int last = items[0];
        for (int i = 0; i < items.length; i++) {
            if (items[i] - last >= mid) {
                count++;
                last = items[i];
            }
        }

        return count;
    }
}
