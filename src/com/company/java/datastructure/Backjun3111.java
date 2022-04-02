package com.company.java.datastructure;

import java.io.*;

// link
// https://www.acmicpc.net/problem/3111
public class Backjun3111 {
    private static char[] l;
    private static char[] r;
    private static int ln;
    private static int rn;
    private static char[] s1;
    private static char[] s2;
    private static char[] s3;

    private static final String[] array = {
            "ne\n" +
            "lukanevolisarmu",
            "aba\n" +
            "ababacccababa",
            "banana\n" +
            "babananananadeda"
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
        l = new char[300001];
        r = new char[300001];
        ln = 0;
        rn = 0;
        s1 = br.readLine().toCharArray();
        s2 = new char[s1.length];
        for (int i = 0; i < s1.length; i++) {
            s2[i] = s1[s1.length - i - 1];
        }

        s3 = br.readLine().toCharArray();

        int left = 0;
        int right = s3.length - 1;
        boolean f = true;
        while (left <= right) {
            if (f) {
                l[ln++] = s3[left++];
            } else {
                r[rn++] = s3[right--];
            }

            if (check(f)) {
                f = !f;
            }
        }

        for (int i = ln - 1; i >= 0; i--) {
            r[rn++] = l[i];
            check(false);
        }

        for (int i = rn - 1; i >= 0; i--) {
            bw.write(String.valueOf(r[i]));
        }

        bw.flush();
    }

    private static boolean check(boolean f) {
        char[] stack = f ? l : r;
        int len = f ? ln : rn;
        char[] str = f ? s2 : s1;
        if (len - s1.length < 0) {
            return false;
        }

        for (int i = 0; i < s1.length; i++) {
            if (stack[len - i - 1] != str[i]) {
                return false;
            }
        }

        if (f) {
            ln -= s1.length;
        } else {
            rn -= s1.length;
        }

        return true;
    }
}