package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1182
public class Backjun1182 {
    private static final String[] array = {
            "5 0\n" +
            "-7 -3 -2 5 8"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] a = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int answer = recursion(0, S, a, 0);
        if (S == 0) {
            answer -= 1;
        }
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion(int total, int S, int[] a, int i) {
        if (i == a.length) {
            if (S == total) {
                return 1;
            } else {
                return 0;
            }
        }

        int answer = 0;
        answer += recursion(total + a[i], S, a, i + 1);
        answer += recursion(total, S, a, i + 1);
        return answer;
    }
}