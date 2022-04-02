package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1717
public class Backjun1717 {
    private static int N;
    private static int M;
    private static int[] b;
    private static int[] r;
    private static final String[] array = {
            "7 8\n" +
            "0 1 3\n" +
            "1 1 7\n" +
            "0 7 6\n" +
            "1 7 1\n" +
            "0 3 7\n" +
            "0 4 2\n" +
            "0 1 1\n" +
            "1 1 1"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        b = new int[N + 1];
        r = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            b[i] = i;
            r[i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = stoi(st.nextToken());
            if (command == 0) {
                union(stoi(st.nextToken()), stoi(st.nextToken()));
            } else {
                if (find(stoi(st.nextToken())) == find(stoi(st.nextToken()))) {
                    bw.write("YES");
                } else {
                    bw.write("NO");
                }

                if (i != M - 1) {
                    bw.write("\n");
                }
            }
        }

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
            int temp = find(b[x]);
            b[x] = temp;
            return temp;
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {

    }
}
