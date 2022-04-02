package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/17089
public class Backjun17089 {
    private static final String[] array = {
            "5 6\n" +
            "1 2\n" +
            "1 3\n" +
            "2 3\n" +
            "2 4\n" +
            "3 4\n" +
            "4 5",
            "4000 0"
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
        int M = Integer.parseInt(st.nextToken());
        boolean[][] a = new boolean[N][N];
        int[] d = new int[N];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            a[x][y] = true;
            a[y][x] = true;
            d[x]++;
            d[y]++;
        }

        int answer = -1;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (a[i][j]) {
                    for (int k = j + 1; k < N; k++) {
                        if (a[i][k] && a[j][k]) {
                            int temp = d[i] + d[j] + d[k] - 6;
                            if (answer == -1 || temp < answer) {
                                answer = temp;
                            }
                        }
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}