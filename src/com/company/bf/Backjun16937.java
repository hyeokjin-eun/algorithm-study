package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16937
public class Backjun16937 {
    private static final String[] array = {
            "2 2\n" +
            "2\n" +
            "1 2\n" +
            "2 1",
            "10 9\n" +
            "4\n" +
            "2 3\n" +
            "1 1\n" +
            "5 10\n" +
            "9 11",
            "10 10\n" +
            "3\n" +
            "6 6\n" +
            "7 7\n" +
            "20 5"
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
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(br.readLine());
        int[][] sticker = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sticker[i][0] = Integer.parseInt(st.nextToken());
            sticker[i][1] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int r1 = sticker[i][0];
            int c1 = sticker[i][1];
            for (int j = i + 1; j < N; j++) {
                int r2 = sticker[j][0];
                int c2 = sticker[j][1];
                for (int rt1 = 0; rt1 < 2; rt1++) {
                    for (int rt2 = 0; rt2 < 2; rt2++) {
                        if (r1 + r2 <= H && Math.max(c1, c2) <= W) {
                            answer = Math.max(answer, r1 * c1 + r2 * c2);
                        }

                        if (c1 + c2 <= W && Math.max(r1, r2) <= H) {
                            answer = Math.max(answer, r1 * c1 + r2 * c2);
                        }

                        int t2 = r2;
                        r2 = c2;
                        c2 = t2;
                    }

                    int t1 = r1;
                    r1 = c1;
                    c1 = t1;
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}