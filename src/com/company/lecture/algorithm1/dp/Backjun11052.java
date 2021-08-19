package com.company.lecture.algorithm1.dp;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/11052
public class Backjun11052 {
    private static final String[] array = {
            "4\n" +
            "1 5 6 7",
            "5\n" +
            "10 9 8 7 6",
            "10\n" +
            "1 1 2 3 5 8 13 21 34 55",
            "10\n" +
            "5 10 11 12 13 30 35 40 45 47"
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
        int count = Integer.parseInt(br.readLine());
        int[] prices = new int[count + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= count; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[count + 1];
        for (int i = 1; i <= count; i++) {
            for (int j = 1; j <= i; j++) {
                int temp = answer[i - j] + prices[j];
                if (answer[i] < temp) answer[i] = temp;
            }
        }

        bw.write(String.valueOf(answer[count]));
        bw.flush();
    }
}
