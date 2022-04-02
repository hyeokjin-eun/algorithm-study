package com.company.java.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14425
public class Backjun14425 {
    private static final String[] array = {
            "5 11\n" +
            "baekjoononlinejudge\n" +
            "startlink\n" +
            "codeplus\n" +
            "sundaycoding\n" +
            "codingsh\n" +
            "baekjoon\n" +
            "codeplus\n" +
            "codeminus\n" +
            "startlink\n" +
            "starlink\n" +
            "sundaycoding\n" +
            "codingsh\n" +
            "codinghs\n" +
            "sondaycoding\n" +
            "startrink\n" +
            "icerink"
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
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<String> a = new ArrayList<>();
        int answer = 0;
        for (int i = 0; i < n + m; i++) {
            String s = br.readLine();
            if (i < n) {
                a.add(s);
            } else {
                if (a.contains(s)) {
                    answer++;
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
