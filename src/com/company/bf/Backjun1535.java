package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1535
public class Backjun1535 {
    private static final String[] array = {
            "3\n" +
            "1 21 79\n" +
            "20 30 25"
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
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int[] h = new int[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(st1.nextToken());
            p[i] = Integer.parseInt(st2.nextToken());
        }

        int[] hp = new int[101];
        for (int i = 0; i < n; i++) {
            for (int j = 99; 0 <= j; j--) {
                if (j + h[i] < 100) {
                    hp[j + h[i]] = Math.max(hp[j + h[i]], hp[j] + p[i]);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 100; i++) {
            if (answer < hp[i]) {
                answer = hp[i];
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static void solution2(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int[] h = new int[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(st1.nextToken());
            p[i] = Integer.parseInt(st2.nextToken());
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            boolean[] check = new boolean[n];
            int temp = bf(i, h[i], p[i], check, h, p, n);
            if (answer < temp) {
                answer = temp;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bf(int c, int ht, int pt, boolean[] check, int[] h, int[] p, int n) {
        check[c] = true;
        int answer = p[c];
        for (int i = 0; i < n; i++) {
            if (!check[i] && ht + h[i] < 100) {
                int temp = p[c] + bf(i, ht + h[i], pt + p[i], check, h, p, n);
                if (answer < temp) {
                    answer = temp;
                }
            }
        }

        check[c] = false;
        return answer;
    }
}
