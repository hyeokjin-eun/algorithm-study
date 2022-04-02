package com.company.lecture.algorithm2.bf;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1107
public class Backjun1107 {
    private static boolean[] broken = new boolean[10];
    private static final String[] array = {
            "5457\n" +
            "3\n" +
            "6 7 8",
            "100\n" +
            "5\n" +
            "0 1 2 3 4",
            "500000\n" +
            "8\n" +
            "0 2 3 4 6 7 8 9",
            "101\n" +
            "2\n" +
            "5 8"
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
        int channel = Integer.parseInt(br.readLine());
        int brokenIndex = Integer.parseInt(br.readLine());
        if (brokenIndex != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < brokenIndex; i++) {
                int num = Integer.parseInt(st.nextToken());
                broken[num] = true;
            }
        }

        int min = Math.abs(channel - 100);
        for (int i = 0; i < 1000001; i++) {
            int length = check(i);
            if (0 < length) {
                int press = Math.abs(channel - i);
                if (min > length + press) {
                    min = length + press;
                }
            }
        }

        bw.write(String.valueOf(min));
        bw.flush();
    }

    private static int check (int channel) {
        if (channel == 0) {
            return broken[0] ? 0 : 1;
        }

        int length = 0;
        while (channel > 0) {
            if (broken[channel % 10]) {
                return 0;
            }

            channel /= 10;
            length++;
        }

        return length;
    }
}
