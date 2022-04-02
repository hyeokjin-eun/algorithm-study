package com.company.java.programmers;

import java.io.*;
import java.util.*;

// link
// https://programmers.co.kr/learn/courses/30/lessons/43165
public class program_target {
    private static boolean[] check;
    private static HashSet<String> a;
    private static final String[] array = {
            "5\n" +
            "1 1 1 1 1\n" +
            "3"
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
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int target = Integer.parseInt(br.readLine());
        check = new boolean[numbers.length];
        int temp = bfs(check, numbers, target);
        System.out.println(temp);
    }

    private static int bfs(boolean[] check, int[] numbers, int target) {
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> targets = new LinkedList<>();
        q.add(0);
        q.add(0);
        targets.add(+numbers[0]);
        targets.add(-numbers[0]);
        int cnt = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            int t = targets.poll();
            if (t == target && x == numbers.length - 1) {
                cnt++;
            }

            if (x + 1 < numbers.length) {
                check[x + 1] = true;
                q.add(x + 1);
                q.add(x + 1);
                targets.add(t + numbers[x + 1]);
                targets.add(t - numbers[x + 1]);
            }
        }

        return cnt;
    }
}
