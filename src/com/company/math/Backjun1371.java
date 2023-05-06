package com.company.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/13171
public class Backjun1371 {

    private static final String[] array = {
            "3\n" +
            "3",
            "100\n" +
            "100"
    };

    private static IOBuffered ioBuffered;
    private static final long M = 1000000007;
    private static long A;
    private static long X;
    private static long answer;

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
        A = Util.stol(ioBuffered.readLine()) % M;
        X = Util.stol(ioBuffered.readLine());
    }

    private static void setAnswer() {
        answer = recursion(X);
    }

    private static long recursion(long x) {
        if (x == 1) {
            return A;
        }

        long div = x / 2;
        long a = recursion(div);
        long t = ((a % M) * (a % M)) % M;
        if (x % 2 != 0) {
            t = ((t % M) * (A % M)) % M;
        }

        return t;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static class Util {
        public static long stol(String s) {
            return Long.parseLong(s);
        }
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

        public void print(long answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(long answer) throws IOException {
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
