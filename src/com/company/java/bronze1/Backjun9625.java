package com.company.java.bronze1;

import java.io.*;

// link
// https://www.acmicpc.net/problem/9625
public class Backjun9625 {
    private static final String[] array = {
            "1",
            "4",
            "10"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int n = Integer.parseInt(br.readLine());
        int[][] answer = new int[n + 1][2];
        answer[0][0] = 1;
        answer[0][1] = 0;
        for (int i = 1; i <= n; i++) {
            answer[i][0] = answer[i - 1][1];
            answer[i][1] = answer[i - 1][1] + answer[i - 1][0];
        }

        bw.write(String.valueOf(answer[n][0]));
        bw.write(" ");
        bw.write(String.valueOf(answer[n][1]));
        bw.flush();
    }
}
