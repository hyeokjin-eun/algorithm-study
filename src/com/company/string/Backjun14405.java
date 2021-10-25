package com.company.string;

import java.io.*;
import java.util.*;

public class Backjun14405 {
    private static final String[] array = {
            "pikapi",
            "chupikachupipichu",
            "pikaqiu"
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
        String s = br.readLine();
        s = s.replaceAll("pi|ka|chu", "");
        bw.write(s.length() == 0 ? "YES" : "NO");
        bw.flush();
    }
}
