package com.company.java.greedy;

import java.io.*;
import java.util.*;

public class Backjun1931 {
    private static final String[] array = {
            "11\n" +
            "1 4\n" +
            "3 5\n" +
            "0 6\n" +
            "5 7\n" +
            "3 8\n" +
            "5 9\n" +
            "6 10\n" +
            "8 11\n" +
            "8 12\n" +
            "2 13\n" +
            "12 14"
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
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i][0] = Integer.parseInt(st.nextToken());
            a[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }

            return o1[1] - o2[1];
        });

        int answer = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            if (end <= a[i][0]) {
                end = a[i][1];
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
