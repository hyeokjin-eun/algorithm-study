package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16917
public class Backjun16917 {
    private static final String[] array = {
            "1500 2000 1600 3 2",
            "1500 2000 1900 3 2",
            "1500 2000 500 90000 100000"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= 100000; i++) {
            int temp = (i * C * 2) + (Math.max(0, X - i) * A) + (Math.max(0, Y - i) * B);
            answer = Math.min(temp, answer);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}