package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1325
public class Backjun1325 {
    private static final String[] array = {
            "5 4\n" +
            "3 1\n" +
            "3 2\n" +
            "4 3\n" +
            "5 3"
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
        for (int i = 0; i < N; i++) {
            a.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            a.get(from).add(to);
            a.get(to).add(from);
        }

        int answer = 0;
        int index = 0;
        for (int i = 0; i < N; i++) {
            boolean[] check = new boolean[N];
            int temp = dfs(i, 0, a, check);
            if (answer < temp) {
                answer = temp;
                index = i + 1;
            }
        }

        bw.write(String.valueOf(index));
        bw.write(" ");
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int dfs(int c, int index, ArrayList<ArrayList<Integer>> a, boolean[] check) {
        check[c] = true;
        int answer = index;
        for (int i = 0; i < a.get(c).size(); i++) {
            if (!check[a.get(c).get(i)]) {
                answer = Math.max(dfs(a.get(c).get(i), index + 1, a, check), answer);
            }
        }

        return answer;
    }
}