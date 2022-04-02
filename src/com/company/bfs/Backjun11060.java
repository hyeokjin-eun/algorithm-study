package com.company.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/11060
public class Backjun11060 {
    private static final String[] array = {
            "10\n" +
            "1 2 0 1 3 2 1 5 4 2"
    };
    private static IOBuffered ioBuffered;
    private static int N;
    private static int[] nums;
    private static int answer;

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
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setData() throws IOException {
        N = stoi(ioBuffered.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = stoi(st.nextToken());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = bfs();
    }

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int[] dist = new int[N];
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (index == N - 1) {
                return dist[index];
            }

            for (int i = 1; i <= nums[index]; i++) {
                int next = index + i;
                if (N <= next || dist[next] != 0) {
                    continue;
                }

                queue.offer(index + i);
                dist[next] = dist[index] + 1;
            }
        }

        return -1;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static class IOBuffered {
        private final BufferedReader bufferedReader;
        private final BufferedWriter bufferedWriter;

        private IOBuffered(String input) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
            InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }

        public static IOBuffered create(String input) throws IOException {
            return new IOBuffered(input);
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print(int answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int answer) throws IOException {
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
