package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1541
public class Backjun1541 {
    private static final String[] array = {
            "55-50+40",
            "30-70-20+40-10+100+30-35"
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
        String[] a = br.readLine().split("-");
        int answer = 0;
        for (int i = 0; i < a.length; i++) {
            String[] s = a[i].split("\\+");
            int n = 0;
            for (int j = 0; j < s.length; j++) {
                n += Integer.parseInt(s[j]);
            }

            if (i == 0) {
                answer += n;
            } else {
                answer -= n;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
