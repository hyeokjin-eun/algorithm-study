package com.company.sort;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1083
public class Backjun1083 {
    private static final String[] array = {
            "7\n" +
            "10 20 30 40 50 60 70\n" +
            "1",
            "5\n" +
            "3 5 1 2 4\n" +
            "2",
            "10\n" +
            "19 20 17 18 15 16 13 14 11 12\n" +
            "5",
            "2\n" +
            "1000000 999999\n" +
            "1000000",
            "10\n" +
            "1 2 3 4 5 6 7 8 9 10\n" +
            "17"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int[] A;
    private static int S;
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
        N = stoi(ioBuffered.readLine());
        A = new int[N];
        answer = new int[N];
        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = stoi(stringTokenizer.nextToken());
        }

        S = stoi(ioBuffered.readLine());
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        if (N * (1 + N) / 2 < S) {
            Arrays.sort(A);
            int[] temp = new int[N];
            for (int i = 0; i < N; i++) {
                temp[i] = A[N - i - 1];
            }

            A = temp;
        } else {
            for (int k = 0; k < N && S > 0; k++) {
                int max = A[k];
                int idx = -1;
                for (int i = k + 1; i < N && i <= k + S; i++) {
                    if (A[i] > max) {
                        max = A[i];
                        idx = i;
                    }
                }

                if (idx == -1) {
                    continue;
                }

                S -= idx - k;
                for (int i = idx; i >= k+1; i--) {
                    int tmp = A[i];
                    A[i] = A[i-1];
                    A[i-1] = tmp;
                }
            }
        }

        answer = A;
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
            for (int i = 0; i < answer.length; i++) {
                bufferedWriter.write(String.valueOf(answer[i]));
                if (i != answer.length - 1) {
                    bufferedWriter.write(" ");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
