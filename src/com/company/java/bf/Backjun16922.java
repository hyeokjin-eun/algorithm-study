package com.company.java.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/16922
public class Backjun16922 {
    private static final String[] array = {
            "1",
            "2",
            "20"
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
        boolean[] check = new boolean[50 * 20 + 1];
        int answer = 0;
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N - i; j++) {
                for (int k = 0; k <= N - i - j; k++) {
                    int l = N - i - j - k;
                    int sum = i + 5 * j + 10 * k + l * 50;
                    if (!check[sum]) {
                        check[sum] = true;
                        answer++;
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}