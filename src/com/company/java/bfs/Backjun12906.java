package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/12906
public class Backjun12906 {
    private static ArrayList<String> ans;
    private static final String[] array = {
            "1 A\n" +
            "2 AA\n" +
            "2 AA",
            "1 B\n" +
            "1 C\n" +
            "1 A",
            "3 CBA\n" +
            "0\n" +
            "0"
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

        int[] cnt = new int[3];
        for (String temp : array) {
            for (char c : temp.toCharArray()) {
                cnt[c - 'A'] += 1;
            }
        }

        ans = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String s = "";
            for (int j = 0; j < cnt[i]; j++) {
                s += (char)('A' + i);
            }

            ans.add(s);
        }

        int answer = bfs(array);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(ArrayList<String> array) {
        HashMap<ArrayList<String>, Integer> map = new HashMap<>();
        map.put(array, 0);
        Queue<ArrayList<String>> queue = new LinkedList<>();
        queue.offer(array);
        while (!queue.isEmpty()) {
            ArrayList<String> a = queue.poll();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j) {
                        continue;
                    }

                    if (a.get(i).length() == 0) {
                        continue;
                    }

                    String[] next = {a.get(0), a.get(1), a.get(2)};
                    next[j] += next[i].charAt(next[i].length() - 1);
                    next[i] = next[i].substring(0, next[i].length() - 1);
                    ArrayList<String> key = new ArrayList<>(Arrays.asList(next));
                    if (!map.containsKey(key)) {
                        map.put(key, map.get(a) + 1);
                        queue.offer(key);
                    }
                }
            }
        }

        return map.get(ans);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void test() {

    }
}