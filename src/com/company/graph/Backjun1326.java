package com.company.graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1326
public class Backjun1326 {

    private static final String[] array = {
            "5\n" +
            "1 2 2 1 2\n" +
            "1 5",
            "5\n" +
            "3 2 1 1 1\n" +
            "1 5"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int[] bridge;
    private static int[] answer;
    private static int a;
    private static int b;

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
        bridge = new int[N];
        answer = new int[N];
        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        for (int i = 0; i < N; i++) {
            bridge[i] = stoi(stringTokenizer.nextToken());
            answer[i] = -1;
        }

        stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        a = stoi(stringTokenizer.nextToken());
        b = stoi(stringTokenizer.nextToken());
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        bfs(a - 1);
    }

    private static void bfs(int a) {
        Queue<Integer> queue = new LinkedList<>();
        answer[a] = 0;
        queue.add(a);
        while (!queue.isEmpty()) {
            int c = queue.poll();
            for (int t = 0; t < N; t++) {
                if ((t - c) % bridge[c] == 0 && answer[t] == -1) {
                    answer[t] = answer[c] + 1;
                    queue.add(t);
                }
            }
        }
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer[b - 1]);
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

        public static IOBuffered create(String input) {
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
