package com.company.math;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2869
public class Backjun2869 {
    private static int A;
    private static int B;
    private static int V;
    private static final String[] array = {
            "2 1 5",
            "5 1 6",
            "100 99 1000000000"
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
        A = stoi(st.nextToken());
        B = stoi(st.nextToken());
        V = stoi(st.nextToken());
        int d = (V - B) / (A - B);
        if ((V - B) % (A - B) != 0) {
            d++;
        }

        bw.write(String.valueOf(d));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}