package com.company.implement;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/30802
public class Backjun30802 {

    private static final String[] array = {
            "23\n" +
            "3 1 4 1 5 9\n" +
            "5 7"
    };

    private static IOBuffered ioBuffered;
    private static long N;
    private static long[] size;
    private static long T;
    private static long P;
    private static long[] answer;

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
        StringTokenizer stringTokenizer;
        N = stol(ioBuffered.readLine());
        stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        size = new long[6];
        for (int i = 0 ; i < 6; i++) {
            size[i] = stol(stringTokenizer.nextToken());
        }

        stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        T = stol(stringTokenizer.nextToken());
        P = stol(stringTokenizer.nextToken());
        answer = new long[3];
    }

    private static long stol(String s) {
        return Long.parseLong(s);
    }

    private static void setAnswer() {
        for (int i = 0; i < size.length; i++) {
            answer[0] += size[i] / T;
            if (size[i] % T != 0) {
                answer[0]++;
            }
        }

        answer[1] = N / P;
        answer[2] = N % P;
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

        public void print(long[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(long[] answer) throws IOException {
            bufferedWriter.write(String.valueOf(answer[0]));
            bufferedWriter.write("\n");
            bufferedWriter.write(String.valueOf(answer[1]));
            bufferedWriter.write(" ");
            bufferedWriter.write(String.valueOf(answer[2]));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
