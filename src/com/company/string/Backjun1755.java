package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1755
public class Backjun1755 {
    private static final String[] array = {
            "8 28",
            "1 99"
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<Pair> a = new ArrayList<>();
        for (int i = M; i <= N; i++) {
            String n = String.valueOf(i);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n.length(); j++) {
                sb.append(converter(String.valueOf(n.charAt(j))));
                if (j != n.length() - 1) {
                    sb.append(" ");
                }
            }

            a.add(new Pair(n, sb.toString()));
        }

        a.sort(Comparator.comparing(o -> o.e));

        for (int i = 0; i < a.size(); i++) {
            bw.write(a.get(i).n);
            if ((i + 1) % 10 == 0) {
                bw.write("\n");
            } else if (i != a.size() - 1) {
                bw.write(" ");
            }
        }

        bw.flush();
    }

    private static String converter(String n) {
        if (n.equals("0")) {
            return "zero";
        } else if (n.equals("1")) {
            return "one";
        } else if (n.equals("2")) {
            return "two";
        } else if (n.equals("3")) {
            return "three";
        } else if (n.equals("4")) {
            return "four";
        } else if (n.equals("5")) {
            return "five";
        } else if (n.equals("6")) {
            return "six";
        } else if (n.equals("7")) {
            return "seven";
        } else if (n.equals("8")) {
            return "eight";
        } else if (n.equals("9")) {
            return "nine";
        } else {
            return "";
        }
    }

    private static class Pair{
        String n;
        String e;
        public Pair(String n, String e) {
            this.n = n;
            this.e = e;
        }
    }
}
