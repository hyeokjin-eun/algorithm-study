package com.company.math;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10830
public class Backjun10830 {
    private static final String[] array = {
            "2 5\n" +
            "1 2\n" +
            "3 4",
            "3 3\n" +
            "1 2 3\n" +
            "4 5 6\n" +
            "7 8 9",
            "5 10\n" +
            "1 0 0 0 1\n" +
            "1 0 0 0 1\n" +
            "1 0 0 0 1\n" +
            "1 0 0 0 1\n" +
            "1 0 0 0 1"
    };

    private static IOBuffered ioBuffered;
    private static StringTokenizer stringTokenizer;
    private static int N;
    private static long B;
    private static long[][] A;
    private static final long MOD = 1000L;
    private static long[][] answer;

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
        stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        N = Util.stoi(stringTokenizer.nextToken());
        B = Util.stol(stringTokenizer.nextToken());
        A = new long[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(ioBuffered.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Util.stol(stringTokenizer.nextToken()) % MOD;
            }
        }
    }

    private static void setAnswer() {
        answer = recursion(B);
    }

    private static long[][] recursion(long b) {
        if (b == 1) {
            return A;
        }

        long[][] ret = recursion(b / 2);
        ret = Array.multiply(ret, ret);
        if (b % 2 == 1) {
            ret = Array.multiply(ret, A);
        }

        return ret;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static class Array {
        public static long[][] multiply(long[][] a, long[][] b) {
            long[][] ret = new long[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        ret[i][j] += a[i][k] * b[k][j];
                        ret[i][j] %= MOD;
                    }
                }
            }

            return ret;
        }
    }

    private static class Util {
        public static int stoi(String s) {
            return Integer.parseInt(s);
        }

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

        public void print(long[][] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(long[][] answer) throws IOException {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    bufferedWriter.write(String.valueOf(answer[i][j]));
                    if (j != N - 1) {
                        bufferedWriter.write(" ");
                    }
                }

                if (i != N - 1) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
