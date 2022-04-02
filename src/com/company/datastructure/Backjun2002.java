package com.company.datastructure;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2002
public class Backjun2002 {
    private static final String[] array = {
            "4\n" +
            "ZG431SN\n" +
            "ZG5080K\n" +
            "ST123D\n" +
            "ZG206A\n" +
            "ZG206A\n" +
            "ZG431SN\n" +
            "ZG5080K\n" +
            "ST123D",
            "5\n" +
            "ZG508OK\n" +
            "PU305A\n" +
            "RI604B\n" +
            "ZG206A\n" +
            "ZG232ZF\n" +
            "PU305A\n" +
            "ZG232ZF\n" +
            "ZG206A\n" +
            "ZG508OK\n" +
            "RI604B",
            "5\n" +
            "ZG206A\n" +
            "PU234Q\n" +
            "OS945CK\n" +
            "ZG431SN\n" +
            "ZG5962J\n" +
            "ZG5962J\n" +
            "OS945CK\n" +
            "ZG206A\n" +
            "PU234Q\n" +
            "ZG431SN",
            "6\n" +
            "1\n" +
            "2\n" +
            "3\n" +
            "4\n" +
            "5\n" +
            "6\n" +
            "2\n" +
            "4\n" +
            "3\n" +
            "1\n" +
            "6\n" +
            "5"
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
        String[] a = new String[N];
        String[] b = new String[N];
        boolean[] check = new boolean[N];
        for (int i = 0; i < 2 * N; i++) {
            if (i < N) {
                a[i] = br.readLine();
            } else {
                b[i - N] = br.readLine();
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            String in = a[i];
            for (int j = 0; j < N; j++) {
                String out = b[j];
                if (!in.equals(out)) {
                    if (!check[j]) {
                        answer++;
                        check[j] = true;
                    }
                } else {
                    check[j] = true;
                    break;
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
