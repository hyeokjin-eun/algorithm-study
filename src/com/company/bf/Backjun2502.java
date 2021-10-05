package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2502
public class Backjun2502 {
    private static final String[] array = {
            "6 41",
            "7 218"
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
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int a = -1;
        int b = -1;
        for (int i = 1; i < k / 2; i++) {
            int[] t = new int[d];
            t[0] = i;
            for (int j = i; j < k; j++) {
                t[1] = j;
                for (int l = 2; l < d; l++) {
                    t[l] = t[l - 1] + t[l - 2];
                }

                if (t[d - 1] == k) {
                    a = t[0];
                    b = t[1];
                    break;
                }
            }

            if (a != -1 && b != -1) {
                break;
            }
        }

        bw.write(String.valueOf(a));
        bw.write("\n");
        bw.write(String.valueOf(b));
        bw.flush();
    }
}
