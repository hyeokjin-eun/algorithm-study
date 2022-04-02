package com.company.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/16637
public class Backjun16637 {
    private static final String[] array = {
            "9\n" +
            "3+8*7-9*2",
            "19\n" +
            "1*2+3*4*5-6*7*8*9*0",
            "5\n" +
            "8*3+5"
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
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        Pair[] a = new Pair[N];
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                a[i] = new Pair(s.charAt(i) - '0', 0);
            } else {
                int op = 1;
                if (s.charAt(i) == '-') op = 2;
                else if (s.charAt(i) == '*') op = 3;
                a[i] = new Pair(0, op);
            }
        }

        int m = (N - 1) / 2;
        int answer = -2147483648;
        for (int i = 0; i < (1 << m); i++) {
            boolean check = true;
            for (int j = 0; j < m - 1; j++) {
                if ((i & (1 << j)) > 0 && (i & (1 << (j + 1))) > 0) {
                    check = false;
                }
            }

            if (!check) {
                continue;
            }

            Pair[] b = new Pair[N];
            for (int j = 0; j < N; j++) {
                b[j] = new Pair(a[j].num, a[j].op);
            }

            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) > 0) {
                    int k = 2 * j + 1;
                    if (b[k].op == 1) {
                        b[k - 1].num += b[k + 1].num;
                        b[k + 1].num = 0;
                    } else if (b[k].op == 2) {
                        b[k - 1].num -= b[k + 1].num;
                        b[k].op = 1;
                        b[k + 1].num = 0;
                    } else if (b[k].op == 3) {
                        b[k - 1].num *= b[k + 1].num;
                        b[k].op = 1;
                        b[k + 1].num = 0;
                    }
                }
            }

            int temp = b[0].num;
            for (int j = 0; j < m; j++) {
                int k = 2 * j + 1;
                if (b[k].op == 1) {
                    temp += b[k + 1].num;
                } else if (b[k].op == 2) {
                    temp -= b[k + 1].num;
                } else if (b[k].op == 3) {
                    temp *= b[k + 1].num;
                }
            }

            answer = Math.max(answer, temp);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static class Pair {
        int num;
        int op;
        public Pair(int num, int op) {
            this.num = num;
            this.op = op;
        }
    }
}