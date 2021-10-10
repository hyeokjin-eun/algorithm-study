package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/6603
public class Backjun6603 {
    private static final String[] array = {
            "7 1 2 3 4 5 6 7\n" +
            "8 1 2 3 5 8 13 21 34\n" +
            "0"
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
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            if (K == 0) {
                break;
            }

            int[] s = new int[K];
            for (int i = 0; i < K; i++) {
                s[i] = Integer.parseInt(st.nextToken());
            }

            ArrayList<String> ans = new ArrayList<>();
            boolean[] check = new boolean[K];
            recursion(ans, 0, 0, K, check, s);
            for (String an : ans) {
                bw.write(an);
                bw.write("\n");
            }

            bw.write("\n");
        }

        bw.flush();
    }

    private static void recursion(ArrayList<String> ans, int start, int select, int K, boolean[] check, int[] num) {
        if (select == 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < check.length; i++) {
                if (check[i]) {
                    sb.append(num[i]);
                    sb.append(" ");
                }
            }

            ans.add(sb.toString());
            return;
        }

        if (start == K) {
            return;
        }

        check[start] = true;
        recursion(ans, start + 1, select  + 1, K, check, num);
        check[start] = false;
        recursion(ans, start + 1, select, K, check, num);
    }
}
