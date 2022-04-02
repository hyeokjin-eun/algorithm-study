package com.company.java.lecture.algorithm2.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16947
public class Backjun16947 {
    private static final String[] array = {
            "4\n" +
            "1 3\n" +
            "4 3\n" +
            "4 2\n" +
            "1 2",
            "6\n" +
            "1 2\n" +
            "3 4\n" +
            "6 4\n" +
            "2 3\n" +
            "1 3\n" +
            "3 5",
            "51\n" +
            "1 2\n" +
            "2 3\n" +
            "3 4\n" +
            "4 5\n" +
            "5 6\n" +
            "6 7\n" +
            "7 8\n" +
            "8 9\n" +
            "9 10\n" +
            "10 11\n" +
            "11 12\n" +
            "12 13\n" +
            "13 14\n" +
            "14 15\n" +
            "15 16\n" +
            "16 17\n" +
            "17 18\n" +
            "18 19\n" +
            "19 20\n" +
            "20 21\n" +
            "21 22\n" +
            "22 23\n" +
            "23 24\n" +
            "24 25\n" +
            "25 26\n" +
            "26 27\n" +
            "27 28\n" +
            "28 29\n" +
            "29 30\n" +
            "30 31\n" +
            "31 32\n" +
            "32 33\n" +
            "33 34\n" +
            "34 35\n" +
            "35 36\n" +
            "36 37\n" +
            "37 38\n" +
            "38 39\n" +
            "39 40\n" +
            "40 41\n" +
            "41 42\n" +
            "42 43\n" +
            "43 1\n" +
            "11 44\n" +
            "44 45\n" +
            "45 46\n" +
            "46 47\n" +
            "34 48\n" +
            "48 49\n" +
            "49 50\n" +
            "50 51",
            "38\n" +
            "1 2\n" +
            "2 3\n" +
            "3 4\n" +
            "4 5\n" +
            "5 6\n" +
            "6 1\n" +
            "1 7\n" +
            "7 8\n" +
            "8 9\n" +
            "9 10\n" +
            "10 11\n" +
            "11 12\n" +
            "12 13\n" +
            "13 14\n" +
            "14 15\n" +
            "15 16\n" +
            "16 17\n" +
            "17 18\n" +
            "18 19\n" +
            "19 20\n" +
            "20 21\n" +
            "21 22\n" +
            "22 23\n" +
            "23 24\n" +
            "24 25\n" +
            "25 26\n" +
            "26 27\n" +
            "27 28\n" +
            "28 29\n" +
            "29 30\n" +
            "30 31\n" +
            "31 32\n" +
            "32 33\n" +
            "33 34\n" +
            "34 35\n" +
            "35 36\n" +
            "36 37\n" +
            "37 38",
            "12\n" +
            "1 3\n" +
            "3 4\n" +
            "4 5\n" +
            "5 6\n" +
            "6 7\n" +
            "7 8\n" +
            "8 4\n" +
            "2 3\n" +
            "7 9\n" +
            "9 12\n" +
            "7 10\n" +
            "10 11"
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
        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            a.get(from).add(to);
            a.get(to).add(from);
        }

        int[] check = new int[n];
        dfs(-1, 0, a, check);
        int[] answer = new int[n];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (check[i] == 2) {
                q.add(i);
                answer[i] = 0;
            } else {
                answer[i] = -1;
            }
        }

        bfs(q, a, answer);
        for (int i = 0; i < n; i++) {
            bw.write(String.valueOf(answer[i]));
            if (i != n - 1) {
                bw.write(" ");
            }
        }

        bw.flush();
    }

    private static int dfs (int b, int c, ArrayList<ArrayList<Integer>> a, int[] check) {
        if (check[c] == 1) {
            return c;
        }

        check[c] = 1;
        for (int i = 0; i < a.get(c).size(); i++) {
            if (a.get(c).get(i) == b) {
                continue;
            }

            int temp = dfs(c, a.get(c).get(i), a, check);
            if (temp == -2) {
                return -2;
            }

            if (temp >= 0) {
                check[c] = 2;
                if (c == temp) {
                    return -2;
                } else {
                    return temp;
                }
            }
        }

        return -1;
    }

    private static void bfs (Queue<Integer> q, ArrayList<ArrayList<Integer>> a, int[] answer) {
        while (!q.isEmpty()) {
            int temp = q.poll();
            for (int i = 0; i < a.get(temp).size(); i++) {
                if (answer[a.get(temp).get(i)] == -1) {
                    q.add(a.get(temp).get(i));
                    answer[a.get(temp).get(i)] = answer[temp] + 1;
                }
            }
        }
    }
}
