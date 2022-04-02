package com.company.lecture.algorithm1.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/17103
public class Backjun17103 {
    private static final String[] array = {
            "5\n" +
            "6\n" +
            "8\n" +
            "10\n" +
            "12\n" +
            "100"
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
        boolean[] check = eratosthenes();
        for (int i = 0; i < index; i++) {
            int count = goldbach(check, Integer.parseInt(br.readLine()));
            bw.write(String.valueOf(count));
            bw.write("\n");
        }

        bw.flush();
    }

    private static boolean[] eratosthenes () {
        boolean[] check = new boolean[1000001];
        for (int i = 2; i <= 1000000; i++) {
            if (!check[i]) {
                for (int j = i + i; j <= 1000000; j += i) {
                    check[j] = true;
                }
            }
        }

        return check;
    }

    private static int goldbach (boolean[] check, int num) {
        int count = 0;
        for (int i = 2; i <= num / 2; i++) {
            if (!check[i] && !check[num - i]) {
                count++;
            }
        }

        return count;
    }
}
