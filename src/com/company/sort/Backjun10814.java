package com.company.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10814
public class Backjun10814 {
    private static final String[] array = {
            "3\n" +
            "21 Junkyu\n" +
            "21 Dohyun\n" +
            "20 Sunyoung"
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
        Pair[] a = new Pair[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i] = new Pair(Integer.parseInt(st.nextToken()), st.nextToken(), i);
        }

        Arrays.sort(a, (o1, o2) -> {
            if (o1.n == o2.n) {
                return o1.i - o2.i;
            }

            return o1.n - o2.n;
        });

        for (int i = 0; i < N; i++) {
            bw.write(String.valueOf(a[i].n));
            bw.write(" ");
            bw.write(a[i].s);
            if (i != N - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static class Pair {
        int n ;
        int i;
        String s;
        public Pair(int n, String s, int i) {
            this.n = n;
            this.s = s;
            this.i = i;
        }
    }
}