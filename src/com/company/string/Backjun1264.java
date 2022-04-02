package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1264
public class Backjun1264 {
    private static final String[] array = {
            "How are you today?\n" +
            "Quite well, thank you, how about yourself?\n" +
            "I live at number twenty four.\n" +
            "#"
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
        while (true) {
            int answer = 0;
            String s = br.readLine().toLowerCase(Locale.ROOT);
            if (s.equals("#")) {
                break;
            }

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    answer++;
                }
            }
            bw.write(String.valueOf(answer));
            bw.write("\n");
        }

        bw.flush();
    }
}
