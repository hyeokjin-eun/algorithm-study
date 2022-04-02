package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/12931
public class Backjun12931 {
    private static int N;
    private static final String[] array = {
            "2\n" +
            "2 1",
            "3\n" +
            "16 16 16",
            "1\n" +
            "100"
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
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] b = new int[N];
        for (int i = 0; i < N; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int answer = recursion(b, 0);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion(int[] b, int cnt) {
        boolean ok = true;
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (max < b[i]) {
                max = b[i];
            }

            if (b[i] % 2 != 0) {
                b[i]--;
                ok = false;
                break;
            }
        }

        if (max == 0) {
            return cnt;
        }

        if (ok) {
            for (int i = 0; i < N; i++) {
                b[i] /= 2;
            }
        }

        return recursion(b, cnt + 1);
    }
}