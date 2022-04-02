package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1181
public class Backjun1181 {
    private static final String[] array = {
            "13\n" +
            "but\n" +
            "i\n" +
            "wont\n" +
            "hesitate\n" +
            "no\n" +
            "more\n" +
            "no\n" +
            "more\n" +
            "it\n" +
            "cannot\n" +
            "wait\n" +
            "im\n" +
            "yours"
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
        int N = stoi(br.readLine());
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        ArrayList<String> array = new ArrayList<>(set);
        array.sort((o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }

            return o1.length() - o2.length();
        });

        for (int i = 0; i < array.size(); i++) {
            bw.write(array.get(i));
            if (i != array.size() - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}