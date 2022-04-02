package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2675
public class Backjun2675 {
    private static final String[] array = {
            "2\n" +
            "3 ABC\n" +
            "5 /HTP"
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

    private static void solution(String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int T = stoi(br.readLine());
        while (T-- != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int repeat = stoi(st.nextToken());
            char[] str = st.nextToken().toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : str) {
                sb.append(repeat(c, repeat));
            }

            bw.write(sb.toString());
            if (T != 0) {
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static String repeat(char c, int n) {
        StringBuilder sb = new StringBuilder();
        while (n-- != 0) {
            sb.append(c);
        }

        return sb.toString();
    }

    private static void test() {
        String s = repeat('a', 3);
        assert s.equals("aaa");
    }
}