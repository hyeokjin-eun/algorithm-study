package com.company.lecture.algorithm1.dp;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/11053
public class Backjun11053 {
    private static final String[] array = {
            "6\n" +
            "10 20 10 30 20 50"
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
        int index = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] a = new int[index + 1][2];
        int answer = 1;
        a[1][0] = Integer.parseInt(st.nextToken());
        a[1][1] = 1;
        for (int i = 2; i <= index; i++) {
            a[i][0] = Integer.parseInt(st.nextToken());
            a[i][1] = 1;
            for (int j = 1; j < i; j++) {
                if (a[i][0] > a[i - j][0] && a[i][1] < a[i - j][1] + 1 ) {
                    a[i][1] = a[i - j][1] + 1;
                    if (a[i][1] > answer) {
                        answer = a[i][1];
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
