package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16638
public class Backjun16638 {
    private static int N;
    private static final String[] array = {
            "9\n" +
            "3+8*7-9*2",
            "5\n" +
            "8*3+5",
            "7\n" +
            "8*3+5+2",
            "19\n" +
            "1*2+3*4*5-6*7*8*9*0",
            "19\n" +
            "1*2+3*4*5-6*7*8*9*9",
            "19\n" +
            "1-9-1-9-1-9-1-9-1-9"
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
        N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        char[] t = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                a[i] = t[i] - '0';
            } else {
                int temp = 0;
                if (t[i] == '-') {
                    temp = 1;
                } else if (t[i] == '*') {
                    temp = 2;
                }

                a[i] = temp;
            }
        }

        long answer = recursion(1, 0, a);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static long recursion(int c, int be, int[] a) {
        if (c == N) {
            ArrayList<Integer> b = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (i % 2 == 0) {
                    b.add(a[i]);
                } else {
                    if (a[i] == -1) {
                        i++;
                    } else {
                        if (a[i] == 2) {
                            int n = b.get(b.size() - 1) * a[i + 1];
                            b.remove(b.size() - 1);
                            b.add(n);
                            i++;
                        } else {
                            b.add(a[i]);
                        }
                    }
                }
            }

            long answer = b.get(0);
            for (int i = 1; i < b.size(); i += 2) {
                if (b.get(i) == 0) {
                    answer += b.get(i + 1);
                } else if (b.get(i) == 1) {
                    answer -= b.get(i + 1);
                }
            }

            return answer;
        }

        long answer = Long.MIN_VALUE;
        answer = Math.max(recursion(c + 2, 0, a), answer);
        if (be == 0) {
            int n1 = a[c - 1];
            int n2 = a[c + 1];
            int op = a[c];
            calc(a, c);
            answer = Math.max(recursion(c + 2, 1, a), answer);
            recover(a, c, n1, n2, op);
        }

        return answer;
    }

    private static void calc(int[] a, int c) {
        if (a[c] == 0) {
            a[c - 1] += a[c + 1];
            a[c] = -1;
            a[c + 1] = 0;
        } else if (a[c] == 1) {
            a[c - 1] -= a[c + 1];
            a[c] = -1;
            a[c + 1] = 0;
        } else if (a[c] == 2) {
            a[c - 1] *= a[c + 1];
            a[c] = -1;
            a[c + 1] = 0;
        }
    }

    private static void recover(int[] a, int c, int n1, int n2, int op) {
        a[c - 1] = n1;
        a[c + 1] = n2;
        a[c] = op;
    }
}