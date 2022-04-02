package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1969
public class Backjun1969 {
    private static final String[] array = {
            "5 8\n" +
            "TATGATAC\n" +
            "TAAGCTAC\n" +
            "AAAGATCC\n" +
            "TGAGATAC\n" +
            "TAAGATGT",
            "4 10\n" +
            "ACGTACGTAC\n" +
            "CCGTACGTAG\n" +
            "GCGTACGTAT\n" +
            "TCGTACGTAA",
            "6 10\n" +
            "ATGTTACCAT\n" +
            "AAGTTACGAT\n" +
            "AACAAAGCAA\n" +
            "AAGTTACCTT\n" +
            "AAGTTACCAA\n" +
            "TACTTACCAA"
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
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] s = new char[N][M];
        for (int i = 0; i < N; i++) {
            s[i] = br.readLine().toCharArray();
        }

        int[][] a = new int[M][26];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                a[j][s[i][j] - 'A']++;
            }
        }

        char[] t = new char[M];
        for (int i = 0; i < M; i++) {
            int index = 0;
            int max = 0;
            for (int j = 0; j < 26; j++) {
                if (max < a[i][j]) {
                    index = j;
                    max = a[i][j];
                }
            }

            t[i] = (char) (index + 'A');
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (t[j] != s[i][j]) {
                    ans++;
                }
            }
        }

        bw.write(String.valueOf(t));
        bw.write("\n");
        bw.write(String.valueOf(ans));
        bw.flush();
    }
}