package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/3019
public class Backjun3019 {
    private static final String[] array = {
            "6 5\n" +
            "2 1 1 1 0 1"
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
        int C = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] a = new int[C];
        for (int i = 0; i < C; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = 0; i < C; i++) {
            if (P == 1) {
                answer += calc(i, "0", C, a) + calc(i, "0000", C, a);
            } else if (P == 2) {
                answer += calc(i, "00", C, a);
            } else if (P == 3) {
                answer += calc(i, "001", C, a) + calc(i, "10", C, a);
            } else if (P == 4) {
                answer += calc(i, "100", C, a) + calc(i, "01", C, a);
            } else if (P == 5) {
                answer += calc(i, "000", C, a) + calc(i, "01", C, a) + calc(i, "101", C, a) + calc(i, "10", C, a);
            } else if (P == 6) {
                answer += calc(i, "000", C, a) + calc(i, "00", C, a) + calc(i, "011", C, a) + calc(i, "20", C, a);
            } else if (P == 7) {
                answer += calc(i, "000", C, a) + calc(i, "00", C, a) + calc(i, "110", C, a) + calc(i, "02", C, a);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int calc(int i, String s, int C, int[] a) {
        if (i + s.length() > C) {
            return 0;
        }

        int base = a[i] - (s.charAt(0) - '0');
        for (int j = 0; j < s.length(); j++) {
            if (base != a[i + j] - (s.charAt(j) - '0')) {
                return 0;
            }
        }

        return 1;
    }
}