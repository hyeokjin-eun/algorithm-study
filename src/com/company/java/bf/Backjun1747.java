package com.company.java.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1747
public class Backjun1747 {
    private static final int max = 1003002;
    private static final String[] array = {
            "31",
            "1000000",
            "1"
    };

    public static void main(String[] args) throws IOException {
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

    private static void test() {
        // eratosthenes Method Test
        boolean[] eratosthenes = eratosthenes();
        int[] answer = new int[]{2, 3, 5, 7, 11, 13, 17};
        for (int i = 0; i < answer.length; i++) {
            assert !eratosthenes[answer[i]];
        }

        // check Method Test
        assert check(232);
        assert check(11);
        assert !check(2232);
        assert check(1);
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        boolean[] eratosthenes = eratosthenes();
        int N = stoi(br.readLine());
        while (N < max) {
            if (!eratosthenes[N] && check(N)) {
                break;
            }

            N++;
        }

        bw.write(String.valueOf(N));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static char[] itochars(int n) {
        return String.valueOf(n).toCharArray();
    }

    private static boolean check(int n) {
        char[] chars = itochars(n);
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }

        return true;
    }

    private static boolean[] eratosthenes() {
        boolean[] eratosthenes = new boolean[max];
        eratosthenes[1] = true;
        for (int i = 2; i < max; i++) {
            if (!eratosthenes[i]) {
                for (int j = i + i; j < max; j += i) {
                    eratosthenes[j] = true;
                }
            }
        }

        return eratosthenes;
    }
}