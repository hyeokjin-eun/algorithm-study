package com.company.lecture.algorithm1.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/6588
public class Backjun6588 {
    private static final String[] array = {
            "8\n" +
            "20\n" +
            "42\n" +
            "1000000\n" +
            "6\n" +
            "0"
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
        boolean[] eratosthenes = eratosthenes();
        while (true) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                break;
            }

            int result = goldbach(num, eratosthenes);
            if (result > 0) {
                bw.write(num + " = " + result + " + " + (num - result));
            } else {
                bw.write("Goldbach's conjecture is wrong.");
            }

            bw.write("\n");
        }

        bw.flush();
    }

    private static boolean[] eratosthenes () {
        boolean[] check = new boolean[1000001];
        for (int i = 2; i < 1000001; i++) {
            if (!check[i]) {
                for (int j = i + i; j < 1000001; j += i) {
                    check[j] = true;
                }
            }
        }

        return check;
    }

    private static int goldbach (int num, boolean[] eratosthenes) {
        for (int i = 3; i <= num / 2; i++) {
            if (!eratosthenes[i] && !eratosthenes[num - i]) {
                return i;
            }
        }

        return -1;
    }
}
