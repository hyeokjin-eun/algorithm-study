package com.company.java.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2751
public class Backjun2751 {
    private static final String[] array = {
            "5\n" +
            "5\n" +
            "4\n" +
            "3\n" +
            "2\n" +
            "1"
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
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            a.add(num);
        }

        Collections.sort(a);
        for (int i = 0; i < n; i++) {
            bw.write(String.valueOf(a.get(i)));
            if (i != n - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }
}
