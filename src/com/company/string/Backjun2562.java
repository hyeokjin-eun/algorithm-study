package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2562
public class Backjun2562 {
    private static final String[] array = {
            "3\n" +
            "29\n" +
            "38\n" +
            "12\n" +
            "57\n" +
            "74\n" +
            "40\n" +
            "85\n" +
            "61",
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

    private static void test() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000000; i++) {
            sb.append(i + 1);
            if (i != 10000000 - 1) {
                sb.append("\n");
            }
        }

        array[1] = sb.toString();
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int max = 0;
        int i = 1;
        int index = 0;
        while (true) {
            String str = br.readLine();
            if (str == null) {
                break;
            }

            int num = stoi(str);
            if (num > max) {
                index = i;
                max = num;
            }

            i++;
        }

        bw.write(String.valueOf(max));
        bw.write("\n");
        bw.write(String.valueOf(index));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
