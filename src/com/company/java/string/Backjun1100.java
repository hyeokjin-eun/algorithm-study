package com.company.java.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1100
public class Backjun1100 {
    private static final String[] array = {
            ".F.F...F\n" +
            "F...F.F.\n" +
            "...F.F.F\n" +
            "F.F...F.\n" +
            ".F...F..\n" +
            "F...F.F.\n" +
            ".F.F.F.F\n" +
            "..FF..F."
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
        int answer = 0;
        for (int i = 0; i < 8; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0 && c[j] == 'F') {
                    answer++;
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}