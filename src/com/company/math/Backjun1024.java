package com.company.math;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1024
public class Backjun1024 {

    private static final String[] array = {
            "18 2",
            "18 4",
            "18 5",
            "45 10",
            "1000000000 2"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int L;
    private static List<Integer> answer;

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
        L = stoi(stringTokenizer.nextToken());
        answer = new ArrayList<>();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        for (int i = L; i <= 100; i++) {
            int x = (N - expression(i - 1)) / i;
            if (isValid(x, i)) {
                for (int j = 0; j < i; j++) {
                    answer.add(x + j);
                }

                break;
            }
        }
    }

    private static int expression(int n) {
        return n * (n + 1) / 2;
    }

    private static boolean isValid(int x, int l) {
        return equals(N, sum(x, l)) && isNum(x);
    }

    private static int sum(int x, int l) {
        int sum = 0;
        for (int i = 0; i < l; i++) {
            sum += x + i;
        }

        return sum;
    }

    private static boolean equals(int a, int b) {
        return a == b;
    }

    private static boolean isNum(int x) {
        return x >= 0;
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

        public void print(List<Integer> answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(List<Integer> answer) throws IOException {
            if (answer.isEmpty()) {
                bufferedWriter.write(String.valueOf(-1));
                return;
            }

            for (int i = 0; i < answer.size(); i++) {
                bufferedWriter.write(String.valueOf(answer.get(i)));
                if (i != answer.size() - 1) {
                    bufferedWriter.write(" ");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
