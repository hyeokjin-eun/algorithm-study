package com.company.bronze1;

import java.io.*;

// link
// https://www.acmicpc.net/problem/13301
public class Backjun13301 {
    private static final String[] array = {
            "5",
            "6",
            "80",
            "1",
            "7",
            "2",
            "3"
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
        long[] answer = new long[81];
        answer[0] = 0;
        answer[1] = 1;
        answer[2] = 1;
        for (int i = 3; i <= n; i++) {
            answer[i] = answer[i - 1] + answer[i - 2];
        }

        long round = 4 * (answer[n]) + 2 * (answer[n - 1]);
        bw.write(String.valueOf(round));
        bw.flush();
    }
}
