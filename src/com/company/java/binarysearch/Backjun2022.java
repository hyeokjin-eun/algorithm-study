package com.company.java.binarysearch;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2022
public class Backjun2022 {
    private static final String[] array = {
            "30 40 10",
            "12.619429 8.163332 3"
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
        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());
        double c = Double.parseDouble(st.nextToken());
        double left = 0;
        double right = Math.min(x, y);
        while (Math.abs(right - left) > 1e-6) {
            double mid = (left + right) / 2;
            double d = mid;
            double h1 = Math.sqrt(x * x - d * d);
            double h2 = Math.sqrt(y * y - d * d);
            double h = (h1 * h2) / (h1 + h2);
            if (h > c) {
                left = mid;
            } else {
                right = mid;
            }
        }

        bw.write(String.format("%.3f", left));
        bw.flush();
    }
}