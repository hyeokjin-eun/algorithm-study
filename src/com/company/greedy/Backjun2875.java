package com.company.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2875
public class Backjun2875 {
    private static final String[] array = {
            "6 3 2", //2
            "2 1 1", //0
            "6 10 3", // 3
            "10 6 3" //4
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
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answer = 0;
        while (N >= 2 && M >= 1 && N + M >= K + 3) {
            answer++;
            N -= 2;
            M -= 1;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}