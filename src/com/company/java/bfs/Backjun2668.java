package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2668
public class Backjun2668 {
    private static final String[] array = {
            "7\n" +
            "3\n" +
            "1\n" +
            "1\n" +
            "5\n" +
            "5\n" +
            "4\n" +
            "6",
            "6\n" +
            "2\n" +
            "3\n" +
            "1\n" +
            "5\n" +
            "6\n" +
            "4"
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
        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i =0; i < N; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            int t = Integer.parseInt(br.readLine()) - 1;
            a.get(t).add(i);
        }

        boolean[] check = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!check[i]) {
                dfs(i, i, check, a);
            }
        }

        int answer = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (check[i]) {
                answer++;
                sb.append(i + 1);
                sb.append("\n");
            }
        }

        bw.write(String.valueOf(answer));
        bw.write("\n");
        bw.write(sb.toString());
        bw.flush();
    }

    private static boolean dfs(int s, int c, boolean[] check, ArrayList<ArrayList<Integer>> a) {
        if (check[c] && s == c) {
            return true;
        }

        check[c] = true;
        for (int i = 0; i < a.get(c).size(); i++) {
            boolean temp = dfs(s, a.get(c).get(i), check, a);
            if (temp){
                return true;
            }
        }

        check[c] = false;
        return false;
    }
}
