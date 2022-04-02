package com.company.java.math;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1085
public class Backjun1085 {
    private static int[] a;
    private static int[] b;
    private static final String[] array = {
            "6 2 10 3",
            "1 1 5 5",
            "653 375 1000 1000",
            "161 181 762 375"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = stoi(st.nextToken());
        int y = stoi(st.nextToken());
        int w = stoi(st.nextToken());
        int h = stoi(st.nextToken());
        a = new int[]{0, 0, w, h};
        b = new int[]{x, y, x, y};
        bw.write(String.valueOf(getMin()));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static int getMin() {
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, Math.abs(a[i] - b[i]));
        }

        return answer;
    }
}