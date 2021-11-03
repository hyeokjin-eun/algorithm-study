package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/17088
public class Backjun17088 {
    private static final String[] array = {
            "4\n" +
            "24 21 14 10"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int answer = bf(a, N);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bf(int[] a, int N) {
        if (N == 1) {
            return 0;
        }

        int answer = -1;
        for (int d1 = -1; d1 <= 1; d1++) {
            for (int d2 = -1; d2 <= 1; d2++) {
                int c = 0;
                if (d1 != 0) {
                    c++;
                }

                if (d2 != 0) {
                    c++;
                }

                int a0 = a[0] + d1;
                int d = (a[1] + d2) - a0;
                boolean ok = true;
                int t = a0 + d;
                for (int i = 2; i < N; i++) {
                    t += d;
                    if (a[i] == t) {
                        continue;
                    } else if (a[i] - 1 == t || a[i] + 1 == t) {
                        c++;
                    } else {
                        ok = false;
                        break;
                    }
                }

                if (ok && (answer == -1 || c < answer)) {
                    answer = c;
                }
            }
        }

        return answer;
    }
}