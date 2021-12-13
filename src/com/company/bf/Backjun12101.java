package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/12101
public class Backjun12101 {
    private static int N;
    private static long K;
    private static ArrayList<String> answer = new ArrayList<>();
    private static final String[] array = {
            "4 3",
            "4 5",
            "10 12",
            "4 8"
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
        N = 10;
        K = 12;
        recursion("", 0);
        Collections.sort(answer);
        System.out.println(answer.get((int) K - 1));
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        answer = new ArrayList<>();
        N = stoi(st.nextToken());
        K = stol(st.nextToken());
        recursion("", 0);
        Collections.sort(answer);
        if (answer.size() < K) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(answer.get((int) Math.min(K - 1, 512))));
        }

        bw.flush();
    }

    private static void recursion(String s, int sum) {
        if (N == sum) {
            answer.add(s);
        }

        for (int i = 1; i <= 3; i++) {
            if (N < sum + i) {
                break;
            }

            String str = s;
            if (str.length() == 0) {
                str += String.valueOf(i);
            } else {
                str += "+" + i;
            }
            recursion(str, sum + i);
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static long stol(String s) {
        return Long.parseLong(s);
    }
}