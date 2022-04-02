package com.company.java.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1764
public class Backjun1764 {
    private static final String[] array = {
            "3 4\n" +
            "ohhenrie\n" +
            "charlie\n" +
            "baesangwook\n" +
            "obama\n" +
            "baesangwook\n" +
            "ohhenrie\n" +
            "clinton"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Set<String> ns = new HashSet<>(n);
        ArrayList<String> a = new ArrayList<>();
        for (int i = 0; i < n + m; i++) {
            String s = br.readLine();
            if (i < n) {
                ns.add(s);
            } else {
                if (ns.contains(s)) {
                    a.add(s);
                }
            }
        }

        Collections.sort(a);
        bw.write(String.valueOf(a.size()));
        bw.write("\n");
        for (int i = 0; i < a.size(); i++) {
            bw.write(a.get(i));
            bw.write("\n");
        }

        bw.flush();
    }
}
