package com.company.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14916
public class Backjun14916 {
    private static final String[] array = {
            "13",
            "14",
            "20",
            "100000",
            "1"
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
        int[] cnt = new int[100001];
        cnt[0] = -1;
        cnt[1] = -1;
        cnt[2] = 1;
        cnt[3] = -1;
        cnt[4] = 2;
        cnt[5] = 1;
        cnt[6] = 3;
        cnt[7] = 2;
        for (int i = 8; i <= n; i++) {
            if (cnt[i - 5] != -1 && cnt[i - (i - 5)] != -1) {
                cnt[i] = cnt[i - 5] + cnt[i - (i - 5)];
            } else if (cnt[i - 2] != -1 && cnt[i - (i - 2)] != -1) {
                cnt[i] = cnt[i - 2] + cnt[i - (i - 2)];
            } else {
                cnt[i] = -1;
            }
        }

        int answer = cnt[n];
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
