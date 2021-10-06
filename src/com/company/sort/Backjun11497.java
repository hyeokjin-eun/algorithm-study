package com.company.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11497
public class Backjun11497 {
    private static final String[] array = {
            "3\n" +
            "7\n" +
            "13 10 12 11 10 11 12\n" +
            "5\n" +
            "2 4 5 7 9\n" +
            "8\n" +
            "6 6 6 6 6 6 6 6"
    };

    public static void main(String[] args) throws IOException {
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
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] a = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(a);
            int[] b = new int[N];
            int left = 0;
            int right = N - 1;
            boolean check = true;
            for (int i = 0; i < N; i++) {
                if (check) {
                    check = false;
                    b[left] = a[i];
                    left++;
                } else {
                    check = true;
                    b[right] = a[i];
                    right--;
                }
            }

            int max = 0;
            for (int i = 1; i < N; i++) {
                if (max < Math.abs(b[i - 1] - b[i])) {
                    max = Math.abs(b[i - 1] - b[i]);
                }
            }

            bw.write(String.valueOf(max));
            if (t != T - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }
}
