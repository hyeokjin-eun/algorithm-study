package com.company.java.lecture.algorithm2.bf;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/11723
public class Backjun11723 {
    private static final String[] array = {
            "26\n" +
            "add 1\n" +
            "add 2\n" +
            "check 1\n" +
            "check 2\n" +
            "check 3\n" +
            "remove 2\n" +
            "check 1\n" +
            "check 2\n" +
            "toggle 3\n" +
            "check 1\n" +
            "check 2\n" +
            "check 3\n" +
            "check 4\n" +
            "all\n" +
            "check 10\n" +
            "check 20\n" +
            "toggle 10\n" +
            "remove 20\n" +
            "check 10\n" +
            "check 20\n" +
            "empty\n" +
            "check 1\n" +
            "toggle 1\n" +
            "check 1\n" +
            "toggle 1\n" +
            "check 1"
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
        int m = Integer.parseInt(br.readLine());
        int s = 0;
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("add")) {
                int x = Integer.parseInt(st.nextToken());
                s = s | (1 << x);
            } else if (command.equals("remove")) {
                int x = Integer.parseInt(st.nextToken());
                s = s & ~(1 << x);
            } else if (command.equals("check")) {
                int x = Integer.parseInt(st.nextToken());
                if ((s & (1 << x)) > 0) {
                    bw.write(String.valueOf(1));
                } else {
                    bw.write(String.valueOf(0));
                }

                bw.write("\n");
            } else if (command.equals("toggle")) {
                int x = Integer.parseInt(st.nextToken());
                s = s ^ (1 << x);
            } else if (command.equals("all")) {
                s = (1 << 21) - 1;
            } else {
                s = 0;
            }
        }

        bw.flush();
    }
}
