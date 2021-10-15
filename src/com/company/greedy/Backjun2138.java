package com.company.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2138
public class Backjun2138 {
    private static final String[] array = {
            "3\n" +
            "000\n" +
            "010"
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
        int[] be = new int[N];
        int[] af = new int[N];
        String t1 = br.readLine();
        String t2 = br.readLine();
        for (int i = 0; i < N; i++) {
            be[i] = t1.charAt(i) - '0';
            af[i] = t2.charAt(i) - '0';
        }

        int[] t = new int[N];
        System.arraycopy(be, 0, t, 0, N);
        int temp1 = go(t, af, N);
        change(be, 0, N);
        int temp2 = go(be, af, N);
        int answer = -1;
        if (temp1 != -1 || temp2 != -1) {
            answer = temp2 != -1 ? temp2 + 1 : temp1;
        }

        if (temp1 != -1 && temp2 != -1) {
            answer = Math.min(temp1, temp2 + 1);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int go(int[] be, int[] af, int N) {
        int answer = 0;
        for (int i = 1; i < N; i++) {
            if (be[i - 1] != af[i -1]) {
                change(be, i, N);
                answer++;
            }
        }

        for (int i = 0; i < N; i++) {
            if (be[i] != af[i]) {
                answer = -1;
                break;
            }
        }

        return answer;
    }

    private static void change(int[] be, int i, int N) {
        if (0 <= i - 1) {
            be[i - 1] = 1 - be[i - 1];
        }

        be[i] = 1 - be[i];

        if (i + 1 < N) {
            be[i + 1] = 1 - be[i + 1];
        }
    }
}