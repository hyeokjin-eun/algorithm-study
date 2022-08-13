package com.company.dp;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/2096
// reference
// https://maivve.tistory.com/227
public class Backjun2096 {
    private static final String[] array = {
            "3\n" +
            "1 2 3\n" +
            "4 5 6\n" +
            "4 9 0",
            "3\n" +
            "0 0 0\n" +
            "0 0 0\n" +
            "0 0 0"
    };
    private static IOBuffered ioBuffered;
    private static int N;
    private static int[][] score;
    private static int[] answer;

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
        N = stoi(ioBuffered.readLine());
        score = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
            score[i][0] = stoi(stringTokenizer.nextToken());
            score[i][1] = stoi(stringTokenizer.nextToken());
            score[i][2] = stoi(stringTokenizer.nextToken());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        int[] max = new int[3];
        int[] min = new int[3];
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                max[0] = score[i][0];
                max[1] = score[i][1];
                max[2] = score[i][2];

                min[0] = score[i][0];
                min[1] = score[i][1];
                min[2] = score[i][2];
                continue;
            }

            max = max(i, max);
            min = min(i, min);
        }

        answer = new int[2];
        answer[0] = getMax(max);
        answer[1] = getMin(min);
    }

    private static int[] max(int index, int[] max) {
        int[] temp = new int[3];
        temp[0] = score[index][0] + Math.max(max[0], max[1]);
        temp[1] = score[index][1] + Math.max(Math.max(max[0], max[1]), max[2]);
        temp[2] = score[index][2] + Math.max(max[1], max[2]);
        return temp;
    }

    private static int[] min(int index, int[] min) {
        int[] temp = new int[3];
        temp[0] = score[index][0] + Math.min(min[0], min[1]);
        temp[1] = score[index][1] + Math.min(Math.min(min[0], min[1]), min[2]);
        temp[2] = score[index][2] + Math.min(min[1], min[2]);
        return temp;
    }

    private static int getMax(int[] max) {
        return Math.max(Math.max(max[0], max[1]), max[2]);
    }

    private static int getMin(int[] min) {
        return Math.min(Math.min(min[0], min[1]), min[2]);
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
        public void print(int[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int[] answer) throws IOException {
            //TODO Answer Write Implement
            bufferedWriter.write(String.valueOf(answer[0]));
            bufferedWriter.write(" ");
            bufferedWriter.write(String.valueOf(answer[1]));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
