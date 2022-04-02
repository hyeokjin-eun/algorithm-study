package com.company.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1965
public class Backjun1965 {
    private static final String[] array = {
            "8\n" +
            "1 6 2 5 7 3 5 6",
            "30\n" +
            "6 16 4 26 27 1 12 25 9 11 18 19 29 23 20 2 22 10 5 28 24 3 21 30 15 13 7 14 17 8"
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
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] temp = new int[n];
        temp[0] = 1;
        for (int i = 0; i < n; i++) {
            temp[i] = 1;
            for (int j = i; 0 <= j; j--) {
                if (a[j] < a[i]) {
                    if (temp[i] < temp[j] + 1) {
                        temp[i] = temp[j] + 1;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (answer < temp[i]) {
                answer = temp[i];
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
