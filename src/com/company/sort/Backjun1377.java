package com.company.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1377
// * 시간 초과 로직 이지만 Bubble Sort 참고용
public class Backjun1377 {
    private static final String[] array = {
            "5\n" +
            "10\n" +
            "1\n" +
            "5\n" +
            "2\n" +
            "3",
            "5\n" +
            "1\n" +
            "3\n" +
            "5\n" +
            "7\n" +
            "9"
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
        Pair[] A = new Pair[N];
        for (int i = 0; i < N; i++) {
            A[i] = new Pair(Integer.parseInt(br.readLine()), i);
        }

        Arrays.sort(A, (o1, o2) -> {
            if (o1.n == o2.n) {
                return o1.i - o2.i;
            }

            return o1.n - o2.n;
        });

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (A[i].i - i > ans) {
                ans = A[i].i - i;
            }
        }

        bw.write(String.valueOf(ans + 1));
        bw.flush();
    }

    private static class Pair {
        int n;
        int i;
        public Pair(int n, int i) {
            this.n = n;
            this.i = i;
        }
    }
}