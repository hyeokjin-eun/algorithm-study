package com.company.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1769
public class Backjun1769 {
    private static final String[] array = {
            "1234567"
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
        String x = br.readLine();
        int answer = 0;
        while (x.length() != 1) {
            answer++;
            int t = 0;
            for (int i = 0; i < x.length(); i++) {
                t += x.charAt(i) - '0';
            }

            x = String.valueOf(t);
        }

        bw.write(String.valueOf(answer));
        bw.write("\n");
        bw.write(stoi(x) % 3 == 0 ? "YES" : "NO");
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
