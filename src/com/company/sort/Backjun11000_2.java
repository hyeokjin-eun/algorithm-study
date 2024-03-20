package com.company.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11000
public class Backjun11000_2 {
    private static final String[] array = {
            "3\n" +
            "1 3\n" +
            "2 4\n" +
            "3 5"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int[][] lectures;
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
        lectures = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
            lectures[i][0] = stoi(stringTokenizer.nextToken());
            lectures[i][1] = stoi(stringTokenizer.nextToken());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        Arrays.sort(lectures, Comparator.comparingInt(list -> list[0]));
        Queue<Integer> queue = new PriorityQueue<>();
        int count = 0;
        for (int i = 0; i < N; i++) {
            int s = lectures[i][0];
            int t = lectures[i][1];
            while (!queue.isEmpty() && queue.peek() <= s) {
                queue.poll();
            }

            queue.add(t);
            count = Math.max(count, queue.size());
        }

        answer = count;
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
