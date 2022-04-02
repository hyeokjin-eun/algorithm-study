package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1920
// TODO: 2022-01-13 Need to implement binary Search
public class Backjun1920 {
    private static final String[] array = {
            "5\n" +
            "4 1 5 2 3\n" +
            "5\n" +
            "1 3 7 9 5"
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
        TreeSet<Integer> treeSet = new TreeSet<>();
        StringTokenizer st;
        int N = stoi(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            treeSet.add(stoi(st.nextToken()));
        }

        int M = stoi(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            if (treeSet.contains(stoi(st.nextToken()))) {
                bw.write(String.valueOf(1));
            } else {
                bw.write(String.valueOf(0));
            }

            if (i != M - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}