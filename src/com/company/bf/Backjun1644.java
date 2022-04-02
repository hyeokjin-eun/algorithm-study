package com.company.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1644
public class Backjun1644 {
    private static int N;
    private static boolean[] eratosthenes;
    private static final String[] array = {
            "20",
            "3",
            "41",
            "53",
            "1",
            "2",
            "4000000"
    };

    public static void main (String[] args) throws IOException {
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

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        N = stoi(br.readLine());
        int answer = 0;
        if (N != 1) {
            eratosthenes = eratosthenes(N);
            int max = N;
            for (int i = N; 0 <= i; i--) {
                if (!eratosthenes[i]) {
                    max = i;
                    break;
                }
            }

            int l = 2;
            int r = 2;
            long sum = 2;
            while (true) {
                if (N == sum) {
                    answer++;
                }

                if (N < sum) {
                    for (int i = l + 1; i <= N; i++) {
                        if (!eratosthenes[i]) {
                            sum -= l;
                            l = i;
                            break;
                        }
                    }
                } else if (r == max) {
                    break;
                } else {
                    for (int i = r + 1; i <= N; i++) {
                        if (!eratosthenes[i]) {
                            sum += i;
                            r = i;
                            break;
                        }
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static boolean[] eratosthenes(int num) {
        boolean[] check = new boolean[num + 1];
        check[1] = true;
        for (int i = 2; i <= num; i++) {
            for (int j = i + i; j <= num; j += i) {
                check[j] = true;
            }
        }

        return check;
    }

    private static void test() {
        // 에라스토체네스 체 Check
        int primeMax = 100;
        int[] prime = new int[]{
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                73, 79, 83, 89, 97
        };

        boolean[] eratosthenes = eratosthenes(primeMax);
        for (int i = 1; i < primeMax; i++) {
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
}