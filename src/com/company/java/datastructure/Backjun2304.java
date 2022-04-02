package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2304
public class Backjun2304 {
    private static final String[] array = {
            "7\n" +
            "2 4\n" +
            "11 4\n" +
            "15 8\n" +
            "4 6\n" +
            "5 3\n" +
            "8 10\n" +
            "13 6",
            "4\n" +
            "2 5\n" +
            "3 6\n" +
            "6 6\n" +
            "7 3",
            "6\n" +
            "2 2\n" +
            "5 4\n" +
            "6 6\n" +
            "7 4\n" +
            "8 6\n" +
            "9 2\n"
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
        int n = Integer.parseInt(br.readLine());
        List<Pair> a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            a.add(new Pair(l, h));
        }

        Collections.sort(a);

        int max = 0;
        Stack<Pair> left = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (max < a.get(i).h) {
                max = a.get(i).h;
                left.push(a.get(i));
            }
        }

        max = 0;
        Stack<Pair> right = new Stack<>();
        for (int i = n - 1; 0 <= i; i--) {
            if (max < a.get(i).h) {
                max = a.get(i).h;
                right.push(a.get(i));
            }
        }

        int answer = (right.peek().l - left.peek().l + 1) * left.peek().h;
        Pair pre = null;
        if (!left.isEmpty()) {
            pre = left.pop();
        }

        while (!left.isEmpty()) {
            Pair p = left.pop();
            answer += (pre.l - p.l) * p.h;
            pre = p;
        }

        pre = null;
        if (!right.isEmpty()) {
            pre = right.pop();
        }

        while (!right.isEmpty()) {
            Pair p = right.pop();
            answer += (p.l - pre.l) * p.h;
            pre = p;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static class Pair implements Comparable<Pair>{
        int l;
        int h;
        public Pair(int l, int h) {
            this.l = l;
            this.h = h;
        }

        @Override
        public int compareTo(Pair o) {
            return this.l < o.l ? -1 : 1;
        }
    }
}
