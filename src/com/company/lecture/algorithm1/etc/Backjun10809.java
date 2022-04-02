package com.company.lecture.algorithm1.etc;

import java.io.*;
import java.util.Arrays;

// link
// https://www.acmicpc.net/problem/10809
public class Backjun10809 {
    private static final String[] array = {
            "baekjoon"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        char[] command = br.readLine().toCharArray();
        int[] indexArray = new int[26];
        Arrays.fill(indexArray, -1);
        for (int i = 0; i < command.length; i++) {
            char charInput = command[i];
            if (indexArray[charInput - 97] == -1) indexArray[charInput -97] = i;
        }

        for (int index : indexArray) {
            bw.write(String.valueOf(index));
            bw.write(" ");
        }

        bw.flush();
    }
}
