package com.company.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2231
public class Backjun2231 {
    private static final String[] array = {
            "216"
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
        int N = stoi(br.readLine());
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int sum = i + sum(i);
            if (sum == N) {
                answer = i;
                break;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static int sum(int n) {
        int temp = 0;
        char[] nums = String.valueOf(n).toCharArray();
        for (char num : nums) {
            temp += num - '0';
        }

        return temp;
    }
}