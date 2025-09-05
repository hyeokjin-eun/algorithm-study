package com.company.example;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjun1920 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        for (int i = 0; i < m; i++) {
            if (i != 0) {
                bw.write("\n");
            }

            int is = binarySearch(a, b[i]);
            bw.write(String.valueOf(is));
        }

        bw.flush();
    }

    private static int binarySearch(int[] a, int k) {
        int l = 0;
        int r = a.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (a[m] == k) {
                return 1;
            }

            if (a[m] < k) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return 0;
    }
}
