package com.company.greedy;

import java.io.*;
import java.util.*;

// link
// https://code.plus/lecture/513
public class Backjun513 {
    private static final String[] array = {
            "6\n" +
            "10 20 10 30 20 50"
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
        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        int length = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            int p = go(length, num, a);
            a[p] = num;
            if (p == length) {
                length++;
            }
        }

        bw.write(String.valueOf(length));
        bw.flush();
    }

    private static int go(int length, int k, int[] a) {
        int l = 0;
        int r = length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (k <= a[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}