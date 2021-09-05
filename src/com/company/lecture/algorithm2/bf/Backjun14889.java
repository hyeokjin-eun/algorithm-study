package com.company.lecture.algorithm2.bf;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/14889
public class Backjun14889 {
    private static final String[] array = {
            "4\n" +
            "0 1 2 3\n" +
            "4 0 5 6\n" +
            "7 1 0 2\n" +
            "3 4 5 0",
            "6\n" +
            "0 1 2 3 4 5\n" +
            "1 0 2 3 4 5\n" +
            "1 2 0 3 4 5\n" +
            "1 2 3 0 4 5\n" +
            "1 2 3 4 0 5\n" +
            "1 2 3 4 5 0",
            "8\n" +
            "0 5 4 5 4 5 4 5\n" +
            "4 0 5 1 2 3 4 5\n" +
            "9 8 0 1 2 3 1 2\n" +
            "9 9 9 0 9 9 9 9\n" +
            "1 1 1 1 0 1 1 1\n" +
            "8 7 6 5 4 0 3 2\n" +
            "9 1 9 1 9 1 0 9\n" +
            "6 5 4 3 2 1 9 0",
            "5\n" +
            "0 3 1 1 1\n" +
            "3 0 1 1 1\n" +
            "1 1 0 1 1\n" +
            "1 1 1 0 1\n" +
            "1 1 1 1 0"
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
        int n = Integer.parseInt(br.readLine());
        int[][] s = new int[20][20];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<Integer> fa = new ArrayList<>();
        ArrayList<Integer> sa = new ArrayList<>();
        int answer = dp(0, n, fa, sa, s);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int dp (int index, int n, ArrayList<Integer> fa, ArrayList<Integer> sa, int[][] s) {
        if (index == n) {
            if (fa.size() != n / 2 || sa.size() != n / 2) {
                return -1;
            }

            int ft = 0;
            int st = 0;
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2; j++) {
                    if (i == j) {
                        continue;
                    }

                    ft += s[fa.get(i)][fa.get(j)];
                    st += s[sa.get(i)][sa.get(j)];
                }
            }

            return Math.abs(ft - st);
        }

        int answer = -1;
        fa.add(index);
        int ft = dp(index + 1, n, fa, sa, s);
        if (answer == - 1 || (ft != -1 && answer > ft)) {
            answer = ft;
        }

        fa.remove(fa.size() - 1);
        sa.add(index);
        int st = dp(index + 1, n, fa, sa, s);
        if (answer == - 1 || (st != -1 && answer > st)) {
            answer = st;
        }

        sa.remove(sa.size() - 1);
        return answer;
    }
}
