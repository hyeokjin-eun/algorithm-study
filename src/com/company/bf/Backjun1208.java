package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1208
public class Backjun1208 {
    private static int N;
    private static int S;
    private static int[] a;
    private static final String[] array = {
            "5 0\n" +
            "-7 -3 -2 5 8",
            "TEST MADE"
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
        S = stoi(st.nextToken());
        a = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = stoi(st.nextToken());
        }

        int sm = N / 2;
        int fm = N - sm;
        int[] first = new int[1 << fm];
        int[] second = new int[1 << sm];
        for (int i = 0; i < (1<<fm); i++) {
            for (int k = 0; k < fm; k++) {
                if ((i & (1 << k)) == (1 << k)) {
                    first[i] += a[k];
                }
            }
        }

        for (int i = 0; i < (1<<sm); i++) {
            for (int k = 0; k < sm; k++) {
                if ((i & (1 << k)) == (1 << k)) {
                    second[i] += a[k + fm];
                }
            }
        }

        Arrays.sort(first);
        Arrays.sort(second);
        fm = (1 << fm);
        sm = (1 << sm);
        for (int i = 0; i < sm / 2; i++) {
            int temp = second[i];
            second[i] = second[sm - i - 1];
            second[sm - i - 1] = temp;
        }

        int l = 0;
        int r = 0;
        long answer = 0;
        while (l < fm && r < sm) {
            if (first[l] + second[r] == S) {
                long t1 = 1;
                long t2 = 1;
                l++;
                r++;
                while (l < fm && first[l] == first[l - 1]) {
                    t1++;
                    l++;
                }

                while (r < sm && second[r] == second[r - 1]) {
                    t2++;
                    r++;
                }

                answer += t1 * t2;
            } else if (first[l] + second[r] < S) {
                l++;
            } else {
                r++;
            }
        }


        bw.write(String.valueOf(S == 0 ? --answer : answer));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("40 0\n");
        for (int i = 0; i < 40; i++) {
            sb.append("0");
            if (i != 39) {
                sb.append(" ");
            }
        }

        array[1] = sb.toString();
    }
}
