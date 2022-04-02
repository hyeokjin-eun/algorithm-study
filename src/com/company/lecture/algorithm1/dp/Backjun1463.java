package com.company.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1463
public class Backjun1463 {
    private static final String[] array = {
            "2",
            "10"
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
        int num = Integer.parseInt(br.readLine());
        int[] memory = new int[num + 1];
        memory[1] = 0;
        for (int i = 2; i <= num; i++) {
            memory[i] = memory[i - 1] + 1;
            if (i % 2 == 0 && memory[i] > memory[i / 2] + 1) memory[i] = memory[i / 2] + 1;
            if (i % 3 == 0 && memory[i] > memory[i / 3] + 1) memory[i] = memory[i / 3] + 1;
        }

        bw.write(String.valueOf(memory[num]));
        bw.flush();
    }
}
