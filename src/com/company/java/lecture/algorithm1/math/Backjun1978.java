package com.company.java.lecture.algorithm1.math;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1978
public class Backjun1978 {
    private static final String[] array = {
            "4\n" +
            "1 3 5 7"
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
        int count = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < index; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (1 < num) {
                boolean isCheck = true;
                for (int j = 2; j < num / 2 + 1; j++) {
                    if (num % j == 0) {
                        isCheck = false;
                        break;
                    }
                }

                if (isCheck) count++;
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
    }
}
