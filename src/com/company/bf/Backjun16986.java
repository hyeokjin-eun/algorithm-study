package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16986
public class Backjun16986 {
    private static int N;
    private static int K;
    private static int[][] balance;
    private static int[][] pattern;
    private static final String[] array = {
            "3 2\n" +
            "1 0 2\n" +
            "2 1 0\n" +
            "0 2 1\n" +
            "2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2\n" +
            "3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3",
            "3 1\n" +
            "1 2 2\n" +
            "0 1 2\n" +
            "0 0 1\n" +
            "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" +
            "3 2 1 3 2 1 1 2 3 3 2 2 3 2 1 3 3 2 1 1",
            "4 5\n" +
            "1 0 2 0\n" +
            "2 1 0 2\n" +
            "0 2 1 2\n" +
            "2 0 0 1\n" +
            "1 3 2 1 3 2 2 2 2 1 3 1 3 2 1 3 2 2 2 2\n" +
            "2 3 3 3 1 1 3 1 3 2 1 3 2 2 2 2 1 3 1 2",
            "9 6\n" +
            "1 2 2 0 0 2 2 0 2\n" +
            "0 1 0 2 0 2 0 2 2\n" +
            "0 2 1 0 0 0 0 0 2\n" +
            "2 0 2 1 0 0 2 2 2\n" +
            "2 2 2 2 1 0 2 2 2\n" +
            "0 0 2 2 2 1 2 2 0\n" +
            "0 2 2 0 0 0 1 2 0\n" +
            "2 0 2 0 0 0 0 1 0\n" +
            "0 0 0 0 0 2 2 2 1\n" +
            "4 8 6 1 2 3 3 7 6 4 4 9 9 3 6 7 6 4 1 1\n" +
            "3 8 9 7 9 8 6 3 8 7 1 6 2 3 6 5 8 5 1 8",
            "4 2\n" +
            "1 0 0 0\n" +
            "2 1 2 0\n" +
            "2 0 1 0\n" +
            "2 2 2 1\n" +
            "2 2 3 1 1 3 3 2 2 1 4 1 1 3 3 1 1 1 1 4\n" +
            "1 4 4 2 1 3 1 2 3 4 2 2 3 4 4 2 4 3 1 3",
            "9 6\n" +
            "1 0 2 2 2 2 2 2 0\n" +
            "2 1 0 2 0 2 0 2 2\n" +
            "0 2 1 2 2 0 2 2 0\n" +
            "0 0 0 1 2 2 2 2 0\n" +
            "0 2 0 0 1 2 2 0 0\n" +
            "0 0 2 0 0 1 0 0 2\n" +
            "0 2 0 0 0 2 1 0 0\n" +
            "0 0 0 0 2 2 2 1 2\n" +
            "2 0 2 2 2 0 2 0 1\n" +
            "6 5 8 9 6 1 8 2 1 7 9 5 1 3 4 9 2 3 1 1\n" +
            "2 2 9 9 4 5 9 7 2 7 7 3 1 7 6 6 5 4 2 6",
            "5 2\n" +
            "1 0 0 0 2\n" +
            "2 1 0 0 2\n" +
            "2 2 1 2 0\n" +
            "2 2 0 1 0\n" +
            "0 0 2 2 1\n" +
            "3 5 1 5 2 2 4 5 4 4 1 5 4 3 2 4 3 4 3 4\n" +
            "3 1 3 4 1 1 1 1 3 1 2 1 1 1 3 3 4 1 1 3",
            "9 5\n" +
            "1 2 2 0 0 2 0 2 2\n" +
            "0 1 0 0 2 2 2 0 0\n" +
            "0 2 1 0 0 0 2 0 0\n" +
            "2 2 2 1 2 2 2 2 2\n" +
            "2 0 2 0 1 2 0 0 2\n" +
            "0 0 2 0 0 1 0 0 0\n" +
            "2 0 0 0 2 2 1 0 0\n" +
            "0 2 2 0 2 2 2 1 0\n" +
            "0 2 2 0 0 2 2 2 1\n" +
            "4 7 4 4 1 8 4 3 5 4 4 9 7 1 9 9 6 9 8 8\n" +
            "1 3 5 5 7 6 1 4 8 8 2 9 9 7 9 1 8 3 9 7"
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
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        balance = new int[N][N];
        // 2 : 승리, 1 : 무승부, 0 : 패배
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                balance[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pattern = new int[3][20];
        for (int i = 0; i < 20; i++) {
            if (i < N) {
                pattern[0][i] = i;
            } else {
                pattern[0][i] = -1;
            }
        }

        for (int i = 1; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 20; j++) {
                pattern[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        int answer = 0;
        do {
            if (game()) {
                answer = 1;
                break;
            }
        } while(nextPermutation(pattern[0]));
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static boolean game() {
        boolean ok = false;
        int[][] temp = new int[3][2];
        int f = 0;
        int s = 1;
        while (true) {
            if (temp[1][0] == K || temp[2][0] == K) {
                break;
            }

            if (temp[0][0] == K) {
                ok = true;
                break;
            }

            if (pattern[f][temp[f][1]] == -1 || pattern[s][temp[s][1]] == -1) {
                break;
            }

            int winner = play(f, s, temp);
            temp[winner][0]++;
            temp[f][1]++;
            temp[s][1]++;
            int t = 3 - (f + s);
            f = winner;
            s = t;
        }

        return ok;
    }

    private static boolean nextPermutation(int[] a) {
        int i = N - 1;
        while (i > 0 && a[i - 1] >= a[i]) {
            i--;
        }

        if (i < 1) {
            return false;
        }

        int j = N - 1;
        while (a[i - 1] >= a[j]) {
            j--;
        }

        int temp = a[i - 1];
        a[i - 1] = a[j];
        a[j] = temp;
        j = N -1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }

        return true;
    }

    private static int play(int f, int s, int[][] temp) {
        int b = balance[pattern[f][temp[f][1]]][pattern[s][temp[s][1]]];
        if (b == 1) return Math.max(f, s);
        else return b == 2 ? f : s;
    }
}