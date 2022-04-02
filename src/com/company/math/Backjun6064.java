package com.company.math;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/6064
public class Backjun6064 {
    private static IOBuffered ioBuffered;
    private static int K;
    private static int[][] input;
    private static int[] answer;
    private static final String[] array = {
            "5\n" +
            "10 12 3 9\n" +
            "10 12 7 2\n" +
            "13 11 5 6\n" +
            "39999 40000 39999 40000\n" +
            "39999 40000 39999 40000"
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

    }

    private static void solution(String input) throws IOException {
       ioBuffered = IOBuffered.create(input);
       setInputData();
       setAnswer();
       printOutData();
    }

    private static void setInputData() throws IOException {
        K = stoi(ioBuffered.readLine());
        input = new int[K][4];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            input[i][0] = stoi(st.nextToken());
            input[i][1] = stoi(st.nextToken());
            input[i][2] = stoi(st.nextToken());
            input[i][3] = stoi(st.nextToken());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = new int[K];
        for (int i = 0; i < K; i++) {
            answer[i] = getYear(i);
        }
    }

    private static int getYear(int index) {
        int m = input[index][0];
        int n = input[index][1];
        int x = input[index][2];
        int y = input[index][3];
        int lcm = m * n / gcd(m, n);
        int t = 0;
        while (t * m < lcm) {
            if ((t * m + x - y) % n == 0) {
                return t * m + x;
            }

            t++;
        }

        return -1;
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    private static void printOutData() throws IOException {
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
            for (int i = 0; i < answer.length; i++) {
                bufferedWriter.write(String.valueOf(answer[i]));
                if (i != answer.length - 1) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}