package com.company.implement;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1205
public class Backjun1205 {

    private static final String[] array = {
            "10 1 10\n" +
            "10 9 8 7 6 5 4 3 3 0",
            "3 90 10\n" +
            "100 90 80"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static long score;
    private static int P;
    private static Long[] rank;
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
        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        N = stoi(stringTokenizer.nextToken());
        score = stol(stringTokenizer.nextToken());
        P = stoi(stringTokenizer.nextToken());
        rank = new Long[N + 1];
        rank[0] = score;
        stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        for (int i = 1; i < N + 1; i++) {
            rank[i] = stol(stringTokenizer.nextToken());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static long stol(String s) {
        return Long.parseLong(s);
    }

    private static void setAnswer() {
        if (rank.length == 1) {
            answer = 1;
        } else {
            Arrays.sort(rank, Collections.reverseOrder());
            Queue<Integer> queue = new LinkedList<>();
            int temp = 0;
            for (int i = 0; i < N + 1; i++) {
                if (queue.isEmpty()) {
                    queue.add(i);
                    continue;
                }

                if (rank[queue.peek()] == rank[i]) {
                    queue.add(i);
                    continue;
                } else {
                    if (rank[queue.peek()] == score) {
                        answer = temp;
                        return;
                    }

                    temp += queue.size();
                    queue = new LinkedList<>();
                    queue.add(i);
                }
            }
        }
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
