package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1339
public class Backjun1339 {
    private static int[] alpha = new int[256];
    private static final String[] array = {
            "2\n" +
            "AAA\n" +
            "AAA",
            "2\n" +
            "GCF\n" +
            "ACDEB",
            "10\n" +
            "A\n" +
            "B\n" +
            "C\n" +
            "D\n" +
            "E\n" +
            "F\n" +
            "G\n" +
            "H\n" +
            "I\n" +
            "J",
            "2\n" +
            "AB\n" +
            "BA"
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
        String[] a = new String[N];
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            a[i] = br.readLine();
            for (int j = 0; j < a[i].length(); j++) {
                set.add(a[i].charAt(j));
            }
        }

        Character[] c = set.toArray(new Character[0]);
        int[] num = new int[set.size()];
        for (int i = 0; i < set.size(); i++) {
            num[i] = 9 - i;
        }

        Arrays.sort(num);
        int answer = 0;
        do {
            int temp = calc(c, num, a);
            if (answer < temp) {
                answer = temp;
            }
        } while(nextPermutation(num));

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static boolean nextPermutation(int[] a) {
        int i = a.length - 1;
        while (i > 0 && a[i - 1] >= a[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        int j  =a.length - 1;
        while (a[j] <= a[i - 1]) {
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

    private static int calc(Character[] c, int[] num, String[] a) {
        for (int i = 0; i < c.length; i++) {
            alpha[c[i]] = num[i];
        }

        int sum = 0;
        for (String s : a) {
            int temp = 0;
            for (char t : s.toCharArray()) {
                temp = temp * 10 + alpha[t];
            }

            sum += temp;
        }

        return sum;
    }
}