package com.company.java.lecture.algorithm1.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/11726
public class Backjun11726 {
    private static final String[] array = {
            "2",
            "9",
            "1000"
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
        // D[num] = D[num - 1]
        // D[num] = D[num - 2]
        int[] temp = new int[num + 1];
        temp[0] = 1;
        temp[1] = 1;
        for (int i = 2; i <= num; i++) {
            temp[i] = temp[i - 1] + temp[i - 2];
            temp[i] %= 10007;
        }

        bw.write(String.valueOf(temp[num]));
        bw.flush();
    }
}
