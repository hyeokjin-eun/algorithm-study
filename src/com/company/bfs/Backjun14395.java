package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14395
public class Backjun14395 {
    private static final String[] array = {
            "7 392",
            "7 256",
            "4 256",
            "7 7",
            "7 9",
            "10 1"
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
        long s = Long.parseLong(st.nextToken());
        long t = Long.parseLong(st.nextToken());
        String answer = bfs(s, t);
        bw.write(answer);
        bw.flush();
    }

    private static String bfs(long s, long t) {
        Queue<Pair> q = new LinkedList<>();
        HashSet<Long> set = new HashSet<>();
        q.add(new Pair(s, ""));
        set.add(s);
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            long n = pair.n;
            String c = pair.c;
            if (n == t) {
                if (c.equals("")) {
                    return String.valueOf(0);
                } else {
                    return c;
                }
            }

            if (!set.contains(n * n)) {
                set.add(n * n);
                q.add(new Pair(n * n, c + "*"));
            }

            if (!set.contains(n + n)) {
                set.add(n + n);
                q.add(new Pair(n + n, c + "+"));
            }

            if (!set.contains(n - n)) {
                set.add(n - n);
                q.add(new Pair(n - n, c + "-"));
            }

            if (n != 0 && !set.contains(n / n)) {
                set.add(n / n);
                q.add(new Pair(n / n, c + "/"));
            }
        }

        return String.valueOf(-1);
    }

    private static class Pair {
        long n;
        String c;

        public Pair(long n, String c) {
            this.n = n;
            this.c = c;
        }
    }
}