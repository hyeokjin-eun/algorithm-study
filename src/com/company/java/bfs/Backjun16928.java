package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16928
public class Backjun16928 {
    private static final String[] array = {
            "3 7\n" +
            "32 62\n" +
            "42 68\n" +
            "12 98\n" +
            "95 13\n" +
            "97 25\n" +
            "93 37\n" +
            "79 27\n" +
            "75 19\n" +
            "49 47\n" +
            "67 17",
            "4 9\n" +
            "8 52\n" +
            "6 80\n" +
            "26 42\n" +
            "2 72\n" +
            "51 19\n" +
            "39 11\n" +
            "37 29\n" +
            "81 3\n" +
            "59 5\n" +
            "79 23\n" +
            "53 7\n" +
            "43 33\n" +
            "77 21",
            "2 1\n" +
            "2 60\n" +
            "30 98\n" +
            "65 25"
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
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            a.get(from).add(to);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            a.get(from).add(to);
        }

        int answer = bfs(a);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(ArrayList<ArrayList<Integer>> a) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(1, 0));
        boolean[] check = new boolean[101];
        check[1] = true;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int n = pair.n;
            int c = pair.c;
            if (n == 100) {
                return c;
            }

            for (int i = 1; i <= 6; i++) {
                if (100 < n + i) {
                    continue;
                }

                if (a.get(n + i).size() == 0) {
                    if (check[n + i]) {
                        continue;
                    }

                    check[n + i] = true;
                    q.add(new Pair(n + i, c + 1));
                } else {
                    for (int j = 0; j < a.get(n + i).size(); j++) {
                        if (check[a.get(n + i).get(j)]) {
                            continue;
                        }

                        check[a.get(n + i).get(j)] = true;
                        q.add(new Pair(a.get(n + i).get(j), c + 1));
                    }
                }
            }
        }

        return -1;
    }

    private static class Pair {
        int n;
        int c;
        public Pair(int n, int c) {
            this.n = n;
            this.c = c;
        }
    }
}