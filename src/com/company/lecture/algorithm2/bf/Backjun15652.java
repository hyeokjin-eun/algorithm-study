package com.company.lecture.algorithm2.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/15652
public class Backjun15652 {
    private static int[] a = new int[10];

    private static final String[] array = {
            "3 1",
            "4 2",
            "3 3"
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
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        recursion(1, n, m + 1, bw);
        bw.flush();
    }

    private static void recursion (int index, int n, int m, BufferedWriter bw) throws IOException {
        if (index == m) {
            for (int i = 1; i < m; i++) {
                bw.write(String.valueOf(a[i]));
                if (i != m - 1) {
                    bw.write(" ");
                }
            }

            bw.write("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (i < a[index - 1]) {
                continue;
            }

            a[index] = i;
            recursion(index + 1, n, m, bw);
        }
    }
}
