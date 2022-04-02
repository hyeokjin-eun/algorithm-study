package com.company.java.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/11660
public class Backjun11660 {
    private static IOBuffered io;
    private static int N;
    private static int M;
    private static int[][] table;
    private static int[][] temp;
    private static Point[] points;
    private static int[] answer;
    private static final String[] array = {
            "4 4\n" +
            "1 2 3 4\n" +
            "2 3 4 5\n" +
            "3 4 5 6\n" +
            "4 5 6 7\n" +
            "2 2 3 4\n" +
            "3 4 3 4\n" +
            "1 1 4 4\n" +
            "1 1 1 4",
            "2 4\n" +
            "1 2\n" +
            "3 4\n" +
            "1 1 1 1\n" +
            "1 2 1 2\n" +
            "2 1 2 1\n" +
            "2 2 2 2",
            "TEST MAX MADE"
    };

    public static void main (String[] args) throws IOException {
        // Test
        test();
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

    private static void test() {
        StringBuilder sb = new StringBuilder();
        int N = 1024;
        int M = 100000;
        sb.append(N + " " + M);
        sb.append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append("1");
                if (j != N - 1) {
                    sb.append(" ");
                }
            }

            if (i != N - 1) {
                sb.append("\n");
            }
        }

        for (int i = 0; i < M; i++) {
            sb.append("\n");
            sb.append("1 1 1024 1024");
        }

        array[2] = sb.toString();
    }

    private static void solution(String input) throws IOException {
        io = new IOBuffered(input);
        setInputData();
        setAnswerArray();
        printOutputDate();
    }

    private static void setInputData() throws IOException {
        setVariable();
        createArray();
        setArrayDate();
        setTemp();
        setPoints();
    }

    private static void setVariable() throws IOException {
        StringTokenizer st = new StringTokenizer(io.readLine());
        N = parseStringToInt(st.nextToken());
        M = parseStringToInt(st.nextToken());
    }

    private static int parseStringToInt(String s) {
        return Integer.parseInt(s);
    }

    private static void createArray() {
        table = new int[N][N];
        temp = new int[N][N];
        points = new Point[M];
    }

    private static void setArrayDate() throws IOException {
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(io.readLine());
            for (int j = 0; j < N; j++) {
                table[i][j] = parseStringToInt(st.nextToken());
            }
        }
    }

    private static void setTemp() {
        copyArray();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isNext(j)) {
                    temp[i][j + 1] += temp[i][j];
                }
            }
        }
    }

    private static boolean isNext(int cur) {
        return cur + 1 < N;
    }

    private static void copyArray() {
        for (int i = 0; i < N; i++) {
            temp[i] = Arrays.copyOf(table[i], N);
        }
    }

    private static void setPoints() throws IOException {
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(io.readLine());
            int x1 = parseStringToInt(st.nextToken()) - 1;
            int y1 = parseStringToInt(st.nextToken()) - 1;
            int x2 = parseStringToInt(st.nextToken()) - 1;
            int y2 = parseStringToInt(st.nextToken()) - 1;
            points[i] = new Point(x1, y1, x2, y2);
        }
    }

    private static void setAnswerArray() {
        answer = new int[M];
        for (int i = 0; i < M; i++) {
            answer[i] = setAnswer(points[i]);
        }
    }

    private static int setAnswer(Point point) {
        int answer = 0;
        for (int i = point.x1; i <= point.x2; i++) {
            answer += calculation(point, i);
        }

        return answer;
    }

    private static int calculation(Point point, int target) {
        int num = temp[target][point.y2];
        if (isArrayOutOfIndex(point.y1)) {
            num -= temp[target][point.y1 - 1];
        }

        return num;
    }

    private static boolean isArrayOutOfIndex(int y) {
        return 0 < y;
    }

    private static void printOutputDate() throws IOException {
        io.print();
    }

    private static class IOBuffered {
        private final BufferedReader bufferedReader;
        private final BufferedWriter bufferedWriter;

        public IOBuffered(String input) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
            InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print() throws IOException {
            write();
            flush();
        }

        private void write() throws IOException {
            for (int i = 0; i < M; i++) {
                bufferedWriter.write(String.valueOf(answer[i]));
                if (i != M - 1) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
    
    private static class Point {
        private final int x1;
        private final int y1;
        private final int x2;
        private final int y2;

        public Point(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}