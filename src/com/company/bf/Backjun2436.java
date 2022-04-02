package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2436
public class Backjun2436 {
    private static IOBuffered ioBuffered;
    private static int A;
    private static int B;
    private static int aa;
    private static int ab;
    private static final String[] array = {
            "6 180",
            "2 86486400"
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
        assert isPrime(2, 3);
        assert !isPrime(2, 4);
        assert isMultiple(4, 2);
        assert isMultiple(3, 333333);
        assert !isMultiple(7, 3);
    }

    private static void solution(String input) throws IOException {
        ioBuffered = IOBuffered.create(input);
        setInputData();
        setAnswerData();
        printOutputData();
    }

    private static void setInputData() throws IOException {
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        A = stoi(st.nextToken());
        B = stoi(st.nextToken());
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswerData() {
        int x = B / A;
        for (int i = 1; i * i <= x; i++) {
            if (isMultiple(x, i) && isPrime(i, x / i)) {
                aa = A * i;
                ab = A * (x / i);
            }
        }
    }

    private static boolean isMultiple(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        return max % min == 0;
    }

    private static boolean isPrime(int a, int b) {
        return gcd(a, b) == 1;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static void printOutputData() throws IOException {
        ioBuffered.print(aa, ab);
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

        public void print(int a, int b) throws IOException {
            write(a, b);
            flush();
        }

        private void write(int a, int b) throws IOException {
            bufferedWriter.write(String.valueOf(a));
            bufferedWriter.write(" ");
            bufferedWriter.write(String.valueOf(b));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}