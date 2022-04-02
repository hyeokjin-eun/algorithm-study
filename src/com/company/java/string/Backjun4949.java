package com.company.java.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/4949
public class Backjun4949 {
    private static final String[] array = {
            "So when I die (the [first] I will see in (heaven) is a score list).\n" +
            "[ first in ] ( first out ).\n" +
            "Half Moon tonight (At least it is better than no Moon at all].\n" +
            "A rope may form )( a trail in a maze.\n" +
            "Help( I[m being held prisoner in a fortune cookie factory)].\n" +
            "([ (([( [ ] ) ( ) (( ))] )) ]).\n" +
            " .\n" +
            "."
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
        while (true) {
            String s = br.readLine();
            if (".".equals(s)) {
                break;
            }

            boolean check = check(s);
            bw.write(check ? "yes" : "no");
            bw.write("\n");
        }

        bw.flush();
    }

    private static boolean check(String s) {
        boolean check = true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            int cs = s.charAt(i);
            if (cs == '(') {
                stack.push('(');
            } else if (cs == '[') {
                stack.push('[');
            } else if (cs == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    check = false;
                    break;
                }
            } else if (cs == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    check = false;
                    break;
                }
            }
        }

        if (!stack.isEmpty()) {
            check = false;
        }

        return check;
    }
}
