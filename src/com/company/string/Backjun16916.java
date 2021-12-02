package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16916
public class Backjun16916 {
    private static int mod = 127;
    private static int base = 256;

    private static final String[] array = {
            "baekjoon\n" +
            "aek",
            "baekjoon\n" +
            "bak",
            "baekjoon\n" +
            "joo",
            "baekjoon\n" +
            "oone",
            "baekjoon\n" +
            "online",
            "baekjoon\n" +
            "baekjoon",
            "TEST MADE"
    };

    public static void main (String[] args) throws IOException {
        // TEST
        test();
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
        String s = br.readLine();
        String p = br.readLine();
        bw.write(String.valueOf(match(s, p)));
        bw.flush();
    }

    private static int h(String s) {
        int hash = 0;
        for (char ch : s.toCharArray()) {
            hash = (hash * base + ch) % mod;
        }

        return hash;
    }

    private static int match(String s, String p) {
        if (s.length() < p.length()) return 0;
        int hash_p = h(p);
        int hash_s = h(s.substring(0, p.length()));
        int first = 1;
        for (int i = 0; i < p.length() - 1; i++) {
            first = (first * base) % mod;
        }

        for (int i = 0; i <= s.length() - p.length(); i++) {
            if (hash_p == hash_s) {
                if (s.startsWith(p, i)) {
                    return 1;
                }
            }

            if (i + p.length() < s.length()) {
                hash_s = hash_s - (s.charAt(i) * first) % mod;
                hash_s = (hash_s + mod) % mod;
                hash_s = ((hash_s * base) % mod + s.charAt(i + p.length())) % mod;
            }
        }

        return 0;
    }

    private static void test() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            if (i % 4 == 0) {
                sb.append("a");
            } else if (i % 4 == 1) {
                sb.append("b");
            } else if (i % 4 == 2) {
                sb.append("c");
            } else {
                sb.append("d");
            }
        }

        sb.append("\n");
        sb.append("cd");
        array[6] = sb.toString();
    }
}