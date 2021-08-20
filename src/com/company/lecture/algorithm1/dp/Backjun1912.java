package com.company.lecture.algorithm1.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Backjun1912 {
    private static final String[] array = {
            "10\n" +
            "10 -4 3 1 5 6 -35 12 21 -1",
            "10\n" +
            "2 1 -4 3 4 -4 6 5 -5 1",
            "5\n" +
            "-1 -2 -3 -4 -5"
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
        int[] a = new int[index];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < index; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int answer = a[0];
        int[] b = new int[index];
        b[0] = a[0];
        for (int i = 1; i < index; i++) {
            b[i] = a[i];
            int t = b[i] + b[i - 1];
            if (b[i] < t) {
                b[i] = t;
            }

            if (answer < b[i]) {
                answer = b[i];
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
