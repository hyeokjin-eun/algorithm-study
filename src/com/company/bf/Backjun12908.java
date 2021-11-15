package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/12908
public class Backjun12908 {
    private static final String[] array = {
            "3 3\n" +
            "4 5\n" +
            "1000 1001 1000 1002\n" +
            "1000 1003 1000 1004\n" +
            "1000 1005 1000 1006",
            "0 0\n" +
            "20 20\n" +
            "1 1 18 20\n" +
            "1000 1003 1000 1004\n" +
            "1000 1005 1000 1006",
            "0 0\n" +
            "20 20\n" +
            "1000 1003 1000 1004\n" +
            "18 20 1 1\n" +
            "1000 1005 1000 1006",
            "10 10\n" +
            "10000 20000\n" +
            "1000 1003 1000 1004\n" +
            "3 3 10004 20002\n" +
            "1000 1005 1000 1006",
            "3 7\n" +
            "10000 30000\n" +
            "3 10 5200 4900\n" +
            "12212 8699 9999 30011\n" +
            "12200 8701 5203 4845"
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
        int sx = stoi(st.nextToken());
        int sy = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int ex = stoi(st.nextToken());
        int ey = stoi(st.nextToken());
        Pair[] a = new Pair[7];
        Pair e = new Pair(ex, ey, ex, ey);
        a[0] = e;
        for (int i = 1; i < 7; i+=2) {
            st = new StringTokenizer(br.readLine());
            int fx = stoi(st.nextToken());
            int fy = stoi(st.nextToken());
            int tx = stoi(st.nextToken());
            int ty = stoi(st.nextToken());
            a[i] = new Pair(fx, fy, tx, ty);
            a[i + 1] = new Pair(tx, ty, fx, fy);
        }

        int[] b = new int[7];
        for (int i = 0; i < 7; i++) {
            b[i] = i;
        }

        long answer = Integer.MAX_VALUE;
        do {
            long dist = 0;
            int nx = sx;
            int ny = sy;
            for (int i = 0; i < 7; i++) {
                dist += getDistance(nx, ny, a[b[i]].fx, a[b[i]].fy);
                if (a[b[i]].fx == ex && a[b[i]].fy == ey) {
                    break;
                }

                dist += 10;
                nx = a[b[i]].tx;
                ny = a[b[i]].ty;
            }

            answer = Math.min(answer, dist);
        } while(nextPermutation(b));

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static long getDistance(int fx, int fy, int tx, int ty) {
        return Math.abs(fx - tx) + Math.abs(fy - ty);
    }

    private static boolean nextPermutation(int[] b) {
        int i = b.length - 1;
        while (i > 0 && b[i - 1] >= b[i]) {
            i--;
        }

        if (i < 1) {
            return false;
        }

        int j = b.length - 1;
        while (b[i - 1] >= b[j]) {
            j--;
        }

        int temp = b[i - 1];
        b[i - 1] = b[j];
        b[j] = temp;
        j = b.length - 1;
        while (i < j) {
            temp = b[i];
            b[i] = b[j];
            b[j] = temp;
            i++;
            j--;
        }

        return true;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static class Pair {
        int fx;
        int fy;
        int tx;
        int ty;
        public Pair(int fx, int fy, int tx, int ty) {
            this.fx = fx;
            this.fy = fy;
            this.tx = tx;
            this.ty = ty;
        }
    }
}