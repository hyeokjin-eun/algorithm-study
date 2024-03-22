package com.company.math;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1072
public class Backjun1072 {

    private static final String[] array = {
            "10 8",
            "100 80",
            "47 47",
            "99000 0",
            "1000000000 470000000"
    };

    private static IOBuffered ioBuffered;
    private static int X;
    private static int Y;
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
        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        X = stoi(stringTokenizer.nextToken());
        Y = stoi(stringTokenizer.nextToken());
        answer = -1;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        long mid;
        long low = 1;
        long high = 1000000000;
        while (low <= high) {
            mid = (low + high) / 2;
            if (getPercent(X, Y) != getPercent(X + mid, Y + mid)) {
                answer = mid;
                high = mid - 1;
            } else if (getPercent(X, Y) == getPercent(X + mid, Y + mid)) {
                low = mid + 1;
            }
        }
    }

    private static long getPercent(long x, long y) {
        return 100 * y / x;
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
