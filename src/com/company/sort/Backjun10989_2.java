package com.company.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10989
public class Backjun10989_2 {
    private static final String[] array = {
            "10\n" +
            "5\n" +
            "2\n" +
            "3\n" +
            "1\n" +
            "4\n" +
            "2\n" +
            "3\n" +
            "5\n" +
            "1\n" +
            "7"
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
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> a = new ArrayList<>();
        int[] c = new int[10001];
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            if (c[n] == 0) {
                a.add(n);
                c[n] += 1;
            } else {
                c[n] += 1;
            }
        }

        Collections.sort(a);
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < c[a.get(i)]; j++) {
                bw.write(String.valueOf(a.get(i)));
                bw.write("\n");
            }
        }

        bw.flush();
    }
}
