package com.company.java.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/15353
public class Backjun15353 {
    private static final String[] array = {
            "9223372036854775807 9223372036854775808"
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
        String max = a.length() < b.length() ? b : a;
        String min = a.length() < b.length() ? a : b;
        String answer = sum(max, min);
        bw.write(answer);
        bw.flush();
    }

    private static String sum(String max, String min) {
        StringBuilder sb = new StringBuilder();
        String[] maxs = max.split("");
        String[] mins = min.split("");
        int maxIndex = max.length() - 1;
        int minIndex = min.length() - 1;
        int temp = 0;
        while (temp != 0 || 0 <= maxIndex) {
            int maxNum = maxIndex < 0 ? 0 : Integer.parseInt(maxs[maxIndex]);
            int minNum = minIndex < 0 ? 0 : Integer.parseInt(mins[minIndex]);
            if (9 < maxNum + minNum + temp) {
                sb.insert(0, (maxNum + minNum + temp - 10));
                temp = 1;
            } else {
                sb.insert(0, (maxNum + minNum + temp));
                temp = 0;
            }

            maxIndex--;
            minIndex--;
        }

        return sb.toString();
    }
}
