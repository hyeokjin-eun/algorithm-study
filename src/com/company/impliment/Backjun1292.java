package com.company.impliment;

import java.io.*;
import java.util.*;


// link
// https://www.acmicpc.net/problem/1292
public class Backjun1292 {
    private static final String[] array = {
            "3 7"
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
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[] a = new int[B];
        int i = 1;
        int index = 0;
        while (index != B) {
            for (int j = 0; j < i; j++) {
                a[index++] = i;
                if (index == B) {
                    break;
                }
            }

            i++;
        }

        int answer = 0;
        for (int j = A - 1; j < B; j++) {
            answer += a[j];
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}