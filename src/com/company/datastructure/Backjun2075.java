package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2075
// TODO: 2021-10-06 다른 방법으로 풀순 없는지 고민 필요
public class Backjun2075 {
    private static final String[] array = {
        "5\n" +
        "12 7 9 15 5\n" +
        "13 8 11 19 6\n" +
        "21 10 26 31 16\n" +
        "48 14 28 35 25\n" +
        "52 20 32 41 49"
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
        int N = Integer.parseInt(br.readLine());
        long[][] a = new long[N][N];
        PriorityQueue<Long> q = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                q.add(a[i][j]);
            }
        }

        long answer = 0;
        for (int i = 0; i < N; i++) {
            long temp = q.poll();
            if (i == N - 1) {
                answer = temp;
            }
        }

        bw.write(String.valueOf(answer));;
        bw.flush();
    }
}