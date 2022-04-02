package com.company.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1436
public class Backjun1436 {
    private static final String[] array = {
            "187",
            "500",
            "10000"
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
        int N = stoi(br.readLine());
        int count = 0;
        int index = 0;
        while (true) {
            if (check(index)) {
                count++;
            }

            if (count == N) {
                break;
            }

            index++;
        }

        bw.write(String.valueOf(index));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static boolean check(int n) {
        return String.valueOf(n).contains("666");
    }
}