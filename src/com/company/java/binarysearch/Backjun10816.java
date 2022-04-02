package com.company.java.binarysearch;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10816
public class Backjun10816 {
    private static final String[] array = {
            "10\n" +
            "6 3 2 10 10 10 -10 -10 7 3\n" +
            "8\n" +
            "10 9 -5 2 3 4 5 -10"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(st.nextToken());
            int upper = upperBound(a, n);
            int lower = lowerBound(a, n);
            if (upper == -1) {
                bw.write(String.valueOf(0));
            } else {
                bw.write(String.valueOf(upper - lower + 1));
            }

            bw.write(" ");
        }

        bw.flush();
    }

    private static int upperBound(int[] a, int n) {
        int left = 0;
        int right = a.length - 1;
        int answer = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == n) {
                answer = mid;
                left = mid + 1;
            } else if (a[mid] > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private static int lowerBound(int[] a, int n) {
        int left = 0;
        int right = a.length - 1;
        int answer = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == n) {
                answer = mid;
                right = mid - 1;
            } else if (a[mid] > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}
