package com.company.lecture.algorithm2.bf;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/2529
public class Backjun2529 {
    private static final int[] a = new int[10];
    private static final boolean[] c = new boolean[10];
    private static final String[] array = {
            "2\n" +
            "< >",
            "9\n" +
            "> < < < > > > < <"
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
        int n = Integer.parseInt(br.readLine());
        char[] chars = new char[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            chars[i] = st.nextToken().charAt(0);
        }

        ArrayList<String> answer = new ArrayList<>();
        dp(0, n, chars, answer);
        bw.write(answer.get(answer.size() - 1));
        bw.write("\n");
        bw.write(answer.get(0));
        bw.flush();
    }

    private static void dp (int index, int n, char[] chars, ArrayList<String> answer) {
        if (index == n + 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n + 1; i++) {
                sb.append(a[i]);
            }

            answer.add(sb.toString());
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (c[i]) {
                continue;
            }

            if (index != 0) {
                if (chars[index - 1] == '<') {
                    if (a[index - 1] > i) {
                        continue;
                    }
                } else {
                    if (a[index - 1] < i) {
                        continue;
                    }
                }
            }

            c[i] = true;
            a[index] = i;
            dp(index + 1, n, chars, answer);
            c[i] = false;
        }
    }
}
