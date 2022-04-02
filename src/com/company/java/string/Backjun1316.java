package com.company.java.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1316
public class Backjun1316 {
    private static final String[] array = {
            "3\n" +
            "happy\n" +
            "new\n" +
            "year",
            "4\n" +
            "aba\n" +
            "abab\n" +
            "abcabc\n" +
            "a"
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
        int answer = 0;
        for (int i = 0; i < n; i++) {
            boolean[] a = new boolean[26];
            String s = br.readLine();
            boolean check = true;
            char bc = s.charAt(0);
            for (int j = 0; j < s.length(); j++) {
                char c  = s.charAt(j);
                if (a[c - 'a'] && bc != c) {
                    check = false;
                    break;
                } else {
                    a[c - 'a'] = true;
                    bc = c;
                }
            }
            if (check) {
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
