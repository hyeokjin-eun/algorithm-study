package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2003
public class Backjun2003 {
    private static int N;
    private static long M;
    private static final String[] array = {
            "4 2\n" +
            "1 1 1 1",
            "10 5\n" +
            "1 2 3 4 2 5 3 1 1 2"
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
        N = stoi(st.nextToken());
        M = stol(st.nextToken());
        long[] A = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = stol(st.nextToken());
        }

        int answer = 0;
        long sum = 0;
        int l = 0;
        int r = 0;
        while (true) {
            if (M <= sum) {
                sum -= A[l++];
            } else if (r == N) {
                break;
            } else {
                sum += A[r++];
            }

            if (sum == M) {
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static long stol(String s) {
        return Long.parseLong(s);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
