package com.company.java.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1201
public class Backjun1201 {
    private static final String[] array = {
            "13 5 4",
            "4 2 2",
            "4 4 1",
            "4 3 2",
            "4 4 2"
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
        int K = Integer.parseInt(st.nextToken());
        if (M + K - 1 > N || M * K < N) {
            bw.write(String.valueOf(-1));
        } else {
            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = i + 1;
            }

            ArrayList<Integer> group = new ArrayList<>();
            group.add(0);
            group.add(K);
            N -= K;
            M -= 1;
            int gs = M == 0 ? 1 : N / M;
            int r = M == 0 ? 0 : N % M;
            for (int i = 0 ; i < M; i++) {
                group.add(group.get(group.size() - 1) + gs + (r > 0 ? 1 : 0));
                if (r > 0) {
                    r--;
                }
            }

            for (int i = 0; i < group.size() - 1; i++) {
                int b = group.get(i);
                int e = group.get(i + 1) - 1;
                while (b < e) {
                    int temp = a[b];
                    a[b] = a[e];
                    a[e] = temp;
                    b++;
                    e--;
                }
            }

            for (int i = 0; i < a.length; i++) {
                bw.write(String.valueOf(a[i]));
                if (i != a.length - 1) {
                    bw.write(" ");
                }
            }
        }

        bw.flush();
    }
}