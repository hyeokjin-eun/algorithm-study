package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2210
public class Backjun2210 {
    private static final String[] array = {
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 1 1\n" +
            "1 1 1 2 1\n" +
            "1 1 1 1 1"
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
        HashSet<String> set = new HashSet<>();
        String[][] a = new String[5][5];
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                a[i][j] = st.nextToken();
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                recursion(set, "", 0, j, i, a);
            }
        }

        bw.write(String.valueOf(set.size()));
        bw.flush();
    }

    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static void recursion(HashSet<String> set, String s, int index, int x, int y, String[][] a) {
        if (index == 6) {
            set.add(s);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int xn = x + xa[i];
            int yn = y + ya[i];
            if (xn < 0 || yn < 0 || 5 <= xn || 5 <= yn) {
                continue;
            }

            recursion(set, s + a[y][x], index + 1, xn, yn, a);
        }
    }
}