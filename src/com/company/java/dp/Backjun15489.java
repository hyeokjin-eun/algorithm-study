package com.company.java.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/15489
public class Backjun15489 {
    private static final String[] array = {
            "3 1 4",
            "2 2 3",
            "1 1 1"
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
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[][] a = new int[31][31];
        for (int i = 1; i < 31; i++) {
            for (int j = 1; j < i + 1; j++) {
                if (j == i || j == 1) {
                    a[i][j] = 1;
                } else {
                    a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < i + 1; j++) {
                answer += a[r + i][c + j];
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
