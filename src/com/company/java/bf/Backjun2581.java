package com.company.java.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2581
public class Backjun2581 {
    private static int M;
    private static int N;
    private static boolean[] eratosthenes;
    private static final String[] array = {
            "60\n" +
            "100",
            "64\n" +
            "65"
    };

    public static void main(String[] args) throws IOException {
        // TEST
        test();
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

    private static void test() {
        // 에라스토체네스 체 Check
        N = 100;
        eratosthenes = new boolean[N + 1];
        int[] prime = new int[]{
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                73, 79, 83, 89, 97
        };

        eratosthenes();
        for (int i = 1; i <= N; i++) {
            boolean ok = false;
            for (int j = 0; j < prime.length; j++) {
                if (i == prime[j]) {
                    ok = true;
                    assert (!eratosthenes[i]);
                    break;
                }
            }

            if (!ok) {
                assert (eratosthenes[i]);
            }
        }
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        M = stoi(br.readLine());
        N = stoi(br.readLine());
        eratosthenes = new boolean[N + 1];
        eratosthenes();
        int answer = 0;
        int min = Integer.MAX_VALUE;
        for (int i = M; i <= N; i++) {
            if (!eratosthenes[i]) {
                answer += i;
                min = Math.min(min, i);
            }
        }

        if (answer == 0) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(answer));
            bw.write("\n");
            bw.write(String.valueOf(min));
        }

        bw.flush();
    }

    private static void eratosthenes() {
        eratosthenes[1] = true;
        for (int i = 2; i <= N; i++) {
            if (!eratosthenes[i]) {
                for (int j = i + i; j <= N; j += i) {
                    eratosthenes[j] = true;
                }
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}