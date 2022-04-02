package com.company.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1342
public class Backjun1342 {
    private static final String[] array = {
            "aabbbaa",
            "ab",
            "aaab",
            "abcdefghij"
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
        char[] origin = br.readLine().toCharArray();
        int[] alpha = new int[26];
        for (int i = 0; i < origin.length; i++) {
            alpha[origin[i] - 'a']++;
        }

        int[] seq = new int[origin.length];
        for (int i = 0; i < origin.length; i++) {
            seq[i] = i;
        }

        int answer = 0;
        do {
            char[] temp =  new char[origin.length];
            for (int i = 0; i < origin.length; i++) {
                temp[i] = origin[seq[i]];
            }

            if (check(temp)) {
                answer++;
            }
        } while(nextPermutation(seq));

        bw.write(String.valueOf(factorial(answer, alpha)));
        bw.flush();
    }

    private static boolean check(char[] a) {
        for (int i = 1; i < a.length - 1; i++) {
            if (a[i - 1] == a[i] || a[i + 1] == a[i]) {
                return false;
            }
        }

        return true;
    }

    private static boolean nextPermutation(int[] a) {
        int i = a.length - 1;
        while (i > 0 && a[i - 1] >= a[i]) {
            i--;
        }

        if (i < 1) {
            return false;
        }

        int j = a.length - 1;
        while (a[i - 1] >= a[j]) {
            j--;
        }

        int temp = a[i - 1];
        a[i - 1] = a[j];
        a[j] = temp;
        j = a.length - 1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }

        return true;
    }

    private static int factorial(int answer, int[] alpha) {
        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i] > 0) {
                int factorial = 1;
                for (int j = 1; j <= alpha[i]; j++) {
                    factorial *= j;
                }

                answer /= factorial;
            }
        }

        return answer;
    }
}