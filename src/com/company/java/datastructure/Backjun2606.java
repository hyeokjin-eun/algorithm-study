package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2606
public class Backjun2606 {
    private static int N;
    private static int M;
    private static int[] b;
    private static int[] r;
    private static final String[] array = {
            "7\n" +
            "6\n" +
            "1 2\n" +
            "2 3\n" +
            "1 5\n" +
            "5 2\n" +
            "5 6\n" +
            "4 7"
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
        N = stoi(br.readLine());
        M = stoi(br.readLine());
        b = new int[N];
        r = new int[N];
        for (int i = 0; i < N; i++) {
            b[i] = i;
            r[i] = 0;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            union(stoi(st.nextToken()) - 1, stoi(st.nextToken()) - 1);
        }

        int answer = 0;
        for (int i = 1; i < N; i++) {
            if (find(0) == find(i)) {
                answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) {
            return;
        }

        if (r[x] < r[y]) {
            int t = x;
            x = y;
            y = t;
        }

        b[y] = x;
        if (r[x] == r[y]) {
            r[x] = r[y] + 1;
        }
    }

    private static int find(int x) {
        if (x == b[x]) {
            return x;
        } else {
            return b[x] = find(b[x]);
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}