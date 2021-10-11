package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/9663
public class Backjun9663 {
    private static final String[] array = {
            "5",
            "8"
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
        boolean[][] check = new boolean[N][N];
        int answer = recursion(check, N, 0);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion(boolean[][] check, int N, int cnt) {
        if (cnt == N) {
            return 1;
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (check(check, i, cnt, N)) {
                check[cnt][i] = true;
                answer += recursion(check, N,cnt + 1);
                check[cnt][i] = false;
            }
        }

        return answer;
    }

    private static boolean check(boolean[][] check, int x, int y, int N) {
        for (int i = 0; i < N; i++) {
            if (check[y][i]) {
                return false;
            }

            if (check[i][x]) {
                return false;
            }
        }

        int temp1 = y;
        int temp2 = y;
        for (int i = x; i < N; i++) {
            if (temp1 < N) {
                if (check[temp1][i]) {
                    return false;
                }

                temp1++;
            }

            if (0 <= temp2) {
                if (check[temp2][i]) {
                    return false;
                }

                temp2--;
            }
        }

        temp1 = y;
        temp2 = y;
        for (int i = x; 0 <= i; i--) {
            if (temp1 < N) {
                if (check[temp1][i]) {
                    return false;
                }

                temp1++;
            }

            if (0 <= temp2) {
                if (check[temp2][i]) {
                    return false;
                }

                temp2--;
            }
        }

        return true;
    }
}

