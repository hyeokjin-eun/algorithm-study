package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16943
public class Backjun16943 {
    private static final String[] array = {
            "13 1000000000",
            "1234 3456",
            "1000 5",
            "789 123",
            "10 100"
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
        char[] A = st.nextToken().toCharArray();
        int B = Integer.parseInt(st.nextToken());
        int answer = -1;
        Arrays.sort(A);
        do {
            int num = Integer.parseInt(String.valueOf(A));
            if (A[0] != '0' && num < B) {
                if (answer == -1 || answer < num) {
                    answer = num;
                }
            }
        } while (nextPermutation(A, B));
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static boolean nextPermutation(char[] temp, int B) {
        int i = temp.length - 1;
        while (i > 0 && temp[i - 1] >= temp[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        int j = temp.length - 1;
        while (temp[j] <= temp[i - 1]) {
            j--;
        }

        char t = temp[i - 1];
        temp[i - 1] = temp[j];
        temp[j] = t;
        j = temp.length - 1;
        while (i < j) {
            t = temp[i];
            temp[i] = temp[j];
            temp[j] = t;
            i++;
            j--;
        }

        return Integer.parseInt(String.valueOf(temp)) < B;
    }
}
