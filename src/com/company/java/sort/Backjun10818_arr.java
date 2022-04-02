package com.company.java.sort;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/10818
public class Backjun10818_arr {
    private static int N;
    private static final String[] array = {
            "5\n" +
            "20 10 35 30 7",
            "TEST MADE",
            "TEST MADE"
    };

    public static void main(String[] args) throws IOException {
        // test
        test();
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
        N = stoi(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[0] = stoi(st.nextToken());
        }

        Arrays.sort(answer);
        bw.write(String.valueOf(answer[0]));
        bw.write(" ");
        bw.write(String.valueOf(answer[answer.length - 1]));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("1000000\n");
        for (int i = 0; i < 1000000; i++) {
            sb.append(i);
            if (i != 999999) {
                sb.append(" ");
            }
        }

        array[1] = sb.toString();

        sb = new StringBuilder();
        sb.append("10000000\n");
        for (int i = 0; i < 10000000; i++) {
            sb.append(i);
            if (i != 9999999) {
                sb.append(" ");
            }
        }

        array[2] = sb.toString();
    }
}
