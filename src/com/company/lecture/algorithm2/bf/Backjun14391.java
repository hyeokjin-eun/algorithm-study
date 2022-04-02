package com.company.lecture.algorithm2.bf;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/14391
public class Backjun14391 {
    private static final String[] array = {
            "2 3\n" +
            "123\n" +
            "312",
            "2 2\n" +
            "99\n" +
            "11",
            "4 3\n" +
            "001\n" +
            "010\n" +
            "111\n" +
            "100",
            "1 1\n" +
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] nums = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(nums[j]);
            }
        }

        int answer = 0;
        for (int s = 0; s < (1 << n * m); s++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int cur = 0;
                for (int j = 0; j < m; j++) {
                    int k = i * m + j;
                    if ((s & (1 << k)) == 0) {
                        cur = cur * 10 + a[i][j];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }

                sum += cur;
            }

            for (int i = 0; i < m; i++) {
                int cur = 0;
                for (int j = 0; j < n; j++) {
                    int k = i * m + j;
                    if ((s & (1 << k)) != 0) {
                        cur = cur * 10 + a[j][i];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }

                sum += cur;
            }

            answer = Math.max(answer, sum);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
