package com.company.java.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/15312
public class Backjun15312 {
    private static final int[] alpha = {
            3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2,
            2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1
    };

    private static final String[] array = {
            "CJM\n" +
            "HER"
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
        String s1 = br.readLine();
        String s2 = br.readLine();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            list.add(convertNum(s1.charAt(i)));
            list.add(convertNum(s2.charAt(i)));
        }

        while (true) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < list.size() - 1; i++) {
                temp.add((list.get(i) + list.get(i + 1)) % 10);
            }

            list = temp;
            if (list.size() == 2) {
                break;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            bw.write(String.valueOf(list.get(i)));
        }

        bw.flush();
    }

    private static int convertNum(char c) {
        return alpha[c - 'A'];
    }
}