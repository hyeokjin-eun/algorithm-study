package com.company.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2346
public class Backjun2346 {
    private static final String[] array = {
            "5\n" +
            "3 2 1 -3 -1"
    };
    private static IOBuffered ioBuffered;
    private static int N;
    private static int[] weight;
    private static Deque<Integer> deque;
    private static int[] answer;

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
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setData() throws IOException {
        N = stoi(ioBuffered.readLine());
        setDeque();
        setWeight();
    }

    private static void setDeque() {
        deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            deque.addLast(i);
        }
    }

    private static void setWeight() throws IOException {
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        weight = new int[N];
        for (int i = 0; i < N; i++) {
            weight[i] = stoi(st.nextToken());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = new int[N];
        int temp = 0;
        int index = 0;
        while (!isDequeEmpty()) {
            for (int i = 1; i < Math.abs(temp); i++) {
                moveDeque(temp);
            }

            if (!setAnswerArrayAndRemoveDeque(temp, index)) {
                break;
            }

            temp = weight[answer[index]];
            index++;
        }
    }

    private static void moveDeque(int temp) {
        if (temp < 0) {
            deque.addFirst(deque.pollLast());
        }

        if (temp > 0) {
            deque.addLast(deque.pollFirst());
        }
    }

    private static boolean isDequeEmpty() {
        return deque.isEmpty();
    }

    private static boolean setAnswerArrayAndRemoveDeque(int temp, int index) {
        if (deque.isEmpty()) {
            return false;
        }

        if (temp < 0) {
            answer[index] = deque.pollLast();
        } else {
            answer[index] = deque.pollFirst();
        }

        return true;
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

        public void print(int[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int[] answer) throws IOException {
            for (int i = 0; i < answer.length; i++) {
                bufferedWriter.write(String.valueOf(answer[i] + 1));
                if (i != answer.length - 1) {
                    bufferedWriter.write(" ");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
