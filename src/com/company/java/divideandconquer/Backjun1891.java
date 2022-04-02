package com.company.java.divideandconquer;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1891
public class Backjun1891 {
    private static final String[] array = {
            "3 341\n" +
            "2 1"
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
        int d = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken()) * -1;
        int l = (int) Math.pow(2, d);
        String[][] a = new String[l][l];
        recursion(a, d, "", 0, 0);
        boolean check = false;
        int sx = 0;
        int sy = 0;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (Integer.parseInt(a[i][j]) == n) {
                    check = true;
                    sx = j + x;
                    sy = i + y;
                }
            }
        }

        if (check) {
            if (sx < 0 || l <= sx || sy < 0 || l <= sy) {
                bw.write(String.valueOf(-1));
            } else {
                bw.write(a[sy][sx]);
            }
        } else {
            bw.write(String.valueOf(-1));
        }

        bw.flush();
    }

    private static void recursion(String[][] a, int d, String s, int x, int y) {
        if (d == 0) {
            a[y][x] = s;
            return;
        }

        int temp = (int) Math.pow(2, d - 1);
        recursion(a, d - 1, s + "2", x, y);
        recursion(a, d - 1, s + "1", x + temp, y);
        recursion(a, d - 1, s + "3", x, y + temp);
        recursion(a, d - 1, s + "4", x + temp, y + temp);
    }
}
