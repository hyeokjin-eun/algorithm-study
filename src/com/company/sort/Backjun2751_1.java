package com.company.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2751
// Arrays.sort 로 구현
public class Backjun2751_1 {
    private static final String[] array = {
            "5\n" +
            "5\n" +
            "4\n" +
            "3\n" +
            "2\n" +
            "1"
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
        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        shuffle(a);
        Arrays.sort(a);
        for (int i = 0; i < N; i++) {
            bw.write(String.valueOf(a[i]));
            if (i != N - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    // Fisher-Yates Shuffle 알고리즘 O(N)
    private static void shuffle(int[] a) {
        Random random = new Random();
        for (int i = a.length - 1; 0 < i; i--) {
            int index = random.nextInt(i);
            int temp = a[index];
            a[index] = a[i];
            a[i] = temp;
        }
    }
}
