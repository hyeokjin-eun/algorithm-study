package com.company.implement;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10250
public class Backjun10250 {
    private static int H;
    private static int W;
    private static int N;
    private static final String[] array = {
            "2\n" +
            "6 12 10\n" +
            "30 50 72"
    };

    public static void main(String[] args) throws IOException {
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
        StringTokenizer st = new StringTokenizer("6 12 6");
        H = stoi(st.nextToken());
        W = stoi(st.nextToken());
        N = stoi(st.nextToken());
        System.out.println(solve());
    }

    private static String solve() {
        if(N % H == 0) {
            return String.valueOf((H * 100) + (N / H));
        } else {
            return String.valueOf((N % H) * 100 + (N / H + 1));
        }
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int T = stoi(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = stoi(st.nextToken());
            W = stoi(st.nextToken());
            N = stoi(st.nextToken());
            bw.write(solve());
            if (t != T - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}