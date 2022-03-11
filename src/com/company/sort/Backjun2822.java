package com.company.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2822
public class Backjun2822 {
    private static final String[] array = {
            "20\n" +
            "30\n" +
            "50\n" +
            "48\n" +
            "33\n" +
            "66\n" +
            "0\n" +
            "64",
            "20\n" +
            "0\n" +
            "50\n" +
            "80\n" +
            "77\n" +
            "110\n" +
            "56\n" +
            "48",
            "20\n" +
            "30\n" +
            "50\n" +
            "80\n" +
            "110\n" +
            "11\n" +
            "0\n" +
            "85"
    };
    private static IOBuffered ioBuffered;
    private static int answer;
    private static int[] answers;
    private static Point[] points;

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
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setData() throws IOException {
        points = new Point[8];
        for (int i = 0; i < 8; i++) {
            points[i] = Point.of(i + 1, stoi(ioBuffered.readLine()));
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = 0;
        answers = new int[5];
        Arrays.sort(points);
        for (int i = 0; i < 5; i++) {
            int index = 7 - i;
            answer += points[index].getPoint();
            answers[i] = points[index].getIndex();
        }
        
        Arrays.sort(answers);
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer, answers);
    }

    private static class Point implements Comparable<Point> {
        private final int index;
        private final int point;

        private Point(int index, int point) {
            this.index = index;
            this.point = point;
        }

        public static Point of(int index, int point) {
            return new Point(index, point);
        }

        public int getIndex() {
            return index;
        }

        public int getPoint() {
            return point;
        }

        @Override
        public int compareTo(Point o) {
            if (this == o) {
                return 0;
            }

            return this.getPoint() - o.getPoint();
        }
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

        public void print(int answer, int[] answers) throws IOException {
            write(answer, answers);
            flush();
        }

        private void write(int answer, int[] answers) throws IOException {
            bufferedWriter.write(String.valueOf(answer));
            bufferedWriter.write("\n");
            for (int i = 0; i < answers.length; i++) {
                bufferedWriter.write(String.valueOf(answers[i]));
                if (i != answers.length - 1) {
                    bufferedWriter.write(" ");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}