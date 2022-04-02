package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16953
public class Backjun16953 {
    private static final String[] array = {
            "2 162",
            "4 42",
            "100 40021"
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
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long answer = bfs(A, B);
        bw.write(String.valueOf(answer == -1 ? -1 : answer + 1));
        bw.flush();
    }

    private static long bfs(long A, long B) {
        Queue<Long> queue = new LinkedList<>();
        queue.offer(A);
        queue.offer(0L);
        while (!queue.isEmpty()) {
            long num = queue.poll();
            long cnt = queue.poll();
            if (num == B) {
                return cnt;
            }

            long temp1 = num * 2;
            long temp2 = num * 10 + 1;
            if (temp1 <= B) {
                queue.offer(num * 2);
                queue.offer(cnt + 1);
            }

            if (temp2 <= B) {
                queue.offer(num * 10 + 1);
                queue.offer(cnt + 1);
            }
        }

        return -1;
    }
}
