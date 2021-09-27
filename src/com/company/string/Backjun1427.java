package com.company.string;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;

// link
// https://www.acmicpc.net/problem/1427
public class Backjun1427 {
    private static final String[] array = {
            "2143"
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
        char[] a = br.readLine().toCharArray();
        Arrays.sort(a);
        StringBuilder sb = new StringBuilder();
        for (int i = a.length - 1; 0 <= i; i--) {
            sb.append(a[i]);
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
