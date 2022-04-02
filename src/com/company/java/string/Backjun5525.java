package com.company.java.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/5525
// TODO: 2021/10/02 재 구현 필요
public class Backjun5525 {
    private static final String[] array = {
            "1\n" +
            "13\n" +
            "OOIOIOIOIIOII",
            "2\n" +
            "13\n" +
            "OOIOIOIOIIOII"
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
        int m = Integer.parseInt(br.readLine());
        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        sb.append("I");
        for (int i = 0; i < n; i++) {
            sb.append("OI");
        }

        int[] pi = pi(sb.toString());
        int answer = 0;
        int i = 0;
        int j = 0;
        while (i <= m - sb.toString().length()) {
            if (j < m && s.charAt(i + j) == sb.toString().charAt(j)) {
                j++;

                if (j == m) {

                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int[] pi (String p) {
        int[] pi = new int[p.length()];
        char[] c = p.toCharArray();
        int i = 1;
        int j = 0;
        while (i + j < p.length()) {
            if (c[i + j] == c[j]) {
                j++;
                pi[i + j - 1] = j;
            } else {
                if (j == 0) {
                    i++;
                } else {
                    i += j - pi[j - 1];
                    j = pi[j - 1];
                }
            }
        }

        return pi;
    }
}
