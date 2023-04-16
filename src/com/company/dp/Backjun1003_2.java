package com.company.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1003
public class Backjun1003_2 {
    private static final String[] array = {
            "3\n" +
            "0\n" +
            "1\n" +
            "3"
    };
    private static IOBuffered ioBuffered;
    private static int T;
    private static int[] t;
    private static int[][] answer;
    private static int max;

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
        T = stoi(ioBuffered.readLine());
        t = new int[T];
        max = 2;
        for (int i = 0; i < T; i++) {
            t[i] = stoi(ioBuffered.readLine());
            max = Math.max(max, t[i]);
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = new int[T][2];
        int[][] temp = new int[max + 1][2];
        temp[0][0] = 1;
        temp[0][1] = 0;
        temp[1][0] = 0;
        temp[1][1] = 1;
        for (int i = 2; i < max + 1; i++) {
            temp[i][0] = temp[i - 1][0] + temp[i - 2][0];
            temp[i][1] = temp[i - 1][1] + temp[i - 2][1];
        }

        for (int i = 0; i < T; i++) {
            answer[i] = temp[t[i]];
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
        public void print(int[][] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int[][] answer) throws IOException {
            //TODO Answer Write Implement
            for (int i = 0; i < answer.length; i++) {
                bufferedWriter.write(String.valueOf(answer[i][0]));
                bufferedWriter.write(" ");
                bufferedWriter.write(String.valueOf(answer[i][1]));
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
