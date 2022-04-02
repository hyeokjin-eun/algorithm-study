package com.company.java.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11399
public class Backjun11399 {
    private static final String[] array = {
            "5\n" +
            "3 1 4 3 2"
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
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> a = new ArrayList<>(n);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(a);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = i; 0 <= j; j--) {
                temp += a.get(j);
            }

            answer += temp;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
