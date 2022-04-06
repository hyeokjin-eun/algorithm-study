package com.company.datastructure;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/2605
public class Backjun2605 {
    private static final String[] array = {
            "5\n" +
            "0 1 1 3 2"
    };
    private static IOBuffered ioBuffered;
    private static int[] select;
    private static int N;
    private static int[] answer;

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
        select = new int[N];
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        for (int i = 0; i < N; i++) {
            select[i] = stoi(st.nextToken());
        }
    }

    private static void setAnswer() {
        answer = new int[N];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (isFirst(select[i])) {
                deque.addLast(i + 1);
            } else {
                addOrder(deque, i);
            }
        }

        setAnswers(deque);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void addOrder(Deque<Integer> deque, int i) {
        Stack<Integer> stack = new Stack<>();
        for (int j = 0; j < select[i]; j++) {
            stack.push(deque.pollFirst());
        }

        deque.addFirst(i + 1);
        while (!stack.isEmpty()) {
            deque.addFirst(stack.pop());
        }
    }

    private static boolean isFirst(int num) {
        return num == 0;
    }

    private static void setAnswers(Deque<Integer> deque) {
        for (int i = 0; !deque.isEmpty(); i++) {
            answer[i] = deque.pollLast();
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

        public void print(int[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int[] answer) throws IOException {
            for (int i = 0; i < answer.length; i++) {
                bufferedWriter.write(String.valueOf(answer[i]));
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
