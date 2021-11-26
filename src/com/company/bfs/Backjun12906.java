package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/12906
public class Backjun12906 {
    private static final String[] array = {
            "1 A\n" +
            "2 AA\n" +
            "2 AA"
    };

    public static void main (String[] args) throws IOException {
        // TEST
        test();
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
        int in = 3;
        ArrayList<String> array = new ArrayList<>();
        while (in-- != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = stoi(st.nextToken());
            if (num == 0) {
                array.add("");
            } else {
                array.add(st.nextToken());
            }
        }

        int answer = bfs(array);
        System.out.println(answer);
    }

    private static int bfs(ArrayList<String> array) {
        HashSet<Pair> hashSet = new HashSet<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(sb(array.get(0)), sb(array.get(1)), sb(array.get(2))));
        Queue<Integer> dist = new LinkedList<>();
        dist.offer(0);
        while (!queue.isEmpty() && !queue.isEmpty()) {
            Pair pair = queue.poll();
            StringBuilder a = pair.a;
            StringBuilder b = pair.b;
            StringBuilder c = pair.c;
            int d = dist.poll();
            if (check(pair)) {
                return d;
            }

            if (a.toString().length() != 0) {
                StringBuilder at = new StringBuilder(a);
                StringBuilder bt = new StringBuilder(b);
                StringBuilder ct = new StringBuilder(c);
                char temp = at.charAt(at.toString().length() - 1);
                at.deleteCharAt(at.toString().length() - 1);
                bt.append(temp);
                queue.offer(new Pair(at, bt, ct));
                dist.offer(d + 1);
                bt.deleteCharAt(bt.toString().length() - 1);
                ct.append(temp);
                queue.offer(new Pair(at, bt, ct));
                dist.offer(d + 1);
            }

            if (b.toString().length() != 0) {
                StringBuilder at = new StringBuilder(a);
                StringBuilder bt = new StringBuilder(b);
                StringBuilder ct = new StringBuilder(c);
                char temp = bt.charAt(bt.toString().length() - 1);
                bt.deleteCharAt(bt.toString().length() - 1);
                at.append(temp);
                queue.offer(new Pair(at, bt, ct));
                dist.offer(d + 1);
                at.deleteCharAt(at.toString().length() - 1);
                ct.append(temp);
                queue.offer(new Pair(at, bt, ct));
                dist.offer(d + 1);
            }

            if (c.toString().length() != 0) {
                StringBuilder at = new StringBuilder(a);
                StringBuilder bt = new StringBuilder(b);
                StringBuilder ct = new StringBuilder(c);
                char temp = ct.charAt(ct.toString().length() - 1);
                ct.deleteCharAt(ct.toString().length() - 1);
                at.append(temp);
                queue.offer(new Pair(at, bt, ct));
                dist.offer(d + 1);
                at.deleteCharAt(at.toString().length() - 1);
                bt.append(temp);
                queue.offer(new Pair(at, bt, ct));
                dist.offer(d + 1);
            }
        }

        return -1;
    }

    private static class Pair {
        StringBuilder a;
        StringBuilder b;
        StringBuilder c;
        public Pair(StringBuilder a, StringBuilder b, StringBuilder c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            return this.a.equals(obj.toString()) && this.b.equals(obj.toString()) && this.c.equals(obj.toString());
        }
    }

    private static StringBuilder sb(String s) {
        return new StringBuilder(s);
    }

    private static boolean check(Pair pair) {
        String a = pair.a.toString();
        if (a.contains("B")) return false;
        if (a.contains("C")) return false;

        String b = pair.b.toString();
        if (b.contains("A")) return false;
        if (b.contains("C")) return false;

        String c = pair.c.toString();
        if (c.contains("A")) return false;
        if (c.contains("B")) return false;

        return true;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {

    }
}
