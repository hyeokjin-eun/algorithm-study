package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/15686
public class Backjun15686_1 {
    private static final String[] array = {
            "5 3\n" +
            "0 0 1 0 0\n" +
            "0 0 2 0 1\n" +
            "0 1 2 0 0\n" +
            "0 0 1 0 0\n" +
            "0 0 0 0 2",
            "5 2\n" +
            "0 2 0 1 0\n" +
            "1 0 1 0 0\n" +
            "0 0 0 0 0\n" +
            "2 0 0 1 1\n" +
            "2 2 0 1 2",
            "5 1\n" +
            "1 2 0 0 0\n" +
            "1 2 0 0 0\n" +
            "1 2 0 0 0\n" +
            "1 2 0 0 0\n" +
            "1 2 0 0 0",
            "5 1\n" +
            "1 2 0 2 1\n" +
            "1 2 0 2 1\n" +
            "1 2 0 2 1\n" +
            "1 2 0 2 1\n" +
            "1 2 0 2 1"
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
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] a = new int[N][N];
        ArrayList<Pair> c = new ArrayList<>();
        ArrayList<Pair> h = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                a[i][j] = num;
                if (num == 2) {
                    c.add(new Pair(j, i, 0));
                } else if (num == 1) {
                    h.add(new Pair(j, i, 0));
                }
            }
        }

        int[] b = new int[c.size()];
        for (int i = 0; i < M; i++) {
            b[i] = 1;
        }

        Arrays.sort(b);
        int answer = -1;
        do {
            int sum = 0;
            for (Pair house : h) {
                int min = -1;
                for (int i = 0; i < c.size(); i++) {
                    if (b[i] == 0) {
                        continue;
                    }

                    Pair chicken = c.get(i);
                    int d1 = Math.abs(house.x - chicken.x);
                    int d2 = Math.abs(house.y - chicken.y);
                    int dist = d1 + d2;
                    if (min == -1 || dist < min) {
                        min = dist;
                    }
                }

                sum += min;
            }

            if (answer == -1 || sum < answer) {
                answer = sum;
            }
        } while(nextPermutation(b));
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static boolean nextPermutation(int[] a) {
        int i = a.length - 1;
        while (i > 0 && a[i - 1] >= a[i]) {
            i--;
        }

        if (i < 1) {
            return false;
        }

        int j = a.length - 1;
        while (a[i - 1] >= a[j]) {
            j--;
        }

        int temp = a[i - 1];
        a[i - 1] = a[j];
        a[j] = temp;
        j = a.length - 1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }

        return true;
    }

    private static class Pair {
        int x;
        int y;
        int s;
        public Pair(int x, int y, int s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }
    }
}
