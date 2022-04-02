package com.company.java.programmers;

import java.io.*;
import java.util.*;

// link
// https://programmers.co.kr/learn/courses/30/lessons/43163
public class program_work_change {
    private static final String[] array = {
            "6\n" +
            "hit\n" +
            "cog\n" +
            "hot dot dog lot log cog",
            "5\n" +
            "hit\n" +
            "cog\n" +
            "hot dot dog lot log"
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
        int n = Integer.parseInt(br.readLine());
        String begin = br.readLine();
        String target = br.readLine();
        String[] words = new String[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            words[i] = st.nextToken();
        }

        boolean[] check = new boolean[n];
        int answer = bfs(begin, target, words, check);
        System.out.println(answer);
    }

    private static int bfs(String begin, String target, String[] words, boolean[] check) {
        Queue<String> queue = new LinkedList<>();
        Queue<Integer> cnt = new LinkedList<>();
        queue.offer(begin);
        cnt.offer(0);
        while (!queue.isEmpty() && !cnt.isEmpty()) {
            String cs = queue.poll();
            int count = cnt.poll();
            if (target.equals(cs)) {
                return count;
            }

            for (int i = 0; i < words.length; i++) {
                int diff = 0;
                for (int j = 0; j < cs.length(); j++) {
                    if (cs.charAt(j) != words[i].charAt(j)) {
                        diff++;
                    }
                }

                if (!check[i] && diff == 1) {
                    check[i] = true;
                    queue.offer(words[i]);
                    cnt.offer(count + 1);
                }
            }
        }

        return 0;
    }
}
