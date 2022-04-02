package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16936
public class Backjun16936 {
    private static final String[] array = {
            "6\n" +
            "4 8 6 3 12 9",
            "4\n" +
            "42 28 84 126"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[][] a = new long[N][2];
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(st.nextToken());
            a[i][0] = num;
            while (num % 3 == 0) {
                a[i][1]++;
                num /= 3;
            }
        }

        Arrays.sort(a, (o1, o2) -> {
            if (o2[1] == o1[1]) {
                return Long.compare(o1[0], o2[0]);
            }

            return Long.compare(o1[1], o2[1]) * -1;
        });

        for (int i = 0; i < N; i++) {
            bw.write(String.valueOf(a[i][0]));
            if (i != N - 1) {
                bw.write(" ");
            }
        }

        bw.flush();
    }
}