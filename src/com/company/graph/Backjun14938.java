package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/14938
public class Backjun14938 {

    private static final String[] array = {
            "5 5 4\n" +
            "5 7 8 2 3\n" +
            "1 4 5\n" +
            "5 2 4\n" +
            "3 2 3\n" +
            "1 2 3"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int M;
    private static int R;
    private static int[] item;
    private static int[][] edges;
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
        N = Util.stoi(stringTokenizer.nextToken());
        M = Util.stoi(stringTokenizer.nextToken());
        R = Util.stoi(stringTokenizer.nextToken());
        item = new int[N];
        edges = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                edges[i][j] = 16;
            }
        }

        stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        for (int i = 0; i < N; i++) {
            item[i] = Util.stoi(stringTokenizer.nextToken());
        }

        for (int i = 0; i < R; i++) {
            stringTokenizer = new StringTokenizer(ioBuffered.readLine());
            int a = Util.stoi(stringTokenizer.nextToken()) - 1;
            int b = Util.stoi(stringTokenizer.nextToken()) - 1;
            int l = Util.stoi(stringTokenizer.nextToken());
            edges[a][b] = l;
            edges[b][a] = l;
        }
    }

    private static void setAnswer() {
        floydWarshall();
        answer = 0;
        for (int i = 0; i < N; i++) {
            int sum = item[i];
            for (int j = 0; j < N; j++) {
                if (edges[i][j] <= M) {
                    sum += item[j];
                }
            }

            answer = Math.max(answer, sum);
        }
    }

    private static void floydWarshall() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (k == i || k == j || i == j) {
                        continue;
                    }

                    edges[i][j] = Math.min(edges[i][j], edges[i][k] + edges[k][j]);
                }
            }
        }
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }
}

class Util {
    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}

class IOBuffered {
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
