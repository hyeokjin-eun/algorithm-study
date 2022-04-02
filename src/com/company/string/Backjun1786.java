package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1786
public class Backjun1786 {
    private static final String[] array = {
            "ABC ABCDAB ABCDABCDABDE\n" +
            "ABCDABD"
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
        ArrayList<Integer> answer = kmp(br.readLine(), br.readLine());
        bw.write(String.valueOf(answer.size()));
        bw.write("\n");
        for (int index : answer) {
            bw.write((index + 1) + " ");
        }

        bw.flush();
    }

    private static ArrayList<Integer> kmp(String s, String p) {
        int[] pi = preprocessing(p);
        ArrayList<Integer> ans = new ArrayList<>();
        int n = s.length();
        int m = p.length();
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) {
                j = pi[j - 1];
            }

            if (s.charAt(i) == p.charAt(j)) {
                if (j == m - 1) {
                    ans.add(i - m + 1);
                    j = pi[j];
                } else {
                    j += 1;
                }
            }
        }

        return ans;
    }

    private static int[] preprocessing(String p) {
        int m = p.length();
        int[] pi = new int[m];
        pi[0] = 0;
        int j = 0;
        for (int i = 1; i < m; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = pi[j - 1];
            }

            if (p.charAt(i) == p.charAt(j)) {
                pi[i] = j + 1;
                j += 1;
            } else {
                pi[i] = 0;
            }
        }

        return pi;
    }
}