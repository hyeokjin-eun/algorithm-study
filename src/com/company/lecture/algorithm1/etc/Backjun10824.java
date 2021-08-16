package com.company.lecture.algorithm1.etc;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/10824
public class Backjun10824 {
    private static final String[] array = {
            "10 20 30 40"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        long numAB = Long.parseLong(st.nextToken() + st.nextToken());
        long numCD = Long.parseLong(st.nextToken() +  st.nextToken());
        bw.write(String.valueOf(numAB + numCD));
        bw.flush();
    }
}
