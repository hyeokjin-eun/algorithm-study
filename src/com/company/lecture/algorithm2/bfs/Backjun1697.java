package com.company.lecture.algorithm2.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1697
public class Backjun1697 {
    private static final String[] array = {
            "5 17"
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
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] location = new int[k * 2];
        boolean[] check = new boolean[k * 2];
        int answer = bfs(n, k, location, check);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int bfs(int n, int k, int[] location, boolean[] check) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        check[n] = true;
        location[n] = 0;
        while (!q.isEmpty()) {
            int x = q.poll();
            if (x == k) {
                break;
            }

            int[] temp = new int[]{x - 1, x + 1, x * 2};
            for (int i = 0; i < 3; i++) {
                if (temp[i] <= k * 2 && !check[temp[i]]) {
                    q.add(temp[i]);
                    location[temp[i]] = location[x] + 1;
                    check[temp[i]] = true;
                }

            }
        }

        return location[k];
    }
}
