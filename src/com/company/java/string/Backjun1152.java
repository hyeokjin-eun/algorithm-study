package com.company.java.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1152
public class Backjun1152 {
    private static final String[] array = {
            " The first character is a blank"
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
        int answer = 0;
        while (st.hasMoreTokens()) {
            st.nextToken();
            answer++;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}