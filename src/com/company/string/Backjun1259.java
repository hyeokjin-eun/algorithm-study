package com.company.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1259
public class Backjun1259 {
    private static final String[] array = {
            "121\n" +
            "1231\n" +
            "12421\n" +
            "0"
    };

    public static void main (String[] args) throws IOException {
        // TEST
        test();
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

    private static void test() {
        boolean test1 = check("121");
        assert test1;
        boolean test2 = check("1231");
        assert !test2;
        boolean test3 = check("1221");
        assert test3;
    }

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        while (true) {
            String s = br.readLine();
            if (s.equals("0")) {
                break;
            }

            if (check(s)) {
                bw.write("yes");
            } else {
                bw.write("no");
            }

            bw.write("\n");
        }

        bw.flush();
    }

    private static boolean check(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }

        return true;
    }
}