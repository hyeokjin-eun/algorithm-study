package com.company.math;

import java.io.*;
import java.math.BigInteger;

// link
// https://www.acmicpc.net/problem/11444
public class Backjun11444 {

    private static final String[] array = {
            "1000",
            "1000000000000000000"
    };

    private static IOBuffered ioBuffered;
    private static long N;
    private static long[][] A = new long[][]{{1, 1}, {1, 0}};
    private static final long MOD = 1000000007;
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
        N = Util.stol(ioBuffered.readLine());
    }

    private static void setAnswer() {
        long[][] ret = recursion(N - 1);
        answer = ret[0][0];
    }

    private static long[][] recursion(long x) {
        if (x == 1L || x == 0) {
            return A;
        }

        long[][] ret = recursion(x / 2);
        ret = Array.multiply(ret, ret);
        if (x % 2 == 1) {
            ret = Array.multiply(ret, A);
        }

        return ret;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static class Array {
        public static long[][] multiply(long[][] a, long[][] b) {
            long[][] ret = new long[2][2];
            ret[0][0] = ((a[0][0] * b[0][0]) + (a[0][1] * b[1][0])) % MOD;
            ret[0][1] = ((a[0][0] * b[0][1]) + (a[0][1] * b[1][1])) % MOD;
            ret[1][0] = ((a[1][0] * b[0][0]) + (a[1][1] * b[1][0])) % MOD;
            ret[1][1] = ((a[1][0] * b[0][1]) + (a[1][1] * b[1][1])) % MOD;
            return ret;
        }
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
