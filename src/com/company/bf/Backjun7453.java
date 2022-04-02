package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/7453
public class Backjun7453 {
    private static final String[] array = {
            "6\n" +
            "-45 22 42 -16\n" +
            "-41 -27 56 30\n" +
            "-36 53 -37 77\n" +
            "-36 30 -75 -46\n" +
            "26 -38 -10 62\n" +
            "-32 -54 -6 45",
            "TEST MADE"
    };

    public static void main (String[] args) throws IOException {
        // TEST
        test();
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
        int N = stoi(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = stoi(st.nextToken());
            B[i] = stoi(st.nextToken());
            C[i] = stoi(st.nextToken());
            D[i] = stoi(st.nextToken());
        }
        int[] first = new int[N * N];
        int[] second = new int[N * N];
        int p = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                first[p] = A[i] + B[j];
                second[p] = C[i] + D[j];
                p += 1;
            }
        }
        Arrays.sort(second);
        long answer = 0;
        for (int num : first) {
            int lower = lowerBinarySearch(second, -num);
            int upper = upperBinarySearch(second, -num);
            answer += upper - lower;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static int upperBinarySearch(int[] a, int val) {
        int left = 0;
        int right = a.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] <= val) left = mid + 1;
            else right = mid;
        }

        return left;
    }

    private static int lowerBinarySearch(int[] a, int val) {
        int left = 0;
        int right = a.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (a[mid] >= val) right = mid;
            else left = mid + 1;
        }

        return left;
    }

    private static void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("4000\n");
        for (int i = 0; i < 4000; i++) {
            sb.append("0");
            sb.append(" ");
            sb.append("0");
            sb.append(" ");
            sb.append("0");
            sb.append(" ");
            sb.append("0");
            if (i != 3999) {
                sb.append("\n");
            }
        }

        array[1] = sb.toString();
    }
}
