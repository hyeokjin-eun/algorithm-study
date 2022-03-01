package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11404
public class Backjun11404 {
    private static final int INF = 100001;
    private static IOBuffered ioBuffered;
    private static int N;
    private static int M;
    private static int[][] city;
    private static final String[] array = {
            "5\n" +
            "14\n" +
            "1 2 2\n" +
            "1 3 3\n" +
            "1 4 1\n" +
            "1 5 10\n" +
            "2 4 2\n" +
            "3 4 1\n" +
            "3 5 1\n" +
            "4 5 3\n" +
            "3 5 10\n" +
            "3 1 8\n" +
            "1 4 2\n" +
            "5 1 7\n" +
            "3 4 2\n" +
            "5 2 4"
    };

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
        setInputData();
        setAnswer();
        printOutputData();
    }

    private static void setInputData() throws IOException {
        N = stoi(ioBuffered.readLine());
        M = stoi(ioBuffered.readLine());
        setCity();
    }

    private static void setCity() throws IOException {
        city = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                city[i][j] = i == j ? 0 : INF;
            }
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            int start = stoi(st.nextToken()) - 1;
            int end = stoi(st.nextToken()) - 1;
            int price = stoi(st.nextToken());
            city[start][end] = Math.min(price, city[start][end]);
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        floydWarshall();
    }

    private static void floydWarshall() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (city[i][j] > city[i][k] + city[k][j]) {
                        city[i][j] = city[i][k] + city[k][j];
                    }
                }
            }
        }
    }

    private static void printOutputData() throws IOException {
        ioBuffered.print(city);
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

        public void print(int[][] city) throws IOException {
            write(city);
            flush();
        }

        private void write(int[][] city) throws IOException {
            for (int i = 0; i < city.length; i++) {
                for (int j = 0; j < city.length; j++) {
                    bufferedWriter.write(String.valueOf(city[i][j]));
                    if (j != city.length - 1) {
                        bufferedWriter.write(" ");
                    }
                }

                if (i != city.length - 1) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
