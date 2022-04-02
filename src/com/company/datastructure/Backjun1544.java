package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1544
public class Backjun1544 {
    private static final String[] array = {
            "5\n" +
            "picture\n" +
            "turepic\n" +
            "icturep\n" +
            "word\n" +
            "ordw"
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
        int N = Integer.parseInt(br.readLine());
        String[] s = new String[N];
        for (int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            s[i] = new String(c);
        }

        int answer = 0;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < s.length; i++) {
            if (set.isEmpty()) {
                answer++;
                addSet(s[i], set);
            } else {
                if (!set.contains(s[i])) {
                    answer++;
                    addSet(s[i], set);
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static void addSet(String s, HashSet<String> set) {
        for (int j = 0; j < s.length(); j++) {
            StringBuilder sb = new StringBuilder();
            sb.append(s, j, s.length());
            sb.append(s, 0, j);
            set.add(sb.toString());
        }
    }
}
