package com.company.bf;

import java.io.*;
import java.util.*;

public class Backjun16198 {
    private static final String[] array = {
            "4\n" +
            "1 2 3 4",
            "5\n" +
            "100 2 1 3 100",
            "7\n" +
            "2 2 7 6 90 5 9",
            "10\n" +
            "1 1 1 1 1 1 1 1 1 1"
    };

    public static void main(String[] args) throws IOException {
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
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] check = new boolean[n];
        int answer = recursion(n, 0, a, 0, check);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion(int n, int cnt, int[] a, int total, boolean[] check) {
        if (cnt == n - 2) {
            return total;
        }

        int answer = 0;
        for (int i = 1; i < n - 1; i++) {
            if (!check[i]) {
                check[i] = true;
                int left = 0;
                int l = i - 1;
                while (0 <= l) {
                    if (!check[l]) {
                        left = a[l];
                        break;
                    }

                    l--;
                }

                int right = 0;
                int r = i + 1;
                while (r < n) {
                    if (!check[r]) {
                        right = a[r];
                        break;
                    }

                    r++;
                }

                int temp = recursion(n, cnt + 1, a, total + (left * right), check);
                check[i] = false;
                if (answer < temp) {
                    answer = temp;
                }
            }
        }

        return answer;
    }
}
