package com.company.greedy;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1026
public class Backjun1026 {

    private static final String[] array = {
            "5\n" +
            "1 1 1 6 0\n" +
            "2 7 8 3 1",
            "3\n" +
            "1 1 3\n" +
            "10 30 20",
            "9\n" +
            "5 15 100 31 39 0 0 3 26\n" +
            "11 12 13 2 3 4 5 9 1"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int[] A;
    private static int[] B;
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
        A = new int[N];
        B = new int[N];
        StringTokenizer a = new StringTokenizer(ioBuffered.readLine());
        StringTokenizer b = new StringTokenizer(ioBuffered.readLine());
        for (int i = 0; i < N; i++) {
            int na = stoi(a.nextToken());
            int nb = stoi(b.nextToken());
            A[i] = na;
            B[i] = nb;
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        PriorityQueue<Integer> aQueue = new PriorityQueue<>(Comparator.naturalOrder());
        PriorityQueue<Integer> bQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            aQueue.add(A[i]);
            bQueue.add(B[i]);
        }

        answer = 0;
        for (int i = 0; i < N; i++) {
            if (!aQueue.isEmpty() && !bQueue.isEmpty()) {
                answer += aQueue.poll() * bQueue.poll();
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
