package com.company.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/12970
public class Backjun12970 {
    private static final String[] array = {
            "3 2",
            "5 8"
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
        int K = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int a = 0; a <= N; a++) {
            int b = N - a;
            if (a * b < K) {
                continue;
            }

            int[] cnt = new int[b + 1];
            for (int i = 0; i < a; i++) {
                int x = Math.min(b, K);
                cnt[x] += 1;
                K -= x;
            }

            for (int i = b; 0 <= i; i--) {
                for (int j = 0; j < cnt[i]; j++) {
                    sb.append("A");
                }

                if (0 < i) {
                    sb.append("B");
                }
            }

            break;
        }

        if (sb.length() == 0) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(sb.toString());
        }

        bw.flush();
    }
}