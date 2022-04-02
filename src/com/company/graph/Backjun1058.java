package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1058
public class Backjun1058 {
    private static int N;
    private static ArrayList<ArrayList<Integer>> a;
    private static final String[] array = {
            "3\n" +
            "NYY\n" +
            "YNY\n" +
            "YYN",
            "3\n" +
            "NNN\n" +
            "NNN\n" +
            "NNN",
            "5\n" +
            "NYNNN\n" +
            "YNYNN\n" +
            "NYNYN\n" +
            "NNYNY\n" +
            "NNNYN",
            "10\n" +
            "NNNNYNNNNN\n" +
            "NNNNYNYYNN\n" +
            "NNNYYYNNNN\n" +
            "NNYNNNNNNN\n" +
            "YYYNNNNNNY\n" +
            "NNYNNNNNYN\n" +
            "NYNNNNNYNN\n" +
            "NYNNNNYNNN\n" +
            "NNNNNYNNNN\n" +
            "NNNNYNNNNN",
            "15\n" +
            "NNNNNNNNNNNNNNY\n" +
            "NNNNNNNNNNNNNNN\n" +
            "NNNNNNNYNNNNNNN\n" +
            "NNNNNNNYNNNNNNY\n" +
            "NNNNNNNNNNNNNNY\n" +
            "NNNNNNNNYNNNNNN\n" +
            "NNNNNNNNNNNNNNN\n" +
            "NNYYNNNNNNNNNNN\n" +
            "NNNNNYNNNNNYNNN\n" +
            "NNNNNNNNNNNNNNY\n" +
            "NNNNNNNNNNNNNNN\n" +
            "NNNNNNNNYNNNNNN\n" +
            "NNNNNNNNNNNNNNN\n" +
            "NNNNNNNNNNNNNNN\n" +
            "YNNYYNNNNYNNNNN"
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
        N = stoi(br.readLine());
        a = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (chars[j] == 'N') {
                    continue;
                }

                int from = i;
                int to = j;
                a.get(from).add(to);
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, bfs(i));
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        queue.offer(0);
        boolean[] check = new boolean[N];
        check[start] = true;
        int answer = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int dist = queue.poll();
            for (int i = 0; i < a.get(cur).size(); i++) {
                if (check[a.get(cur).get(i)]) {
                    continue;
                }

                queue.offer(a.get(cur).get(i));
                queue.offer(dist + 1);
                check[a.get(cur).get(i)] = true;
                if (dist + 1 <= 2) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
