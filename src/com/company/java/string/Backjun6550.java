package com.company.java.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/6550
public class Backjun6550 {
    private static final String[] array = {
            "sequence subsequence\n" +
            "person compression\n" +
            "VERDI vivaVittorioEmanueleReDiItalia\n" +
            "caseDoesMatter CaseDoesMatter"
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
            String str = br.readLine();
            if (str == null) {
                break;
            }

            StringTokenizer st = new StringTokenizer(str);
            char[] s = st.nextToken().toCharArray();
            char[] t = st.nextToken().toCharArray();
            int j = 0;
            boolean ok = false;
            for (int i = 0; i < t.length; i++) {
                if (s[j] == t[i]) {
                    j++;
                    if (j == s.length) {
                        ok = true;
                        break;
                    }
                }
            }

            bw.write(ok ? "Yes" : "No");
            bw.write("\n");
        }

        bw.flush();
    }
}