package com.company.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1159
public class Backjun1159 {
    private static final String[] array = {
            "18\n" +
            "babic\n" +
            "keksic\n" +
            "boric\n" +
            "bukic\n" +
            "sarmic\n" +
            "balic\n" +
            "kruzic\n" +
            "hrenovkic\n" +
            "beslic\n" +
            "boksic\n" +
            "krafnic\n" +
            "pecivic\n" +
            "klavirkovic\n" +
            "kukumaric\n" +
            "sunkic\n" +
            "kolacic\n" +
            "kovacic\n" +
            "prijestolonasljednikovi"
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
        int[] a = new int[26];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            a[s.charAt(0) - 'a']++;
        }

        boolean ok = false;
        for (int i = 0; i < 26; i++) {
            if (5 <= a[i]) {
                ok = true;
                bw.write(String.valueOf((char) (i + 'a')));
            }
        }

        if (!ok) {
            bw.write("PREDAJA");
        }

        bw.flush();
    }
}