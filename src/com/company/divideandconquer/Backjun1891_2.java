package com.company.divideandconquer;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1891
public class Backjun1891_2 {
    private static final String[] array = {
            "3 341\n" +
            "2 1",
            "50 11111111111111111111111111111111111111111111111111\n" +
            "2 -1"
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
        String n = st.nextToken();
        st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken()) * -1;
        Pair pair = recursion((1L << d), "", 0, 0 , n, 0);
        pair.x += x;
        pair.y += y;
        if (pair.x < 0 || pair.y < 0 || (1L << d) <= pair.x || (1L << d) <= pair.y) {
            bw.write(String.valueOf(-1));
        } else {
            String temp = solve((1L << d), "", 0, 0, pair.x, pair.y);
            bw.write(temp);
        }

        bw.flush();
    }

    private static Pair recursion(long d, String s, long x, long y, String n, int size) {
        if (d == 1) {
            if (n.equals(s)) return new Pair(x, y);
        }

        long temp = d / 2;
        if (n.charAt(size) == '2') {
            return recursion(temp, s + "2", x, y, n, size + 1);
        } else if (n.charAt(size) == '1') {
            return recursion(temp, s + "1", x + temp, y, n, size + 1);
        } else if (n.charAt(size) == '3') {
            return recursion(temp, s + "3", x, y + temp, n, size + 1);
        } else if (n.charAt(size) == '4') {
            return recursion(temp, s + "4", x + temp, y + temp, n, size + 1);
        }

        return new Pair(0, 0);
    }

    private static String solve(long d, String s, long x, long y, long ex, long ey) {
        if (d == 1) {
            return s;
        }

        long temp = d / 2;
        if (ex < x + temp && ey < y + temp) {
            return solve(temp, s + "2", x, y, ex, ey);
        } else if (ey < y + temp && x + temp <= ex) {
            return solve(temp, s + "1", x + temp, y, ex, ey);
        } else if (y + temp <= ey && ex < x + temp) {
            return solve(temp, s + "3", x, y + temp, ex, ey);
        } else {
            return solve(temp, s + "4", x + temp, y + temp, ex, ey);
        }
    }

    private static class Pair {
        long x;
        long y;
        public Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
