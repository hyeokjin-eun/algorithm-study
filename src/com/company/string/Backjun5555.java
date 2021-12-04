package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/5555
public class Backjun5555 {
    private static final String[] array = {
            "ABCD\n" +
            "3\n" +
            "ABCDXXXXXX\n" +
            "YYYYABCDXX\n" +
            "DCBAZZZZZZ",
            "XYZ\n" +
            "1\n" +
            "ZAAAAAAAXY",
            "PQR\n" +
            "3\n" +
            "PQRAAAAPQR\n" +
            "BBPQRBBBBB\n" +
            "CCCCCCCCCC"
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
        String p = br.readLine();
        int N = stoi(br.readLine());
        int answer = 0;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            s = s + s;
            if (s.contains(p)) {
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}