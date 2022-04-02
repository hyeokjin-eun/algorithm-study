package com.company.java.lecture.algorithm2.graph;

import java.io.*;
import java.util.ArrayList;

// link
// https://www.acmicpc.net/problem/13023
public class Backjun13023 {
    private static final String[] array = {
            "5 4\n" +
            "0 1\n" +
            "1 2\n" +
            "2 3\n" +
            "3 4",
            "5 5\n" +
            "0 1\n" +
            "1 2\n" +
            "2 3\n" +
            "3 0\n" +
            "1 4",
            "6 5\n" +
            "0 1\n" +
            "0 2\n" +
            "0 3\n" +
            "0 4\n" +
            "0 5",
            "8 8\n" +
            "1 7\n" +
            "3 7\n" +
            "4 7\n" +
            "3 4\n" +
            "4 6\n" +
            "3 5\n" +
            "0 4\n" +
            "2 7"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] r = br.readLine().split(" ");
            int r1 = Integer.parseInt(r[0]);
            int r2 = Integer.parseInt(r[1]);
            a.get(r1).add(r2);
            a.get(r2).add(r1);
        }

        boolean[] check = new boolean[n];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = recursion(1, i, a, check);
            if (answer == 1) {
                break;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int recursion (int index, int cur, ArrayList<ArrayList<Integer>> a, boolean[] check) {
        if (index == 5) {
            return 1;
        }

        int answer = 0;
        check[cur] = true;
        for (int i = 0; i < a.get(cur).size(); i++) {
            if (check[a.get(cur).get(i)]) {
                continue;
            }

            answer = recursion(index + 1, a.get(cur).get(i), a, check);
            if (answer == 1) {
                return answer;
            }
        }

        check[cur] = false;
        return answer;
    }
}