package com.company.java.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1946
public class Backjun1946 {
    private static final String[] array = {
            "2\n" +
            "5\n" +
            "3 2\n" +
            "1 4\n" +
            "4 1\n" +
            "2 3\n" +
            "5 5\n" +
            "7\n" +
            "3 6\n" +
            "7 3\n" +
            "4 2\n" +
            "1 4\n" +
            "5 7\n" +
            "2 5\n" +
            "6 1"
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
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] a = new int[n][2];
            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                a[j][0] = Integer.parseInt(st.nextToken());
                a[j][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(a, (o1, o2) -> {
                if (o1[0] < o2[0]) {
                    return -1;
                } else {
                    return 1;
                }
            });

            int answer = 1;
            int min = a[0][1];
            for (int j = 1; j < n; j++) {
                if (a[j][1] < min) {
                    answer++;
                    min = a[j][1];
                }
            }

            bw.write(String.valueOf(answer));
            if (i != t - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }
}
