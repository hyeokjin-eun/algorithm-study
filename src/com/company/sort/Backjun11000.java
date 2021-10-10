package com.company.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11000
// TODO: 2021-10-10 재 풀이
public class Backjun11000 {
    private static final String[] array = {
            "3\n" +
            "1 3\n" +
            "2 4\n" +
            "3 5",
            "5\n" +
            "1 7\n" +
            "2 3\n" +
            "3 4\n" +
            "4 8\n" +
            "7 10",
            "3\n" +
            "999999999 1000000000\n" +
            "999999998 999999999 \n" +
            "1 999999998"
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
        long[][] a = new long[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i][0] = Integer.parseInt(st.nextToken());
            a[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a, (o1, o2) -> (int) (o1[0] - o2[0]));
        int left = 0;
        int right = N - 1;
        int answer = 1;
        while (left < right) {
            if (a[right][0] < a[left][1] && a[left][1] <= a[right][1]) {
                answer = Math.max(answer, right - left + 1);
            }

            left++;
            if (a[left][1] <= a[right][0]) {
                right--;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}