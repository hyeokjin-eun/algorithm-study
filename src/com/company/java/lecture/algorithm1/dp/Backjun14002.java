package com.company.java.lecture.algorithm1.dp;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Stack;

// link
// https://www.acmicpc.net/problem/14002
public class Backjun14002 {
    private static final String[] array = {
            "6\n" +
            "10 20 10 30 20 50",
            "1\n" +
            "20",
            "2\n" +
            "20 10",
            "6\n" +
            "1 3 5 7 2 3",
            "3\n" +
            "3 1 2"
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
        int[][] a = new int[index][3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < index; i++) {
            a[i][0] = Integer.parseInt(st.nextToken());
            a[i][1] = 1;
            a[i][2] = -1;
        }

        int count = a[0][1];
        int aIndex = 0;
        for (int i = 0; i < index; i++) {
            for (int j = 1; j <= i; j++) {
                if (a[i][0] > a[i - j][0] && a[i][1] < a[i - j][1] + 1) {
                    a[i][1] = a[i - j][1] + 1;
                    a[i][2] = i - j;
                    if (count < a[i][1]) {
                        count = a[i][1];
                        aIndex = i;
                    }
                }
            }
        }

        bw.write(String.valueOf(count));
        bw.write("\n");
        Stack<Integer> stack = new Stack<>();
        for (int i = aIndex; i != -1; i = a[i][2]) {
            stack.push(a[i][0]);
        }

        while (!stack.empty()) {
            bw.write(String.valueOf(stack.pop()));
            bw.write(" ");
        }

        bw.flush();
    }
}
