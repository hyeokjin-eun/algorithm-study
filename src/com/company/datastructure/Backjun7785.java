package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/7785
public class Backjun7785 {
    private static final String[] array = {
            "4\n" +
            "Baha enter\n" +
            "Askar enter\n" +
            "Baha leave\n" +
            "Artem enter"
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
        ArrayList<String> a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String action = st.nextToken();
            if (action.equals("enter")) {
                a.add(name);
            } else {
                a.remove(name);
            }
        }

        a.sort(Collections.reverseOrder());
        for (int i = 0; i < a.size(); i++) {
            bw.write(String.valueOf(a.get(i)));
            if (i != a.size() - 1) {
                bw.write("\n");
            }
        }

        bw.flush();
    }
}