package com.company.math;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10952
public class Backjun10952 {
    private static final String[] array = {
            "1 1\n" +
            "2 3\n" +
            "3 4\n" +
            "9 8\n" +
            "5 2\n" +
            "0 0"
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
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = stoi(st.nextToken());
            int B = stoi(st.nextToken());
            if (A == 0 && B == 0) {
                break;
            }

            bw.write(String.valueOf(A + B));
            bw.write("\n");
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}