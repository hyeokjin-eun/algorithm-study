package com.company.java.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14248
public class Backjun14248 {
    private static final String[] array = {
            "5\n" +
            "1 4 2 2 1\n" +
            "3"
    };
    private static IOBuffered ioBuffered;
    private static int[] board;
    private static int S;
    private static int N;
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
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        board = new int[N];
        for (int i = 0; i < N; i++) {
            board[i] = stoi(st.nextToken());
        }

        S = stoi(ioBuffered.readLine());
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = bfs();
    }

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(S - 1);
        int cnt = 1;
        boolean[] check = new boolean[N];
        check[S - 1] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int front = cur + board[cur];
            int back = cur - board[cur];
            if (isNotOutOfIndex(front) && !check[front]) {
                cnt++;
                queue.offer(front);
                check[front] = true;
            }

            if (isNotOutOfIndex(back) && !check[back]) {
                cnt++;
                queue.offer(back);
                check[back] = true;
            }
        }

        return cnt;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static boolean isNotOutOfIndex(int next) {
        return 0 <= next && next < N;
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
