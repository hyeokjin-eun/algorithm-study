package com.company.sort;

import com.company.dp.Backjun9184;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/5576
public class Backjun5576 {
    private static final String[] array = {
            "23\n" +
            "23\n" +
            "20\n" +
            "15\n" +
            "15\n" +
            "14\n" +
            "13\n" +
            "9\n" +
            "7\n" +
            "6\n" +
            "25\n" +
            "19\n" +
            "17\n" +
            "17\n" +
            "16\n" +
            "13\n" +
            "12\n" +
            "11\n" +
            "9\n" +
            "5",
            "17\n" +
            "25\n" +
            "23\n" +
            "25\n" +
            "79\n" +
            "29\n" +
            "1\n" +
            "61\n" +
            "59\n" +
            "100\n" +
            "44\n" +
            "74\n" +
            "94\n" +
            "57\n" +
            "13\n" +
            "54\n" +
            "82\n" +
            "0\n" +
            "42\n" +
            "45"
    };
    private static IOBuffered ioBuffered;
    private static int[] W;
    private static int[] K;
    private static int[] answer;

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
        W = new int[10];
        for (int i = 0; i < W.length; i++) {
            W[i] = stoi(ioBuffered.readLine());
        }

        K = new int[10];
        for (int i = 0; i < K.length; i++) {
            K[i] = stoi(ioBuffered.readLine());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = new int[2];
        Arrays.sort(W);
        Arrays.sort(K);
        for (int i = 0; i < 3; i++) {
            answer[0] += W[W.length - 1 - i];
            answer[1] += K[K.length - 1 - i];
        }
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

        public void print(int[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int[] answer) throws IOException {
            bufferedWriter.write(String.valueOf(answer[0]));
            bufferedWriter.write(" ");
            bufferedWriter.write(String.valueOf(answer[1]));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
