package com.company.java.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1783
public class Backjun1783 {
    private static final int[] xa = new int[]{1, 2, 2, 1};
    private static final int[] ya = new int[]{2, 1, 1, 2};
    private static final String[] array = {
            "100 50"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = 0;
        if (N == 1) {
            answer = 1;
        } else if (N == 2) {
            answer = Math.min(4, (M + 1) / 2);
        } else {
            if (7 <= M) {
                answer = M - 2;
            } else {
                answer = Math.min(4, M);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}