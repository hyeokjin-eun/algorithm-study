package com.company.java.implement;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11659
public class Backjun11659 {
    private static int N;
    private static int M;
    private static int[] a;
    private static final String[] array = {
            "5 3\n" +
            "5 4 3 2 1\n" +
            "1 3\n" +
            "2 4\n" +
            "5 5"
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
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        a = new int[N + 1];
        a[0] = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            a[i] = a[i - 1] + stoi(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = stoi(st.nextToken());
            int n2 = stoi(st.nextToken());
            bw.write(String.valueOf(a[n2] - a[n1 - 1]));
            if (i != M - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}