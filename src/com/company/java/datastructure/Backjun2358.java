package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2358
public class Backjun2358 {
    private static final String[] array = {
            "4\n" +
            "0 0\n" +
            "10 10\n" +
            "0 10\n" +
            "10 0"
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
        int[][] a = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i][0] = Integer.parseInt(st.nextToken());
            a[i][1] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        HashMap<Integer, Boolean> x = new HashMap<>();
        HashMap<Integer, Boolean> y = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (x.containsKey(a[i][0])) {
                if (!x.get(a[i][0])) {
                    x.replace(a[i][0], true);
                    answer++;
                }
            } else {
                x.put(a[i][0], false);
            }

            if (y.containsKey(a[i][1])) {
                if (!y.get(a[i][1])) {
                    y.replace(a[i][1], true);
                    answer++;
                }
            } else {
                y.put(a[i][1], false);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
