package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/7568
public class Backjun7568 {
    private static final String[] array = {
            "5\n" +
            "55 185\n" +
            "58 183\n" +
            "88 186\n" +
            "60 175\n" +
            "46 155"
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
        int N = stoi(br.readLine());
        int[] rank = new int[N];
        Arrays.fill(rank, 1);

        Pair[] pairs = new Pair[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int weight = stoi(st.nextToken());
            int tall = stoi(st.nextToken());
            pairs[i] = new Pair(weight, tall);
        }

        bf(rank, pairs, N);
        for (int i = 0; i < N; i++) {
            bw.write(String.valueOf(rank[i]));
            if (i != N - 1) {
                bw.write(" ");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void bf(int[] rank, Pair[] pairs, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }

                if (isWeight(pairs[i], pairs[j]) && isTall(pairs[i], pairs[j])) {
                    rank[i]++;
                }
            }
        }
    }

    private static boolean isWeight(Pair first, Pair second) {
        return first.weight < second.weight;
    }

    private static boolean isTall(Pair first, Pair second) {
        return first.tall < second.tall;
    }

    private static class Pair {
        int weight;
        int tall;
        public Pair(int weight, int tall) {
            this.weight = weight;
            this.tall = tall;
        }
    }
}