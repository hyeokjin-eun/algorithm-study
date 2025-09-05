package com.company.example;

import java.io.*;

public class Backjun17609 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(in.readLine());
        String[] temp = new String[n];
        for (int i = 0; i < n; i++) {
            temp[i] = in.readLine();
        }

        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            answer[i] = solve(temp[i]);
        }

        for (int i = 0; i < n; i++) {
            out.write(answer[i] + "\n");
        }

        out.flush();
    }

    private static int solve(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                boolean leftSkip = solve2(s, left + 1, right);
                boolean rightSkip = solve2(s, left, right - 1);
                if (leftSkip || rightSkip) {
                    return 1;
                } else {
                    return 2;
                }
            }

            left++;
            right--;
        }

        return 0;
    }

    private static boolean solve2(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
