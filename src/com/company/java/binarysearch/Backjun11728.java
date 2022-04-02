package com.company.java.binarysearch;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11728
public class Backjun11728 {
    private static final String[] array = {
            "2 2\n" +
            "3 5\n" +
            "2 9",
            "2 1\n" +
            "4 7\n" +
            "1",
            "4 3\n" +
            "2 3 5 9\n" +
            "1 4 7"
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
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] a = new int[N];
        int[] b = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st= new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = merge(a, b);
        for (int i = 0; i < answer.length; i++) {
            bw.write(String.valueOf(answer[i]));
            if (i != answer.length - 1) {
                bw.write(" ");
            }
        }

        bw.flush();
    }

    private static int[] merge(int[] a, int [] b) {
        int[] merge = new int[a.length + b.length];
        int index = 0;
        int al = 0;
        int bl = 0;
        while (a.length != al && b.length != bl) {
            if (a[al] < b[bl]) {
                merge[index++] = a[al++];
            } else {
                merge[index++] = b[bl++];
            }
        }

        while (al < a.length) {
            merge[index++] = a[al++];
        }

        while (bl < b.length) {
            merge[index++] = b[bl++];
        }

        return merge;
    }
}