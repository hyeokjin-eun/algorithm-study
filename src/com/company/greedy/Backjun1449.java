package com.company.greedy;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1449
public class Backjun1449 {

    private static final String[] array = {
            "4 2\n" +
            "1 2 100 101",
            "4 3\n" +
            "1 2 3 4",
            "3 1\n" +
            "3 2 1"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int L;
    private static boolean[] holes;
    private static int answer;

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
        holes = new boolean[1001];
        stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        for (int i = 0; i < N; i++) {
            holes[stoi(stringTokenizer.nextToken())] = true;
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        int count = 0;
        for (int i = 0; i < 1001; i++) {
            if (holes[i]) {
                i += L - 1;
                count++;
            }
        }

        answer = count;
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

        public void print(int answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int answer) throws IOException {
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
