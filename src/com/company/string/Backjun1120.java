package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1120
public class Backjun1120 {
    private static final String[] array = {
            "adaabc aababbc"
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
        String a = st.nextToken();
        String b = st.nextToken();
        int answer = a.length();
        for (int i = 0; i < b.length() - a.length() + 1; i++) {
            int temp = 0;
            for (int j = 0; j < a.length(); j++) {
                if (a.charAt(j) != b.charAt(j + i)) {
                    temp++;
                }
            }

            if (temp < answer) {
                answer = temp;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
