package com.company.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/5525
public class Backjun5525_2 {
    private static final String[] array = {
            "1\n" +
            "13\n" +
            "OOIOIOIOIIOII"
    };
    private static IOBuffered ioBuffered;
    private static int N;
    private static int M;
    private static char[] chars;
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
        N = stoi(ioBuffered.readLine());
        M = stoi(ioBuffered.readLine());
        chars = ioBuffered.readLine().toCharArray();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        int[] dp = new int[M];
        int count = 0;
        for (int i = 1; i < M - 1; i++) {
            if (chars[i] == 'O' && chars[i + 1] == 'I') {
                dp[i + 1] = dp[i - 1] + 1;
                if (dp[i + 1] >= N && chars[i - 2 * N + 1] == 'I') {
                    count++;
                }
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
