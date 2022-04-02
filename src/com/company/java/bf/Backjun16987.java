package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16987
public class Backjun16987 {
    private static int N;
    private static final String[] array = {
            "3\n" +
            "8 5\n" +
            "1 100\n" +
            "3 5",
            "3\n" +
            "1 100\n" +
            "8 5\n" +
            "3 5",
            "1\n" +
            "123 45",
            "8\n" +
            "222 117\n" +
            "176 92\n" +
            "287 6\n" +
            "284 81\n" +
            "221 96\n" +
            "263 96\n" +
            "188 40\n" +
            "225 1",
            "6\n" +
            "65 281\n" +
            "272 145\n" +
            "233 175\n" +
            "229 12\n" +
            "99 88\n" +
            "142 159",
            "8\n" +
            "161 36\n" +
            "248 93\n" +
            "233 13\n" +
            "241 122\n" +
            "285 91\n" +
            "260 25\n" +
            "221 14\n" +
            "233 42",
            "3\n" +
            "213 295\n" +
            "153 24\n" +
            "15 233",
            "8\n" +
            "44 11\n" +
            "116 73\n" +
            "121 54\n" +
            "49 232\n" +
            "69 136\n" +
            "159 242\n" +
            "109 172\n" +
            "28 31",
            "6\n" +
            "100 1\n" +
            "100 1\n" +
            "100 1\n" +
            "100 1\n" +
            "100 1\n" +
            "100 1"
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
        N = Integer.parseInt(br.readLine());
        int[][] a = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i][0] = Integer.parseInt(st.nextToken());
            a[i][1] = Integer.parseInt(st.nextToken());
        }

        int answer = recursion(0, a);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion(int i, int[][] a) {
        // 마지막에 도착했다면
        if (i == N) {
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                // 계란이 깨져있다면
                if (a[j][0] <= 0) {
                    cnt++;
                }
            }

            return cnt;
        }

        // 현재 계란이 깨져있다면
        if (a[i][0] <= 0) {
            return recursion(i + 1, a);
        }

        int answer = 0;
        boolean ok = false;
        // 다음 계란 선택
        for (int j = 0; j < N; j++) {
            // 현재 계란 건너 뛰기
            if (i == j) {
                continue;
            }

            // 계란이 깨져 있지 않다면
            if (a[j][0] > 0) {
                ok = true;
                a[i][0] -= a[j][1];
                a[j][0] -= a[i][1];
                answer = Math.max(answer, recursion(i + 1, a));
                a[i][0] += a[j][1];
                a[j][0] += a[i][1];
            }
        }

        // 깬 계란이 없으면 다음 계란
        if (!ok) {
            return recursion(i + 1, a);
        }

        return answer;
    }
}
