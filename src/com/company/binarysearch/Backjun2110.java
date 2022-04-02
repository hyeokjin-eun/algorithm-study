package com.company.binarysearch;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2110
public class Backjun2110 {
    private static final String[] array = {
            "5 3\n" +
            "1\n" +
            "2\n" +
            "8\n" +
            "4\n" +
            "9"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(a);
        int left = 1;
        int right = a[N - 1] - a[0];
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(a, C, mid)) {
                answer = Math.max(mid, answer);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static boolean check (int[] a, int c, int x) {
        int cnt = 1;
        int last = a[0];
        for (int house : a) {
            if (house - last >= x) {
                cnt++;
                last = house;
            }
        }

        return cnt >= c;
    }
}
