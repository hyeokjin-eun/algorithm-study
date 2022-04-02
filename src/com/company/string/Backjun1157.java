package com.company.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1157
public class Backjun1157 {
    private static final String[] array = {
            "Mississipi",
            "zZa",
            "baaa"
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
        char[] chars = br.readLine().toCharArray();
        int[] alpha = new int[26];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] - 'A' < 26) {
                // 대문자
                alpha[chars[i] - 'A']++;
            } else {
                // 소문자
                alpha[chars[i] - 'a']++;
            }
        }

        int max = 0;
        int index = 0;
        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i] == max) {
                index = '?' - 'A';
                continue;
            }

            if (max < alpha[i]) {
                max = alpha[i];
                index = i;
            }
        }

        bw.write(String.valueOf(itoc(index + 'A')));
        bw.flush();
    }

    private static char itoc(int num) {
        return (char) num;
    }
}