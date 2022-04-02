package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2529
public class Backjun2529_1 {
    private static final String[] array = {
            "2\n" +
            "< >",
            "9\n" +
            "> < < < > > > < <"
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
        int K = Integer.parseInt(br.readLine());
        char[] a = new char[K];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            a[i] = st.nextToken().charAt(0);
        }

        int[] min = new int[K + 1];
        int[] max = new int[K + 1];
        for (int i = 0; i < K + 1; i++) {
            min[i] = i;
            max[i] = 9 - i;
        }

        do {
            if (check(min, a)) {
                break;
            }
        } while (nextPermutation(min));

        do {
            if (check(max, a)) {
                break;
            }
        } while (prevPermutation(max));

        StringBuilder smi = new StringBuilder();
        StringBuilder sma = new StringBuilder();
        for (int i = 0; i < K + 1; i++) {
            smi.append(min[i]);
            sma.append(max[i]);
        }

        bw.write(sma.toString());
        bw.write("\n");
        bw.write(smi.toString());
        bw.flush();
    }

    // 다음 순열
    private static boolean nextPermutation(int[] a) {
        // 순열의 마지막 수에서 끝나는 가장 긴 감소 수열 찾기
        int i = a.length - 1;
        while (i > 0 && a[i - 1] >= a[i]) {
            i--;
        }

        // 다음 순열이 없음
        if (i <= 0) {
            return false;
        }

        // 가장 긴 감소 수열 중 i - 1 번째보다 작은 수 중 가장 큰수
        int j = a.length - 1;
        while (a[j] <= a[i - 1]) {
            j--;
        }

        // i - 1, j 자리변경
        int temp = a[i - 1];
        a[i - 1] = a[j];
        a[j] = temp;

        // i 번째부터 순열 뒤집기
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

    // 이전 순열
    private static boolean prevPermutation(int[] a) {
        // 순열의 마지막 수에서 끝나는 가장 긴 증가 수열 찾기
        int i = a.length - 1;
        while (i > 0 && a[i - 1] <= a[i]) {
            i--;
        }

        // 가장 긴 증가 수열 중 i - 1 번째보다 큰 수 중 가장 작은 수
        int j = a.length - 1;
        while (a[j] >= a[i - 1]) {
            j--;
        }

        // i - 1, j 자리변경
        int temp = a[i - 1];
        a[i - 1] = a[j];
        a[j] = temp;

        // i 번째부터 순열 뒤집기
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

    private static boolean check(int[] num, char[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == '<' && num[i] > num[i + 1]) {
                return false;
            }

            if (a[i] == '>' && num[i] < num[i + 1]) {
                return false;
            }
        }

        return true;
    }
}
