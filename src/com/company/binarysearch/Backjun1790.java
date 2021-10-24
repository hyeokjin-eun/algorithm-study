package com.company.binarysearch;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1790
public class Backjun1790 {
    private static final String[] array = {
            "20 23",
            "100000000 78888866"
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
        long k = Long.parseLong(st.nextToken());
        int answer = -1;
        if (k <= length(N)) {
            int left = 1;
            int right = N;
            while (left <= right) {
                int mid = (right + left) / 2;
                long l = length(mid);
                if (l < k) {
                    left = mid + 1;
                } else {
                    answer = mid;
                    right = mid - 1;
                }
            }

            String s = Integer.toString(answer);
            long l = length(answer);
            answer = s.charAt(s.length() - (int) (l - k) - 1) - '0';
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static long length(int n) {
        long answer = 0;
        for (int start = 1, length = 1; start <= n; start *= 10, length++) {
            int end = start * 10 - 1;
            if (end > n) {
                end = n;
            }

            answer += (long) (end - start + 1) * length;
        }

        return answer;
    }
}