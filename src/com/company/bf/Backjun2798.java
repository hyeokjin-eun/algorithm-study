package com.company.bf;

import java.io.*;
import java.util.*;

public class Backjun2798 {
    private static int N;
    private static int M;
    private static int[] a;
    private static final String[] array = {
            "5 21\n" +
            "5 6 7 8 9",
            "10 500\n" +
            "93 181 245 214 315 36 185 138 216 295"
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

    private static void test() {

    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        a = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = stoi(st.nextToken());
        }

        int[] b = new int[N];
        for (int i = 0; i < 3; i++) {
            b[i] = 1;
        }

        Arrays.sort(b);
        int answer = 0;
        do {
            int temp = 0;
            for (int i = 0; i < b.length; i++) {
                if (b[i] == 1) {
                    temp += a[i];
                }
            }

            if (temp <= M) {
                answer = Math.abs(temp - M) < Math.abs(answer - M) ? temp : answer;
            }
        } while(nextPermutation(b));
        bw.write(String.valueOf(answer));
        bw.flush();
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
}