package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/9733
public class Backjun9733 {
    private static final String[] print = {
            "Re","Pt","Cc","Ea","Tb","Cm","Ex"
    };

    private static final String[] array = {
            "Cc Pt Pt Re Tb Re Cm Cm Re Pt Pt Re Ea Ea Pt Pt\n" +
            "Pt Re Re Cb Cb Pt Pt Cb"
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
        HashMap<String, Integer> map = new HashMap<>();
        int total = 0;
        for (int i = 0; i < print.length; i++) {
            map.put(print[i], 0);
        }

        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreElements()) {
                String s = st.nextToken();
                if (map.containsKey(s)) {
                    map.replace(s, map.get(s) + 1);
                }

                total++;
            }
        }

        for (int i = 0; i < print.length; i++) {
            double temp = (double) map.get(print[i]) / (double) total;
            bw.write(print[i] + " " + map.get(print[i]) + " " + String.format("%.2f", temp));
            bw.write("\n");
        }

        bw.write("Total " + total + " 1.00");
        bw.flush();
    }
}
