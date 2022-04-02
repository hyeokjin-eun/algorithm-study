package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1806
public class Backjun1806 {
    private static final String[] array = {
            "10 15\n" +
            "5 1 3 5 10 7 4 9 2 8"
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
        int N = stoi(st.nextToken());
        int S = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = stoi(st.nextToken());
        }

        int l = 0;
        int r = 0;
        int answer = 0;
        int sum = 0;
        while (true) {
            if (S <= sum) {
                if (answer == 0 || r - l < answer) {
                    answer = r - l;
                }

                sum -= A[l++];
            } else if (r == N) {
                break;
            } else {
                sum += A[r++];
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}