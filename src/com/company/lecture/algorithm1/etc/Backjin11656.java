package com.company.lecture.algorithm1.etc;

import java.io.*;
import java.util.Arrays;

public class Backjin11656 {
    private static final String[] array = {
            "baekjoon",
            "aab"
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
        String inputString = br.readLine();
        int stringLength = inputString.length();
        String[] answerArrray = new String[stringLength];
        for (int i = 0; i < stringLength; i++) {
            answerArrray[i] = inputString.substring(i);
        }

        Arrays.sort(answerArrray);
        for (String answer : answerArrray) {
            bw.write(answer);
            bw.write("\n");
        }

        bw.flush();
    }
}
