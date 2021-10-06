package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2493
public class Backjun2493 {
    private static final String[] array = {
            "5\n" +
            "6 9 5 7 4"
    };

    public static void main(String[] args) throws IOException {
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
        int[][] a = new int[N][2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i][0] = Integer.parseInt(st.nextToken());
            a[i][1] = i;
        }

        Stack<int[]> stack = new Stack<>();
        int[] answer = new int[N];
        for (int i = N - 1; 0 <= i; i--) {
            if (stack.isEmpty()) {
                stack.push(a[i]);
            } else {
                while (!stack.isEmpty() && stack.peek()[0] <= a[i][0]) {
                    answer[stack.peek()[1]] = i + 1;
                    stack.pop();
                }

                stack.push(a[i]);
            }
        }

        for (int i = 0; i < N; i++) {
            bw.write(String.valueOf(answer[i]));
            if (i != N - 1) {
                bw.write(" ");
            }
        }

        bw.flush();
    }
}
