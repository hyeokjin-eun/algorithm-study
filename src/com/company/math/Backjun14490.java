package com.company.math;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14490
public class Backjun14490 {
    private static IOBuffered ioBuffered;
    private static int N;
    private static int M;
    private static int[] answer;
    private static final String[] array = {
            "100:10",
            "18:24",
            "10:10"
    };

    public static void main (String[] args) throws IOException {
        // TEST
        test();
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

    private static void test() {
        int gcd;
        gcd = gcd(18, 24);
        assert gcd == 6;

        gcd = gcd(100, 10);
        assert gcd == 10;

        gcd = gcd(3, 4);
        assert gcd == 1;

        gcd = gcd(50, 10);
        assert gcd == 10;

        gcd = gcd(50, 5);
        assert gcd == 5;

        gcd = gcd(24, 18);
        assert gcd == 6;

        gcd = gcd(10, 50);
        assert gcd == 10;
    }

    private static void solution(String input) throws IOException {
        ioBuffered = IOBuffered.create(input);
        setInputData();
        setAnswerData();
        printOutputData(answer);
    }

    private static void setInputData() throws IOException {
        String[] str = ioBuffered.readLine().split(":");
        N = stoi(str[0]);
        M = stoi(str[1]);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswerData() {
        int gcd = gcd(N, M);
        answer = new int[2];
        answer[0] = N / gcd;
        answer[1] = M / gcd;
    }

    private static int gcd(int n, int m) {
        return m == 0 ? n : gcd(m, n % m);
    }

    private static void printOutputData(int[] answer) throws IOException {
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

        public void print(int[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int[] answer) throws IOException {
            bufferedWriter.write(String.valueOf(answer[0]));
            bufferedWriter.write(":");
            bufferedWriter.write(String.valueOf(answer[1]));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}