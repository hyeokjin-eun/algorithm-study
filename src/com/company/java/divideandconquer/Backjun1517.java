package com.company.java.divideandconquer;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1517
// TODO: 2021-10-22 전역 변수를 사용하지 말고 활용 방법 (배열을 새로 생성 X 기존 배열에서 순서만 변경해서 병합)
public class Backjun1517 {
    private static final String[] array = {
            "3\n" +
            "3 2 1",
            "4\n" +
            "3 5 2 9"
    };

    private static long answer = 0;
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
        int[] num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int[] temp = solve(num);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int[] solve(int[] num) {
        if (num.length == 1) {
            return num;
        }

        int m = num.length / 2;
        int[] aa = new int[m];
        int[] ba = new int[num.length - m];
        for (int i = 0; i < m; i++) {
            aa[i] = num[i];
        }

        for (int i = m; i < num.length; i++) {
            ba[i - m] = num[i];
        }

        int[] a = solve(aa);
        int[] b = solve(ba);
        return merge(a, b);
    }

    private static int[] merge(int[] a, int[] b) {
        int[] merge = new int[a.length + b.length];
        int an = 0;
        int bn = 0;
        int i = 0;
        while (an < a.length && bn < b.length) {
            if (a[an] <= b[bn]) {
                merge[i++] = a[an++];
            } else {
                answer += a.length - an;
                merge[i++] = b[bn++];
            }
        }

        while (an < a.length) {
            merge[i++] = a[an++];
        }

        while (bn < b.length) {
            merge[i++] = b[bn++];
        }

        return merge;
    }
}