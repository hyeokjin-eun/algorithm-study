package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/12886
public class Backjun12886 {
    private static final String[] array = {
            "10 15 35",
            "1 1 2"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int sum = A + B + C;
        if (sum % 3 != 0) {
            bw.write(String.valueOf(0));
        } else {
            bw.write(String.valueOf(bfs(A, B, sum)));
        }

        bw.flush();
    }

    private static int bfs(int a, int b, int sum) {
        boolean[][] check = new boolean[sum + 1][sum + 1];
        Queue<Integer> aq = new LinkedList<>();
        Queue<Integer> bq = new LinkedList<>();
        aq.add(a);
        bq.add(b);
        check[a][b] = true;
        while (!aq.isEmpty() && !bq.isEmpty()) {
            int ca = aq.poll();
            int cb = bq.poll();
            int cc = sum - ca - cb;
            if (ca < cb) {
                if (!check[ca + ca][cb - ca]) {
                    check[ca + ca][cb - ca] = true;
                    aq.add(ca + ca);
                    bq.add(cb - ca);
                }
            } else {
                if (!check[cb + cb][ca - cb]) {
                    check[cb + cb][ca - cb] = true;
                    aq.add(cb + cb);
                    bq.add(ca - cb);
                }
            }

            if (ca < cc) {
                if (!check[ca + ca][cc - ca]) {
                    check[ca + ca][cc - ca] = true;
                    aq.add(ca + ca);
                    bq.add(cc - ca);
                }
            } else {
                if (!check[cc + cc][ca - cc]) {
                    check[cc + cc][ca - cc] = true;
                    aq.add(cc + cc);
                    bq.add(ca - cc);
                }
            }

            if (cb < cc) {
                if (!check[cb + cb][cc - cb]) {
                    check[cb + cb][cc - cb] = true;
                    aq.add(cb + cb);
                    bq.add(cc - cb);
                }
            } else {
                if (!check[cc + cc][cb - cc]) {
                    check[cc + cc][cb - cc] = true;
                    aq.add(cc + cc);
                    bq.add(cb - cc);
                }
            }
        }

        return check[sum / 3][sum / 3] ? 1 : 0;
    }
}