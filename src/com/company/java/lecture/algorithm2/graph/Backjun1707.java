package com.company.java.lecture.algorithm2.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1707
public class Backjun1707 {
    private static final String[] array = {
            "2\n" +
            "3 2\n" +
            "1 3\n" +
            "2 3\n" +
            "4 4\n" +
            "1 2\n" +
            "2 3\n" +
            "3 4\n" +
            "4 2"
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
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            ArrayList<ArrayList<Integer>> a = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            for (int j = 0; j <= v; j++) {
                a.add(new ArrayList<>());
            }

            for (int j = 0; j < e; j++) {
                StringTokenizer st1 = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st1.nextToken());
                int to = Integer.parseInt(st1.nextToken());
                a.get(from).add(to);
                a.get(to).add(from);
            }

            int[] check = new int[v + 1];
            boolean answer = true;
            for (int j = 0; j <= v; j++) {
                if (check[j] == 0) {
                    if (!dfs(1, check, 1, a)) {
                        answer = false;
                    }
                }
            }

            bw.write(answer ? "YES" : "NO");
            bw.write("\n");
        }

        bw.flush();
    }

    private static boolean dfs (int cur, int[] check, int group, ArrayList<ArrayList<Integer>> a) {
        check[cur] = group;
        for (int i = 0; i < a.get(cur).size(); i++) {
            if (check[a.get(cur).get(i)] == 0) {
                if (!dfs(a.get(cur).get(i), check, 3 - group, a)) {
                    return false;
                }
            } else if (check[cur] == check[a.get(cur).get(i)]) {
                return false;
            }
        }

        return true;
    }
}
