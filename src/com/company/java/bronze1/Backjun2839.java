package com.company.java.bronze1;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2839
public class Backjun2839 {
    private static final String[] array = {
            "18",
            "4",
            "6",
            "9"
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
        int n = Integer.parseInt(br.readLine());
        int count = 0;
        while (true) {
            if (n % 5 == 0) {
                count += n / 5;
                break;
            } else if (n < 1) {
                count = -1;
                break;
            } else {
                n -= 3;
                count++;
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
    }
}
