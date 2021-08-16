package com.company.lecture.algorithm1.queue;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1158
public class Backjun1158 {
    private static final String[] array = {
            "7 3"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int queueSize = Integer.parseInt(st.nextToken());
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < queueSize; i++) {
            queue.offer(String.valueOf(i + 1));
        }

        bw.write("<");
        int index = Integer.parseInt(st.nextToken());
        int count = 1;
        while (!queue.isEmpty()) {
            if (queue.size() == 1) {
                bw.write(queue.poll());
                break;
            }

            if (count == index) {
                bw.write(queue.poll());
                bw.write(", ");
                count = 1;
                continue;
            }

            queue.offer(queue.poll());
            count++;
        }
        bw.write(">");
        bw.flush();
    }
}
