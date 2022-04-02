package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16938
public class Backjun16938 {
    private static final String[] array = {
            "4 40 50 10\n" +
            "10 20 30 25",
            "3 5 6 1\n" +
            "1 2 3",
            "5 25 35 10\n" +
            "10 10 20 10 20"
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
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer += recursion(i + 1, N, L, R, X, num[i], num[i], num[i], num, 1);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion(int index, int N, int L, int R, int X, int sum, int max, int min, int[] num, int s) {
        if (index == N && 2 <= s) {
            if (L <= sum && sum <= R && X <= max - min) return 1;
            else return 0;
        }

        if (index == N) return 0;
        int answer = 0;
        answer += recursion(index + 1, N, L, R, X, sum + num[index], Math.max(max, num[index]), Math.min(min, num[index]), num, s + 1);
        answer += recursion(index + 1, N, L, R, X, sum, max, min, num, s);
        return answer;
    }
}