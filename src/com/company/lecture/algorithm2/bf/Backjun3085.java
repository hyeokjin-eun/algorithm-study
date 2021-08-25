package com.company.lecture.algorithm2.bf;

import java.io.*;

// link
// https://www.acmicpc.net/problem/3085
public class Backjun3085 {
    private static final String[] array = {
            "3\n" +
            "CCP\n" +
            "CCP\n" +
            "PPC",
            "4\n" +
            "PPPP\n" +
            "CYZY\n" +
            "CCPY\n" +
            "PPCC",
            "5\n" +
            "YCPZY\n" +
            "CYZZP\n" +
            "CCPPP\n" +
            "YCYZC\n" +
            "CPPZZ"
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
        int index = Integer.parseInt(br.readLine());
        char[][] candy = new char[index][index];
        for (int i = 0; i < index; i++) {
            char[] inputs = br.readLine().toCharArray();
            for (int j = 0; j < index; j++) {
                candy[i][j] = inputs[j];
            }
        }


        for (int i = 0; i < index - 1; i++) {
            for () {

            }
        }
    }
}
