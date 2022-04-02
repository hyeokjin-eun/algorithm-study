package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/7785
public class Backjun7785_2 {
    private static int N;

    private static final String[] array = {
            "4\n" +
            "Baha enter\n" +
            "Askar enter\n" +
            "Baha leave\n" +
            "Artem enter"
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
        N = stoi(br.readLine());
        TreeSet<String> set = new TreeSet<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String command = st.nextToken();
            if (command.equals("enter")) {
                set.add(name);
            } else {
                set.remove(name);
            }
        }

        for (String str : new ArrayList<>(set)) {
            bw.write(str);
            bw.write("\n");
        }

        bw.flush();
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}