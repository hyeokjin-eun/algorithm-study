package com.company.implement;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// link
// https://www.acmicpc.net/problem/1213
public class Backjun1213 {

    private static final String[] array = {
            "AABB",
            "AAABB",
            "ABACABA",
            "ABCD"
    };

    private static IOBuffered ioBuffered;
    private static int[] alpha;
    private static String S;
    private static String answer;
    private static final String NOT_ANSWER = "I'm Sorry Hansoo";

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
        S = ioBuffered.readLine();
        alpha = new int[26];
        for (int i = 0; i < S.length(); i++) {
            int index = S.charAt(i) - 'A';
            alpha[index]++;
        }
    }

    private static Queue<String> start;
    private static String mid;
    private static Stack<String> end;
    private static void setAnswer() {
        if (isAnswer()) {
            answer = NOT_ANSWER;
        } else {
            setStartAndMidAndEnd();
            answer= createAnswer();
        }
    }

    private static boolean isAnswer() {
        int isAnswer = 0;
        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i] % 2 != 0) {
                isAnswer++;
            }
        }

        return 1 < isAnswer;
    }

    private static void setStartAndMidAndEnd() {
        start = new LinkedList<>();
        mid = "";
        end = new Stack<>();
        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i] % 2 != 0) {
                mid = itos(i);
            }

            for (int j = 0; j < alpha[i] / 2; j++) {
                start.add(itos(i));
                end.add(itos(i));
            }
        }
    }

    private static String createAnswer() {
        StringBuilder stringBuilder = new StringBuilder();
        while (!start.isEmpty()) {
            stringBuilder.append(start.poll());
        }

        stringBuilder.append(mid);
        while (!end.isEmpty()) {
            stringBuilder.append(end.pop());
        }

        return stringBuilder.toString();
    }

    private static String itos(int num) {
        return String.valueOf((char) (num + 'A'));
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

        public void print(String answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(String answer) throws IOException {
            bufferedWriter.write(answer);
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
