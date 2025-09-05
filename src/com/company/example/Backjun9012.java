package com.company.example;

import java.io.*;
import java.util.*;

public class Backjun9012 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            if (t != 0) {
                bw.write("\n");
            }

            String s = br.readLine();
            bw.write(solve(s));
        }

        bw.flush();
    }

    private static String solve(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (c == '(') {
                    stack.push(c);
                } else {
                    char top = stack.pop();
                    if (top != '(') {
                        stack.push(c);
                    }
                }
            }
        }

        return stack.isEmpty() ? "YES" : "NO";
    }
}
