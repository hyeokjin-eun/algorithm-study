package com.company.java.lecture.algorithm2.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/14501
public class Backjun14501 {
    private static final String[] array = {
            "7\n" +
            "3 10\n" +
            "5 20\n" +
            "1 10\n" +
            "1 20\n" +
            "2 15\n" +
            "4 40\n" +
            "2 200",
            "10\n" +
            "1 1\n" +
            "1 2\n" +
            "1 3\n" +
            "1 4\n" +
            "1 5\n" +
            "1 6\n" +
            "1 7\n" +
            "1 8\n" +
            "1 9\n" +
            "1 10",
            "10\n" +
            "5 10\n" +
            "5 9\n" +
            "5 8\n" +
            "5 7\n" +
            "5 6\n" +
            "5 10\n" +
            "5 9\n" +
            "5 8\n" +
            "5 7\n" +
            "5 6",
            "10\n" +
            "5 50\n" +
            "4 40\n" +
            "3 30\n" +
            "2 20\n" +
            "1 10\n" +
            "1 10\n" +
            "2 20\n" +
            "3 30\n" +
            "4 40\n" +
            "5 50"
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
        int n = Integer.parseInt(br.readLine());
        int[] day = new int[n + 1];
        int[] pay = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            String[] set = br.readLine().split(" ");
            day[i] = Integer.parseInt(set[0]);
            pay[i] = Integer.parseInt(set[1]);
        }

        int result = pay(day, pay, 1, 0, n);
        System.out.println(result);
    }

    private static int pay(int[] days, int[] pays, int day, int pay, int n) {
        if (day == n + 1) {
            return pay;
        }

        if (day > n + 1) {
            return 0;
        }

        int result = pay(days, pays, day + days[day], pay + pays[day], n);
        int a = pay(days, pays, day + 1, pay, n);
        return Math.max(result, a);
    }
}