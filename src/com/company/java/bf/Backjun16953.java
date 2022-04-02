package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16953
public class Backjun16953 {
    private static final String[] array = {
            "2 162",
            "4 42",
            "100 40021"
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
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long answer = recursion(0, A, B);
        bw.write(String.valueOf(answer == -1 ? -1 : answer + 1));
        bw.flush();
    }

    private static long recursion(int index, long A, long B) {
        if (A == B) {
            return index;
        }

        if (B < A) {
            return -1;
        }

        long temp1 = recursion(index + 1, A * 2, B);
        long temp2 = recursion(index + 1, A * 10 + 1, B);
        if (temp1 == -1 && temp2 == -1) {
            return -1;
        } else if (temp1 == -1 || temp2 == -1) {
            return temp1 == -1 ? temp2 : temp1;
        } else {
            return Math.min(temp1, temp2);
        }
    }
}