package com.company.sort;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/25305
public class Backjun25305 {

    private static final String[] array = {
            "5 2\n" +
            "100 76 85 93 98"
    };
    private static IOBuffered ioBuffered;
    private static int N;
    private static int k;
    private static int[] scores;
    private static int answer;

    public static void main(String[] args) throws IOException {
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
        //TODO Algorithm Start
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setData() throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        N = stoi(stringTokenizer.nextToken());
        k = stoi(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        scores = new int[N];
        for (int i = 0; i < N; i++) {
            scores[i] = stoi(stringTokenizer.nextToken());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        Arrays.sort(scores);
        answer = scores[N - k];
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

        /**
         * IOBuffered Create
         * @param input Input String
         * @return IOBuffered Instance
         */
        public static IOBuffered create(String input) throws IOException {
            return new IOBuffered(input);
        }

        /**
         * BufferedReader Read Line
         * @return BufferedReader.readLIne
         */
        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        /**
         * Console Print Out (BufferedWriter.write())
         */
        public void print(int answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int answer) throws IOException {
            //TODO Answer Write Implement
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
