package com.company.java.divideandconquer;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1992
public class Backjun1992 {
    private static IOBuffered ioBuffered;
    private static int N;
    private static int[][] video;
    private static String answer;
    private static final String[] array = {
            "8\n" +
            "11110000\n" +
            "11110000\n" +
            "00011100\n" +
            "00011100\n" +
            "11110000\n" +
            "11110000\n" +
            "11110011\n" +
            "11110011",
            "TEST MADE",
            "1\n" +
            "1"
    };

    public static void main(String[] args) throws IOException {
        // TEST
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
        sb.append("64");
        sb.append("\n");
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                sb.append((i + j) % 2 == 0 ? "0" : "1");
            }

            if (i != 63) {
                sb.append("\n");
            }
        }

        array[1] = sb.toString();
    }

    private static void solution(String input) throws IOException {
        ioBuffered = IOBuffered.create(input);
        setInputData();
        setAnswer();
        printOutputData();
    }

    private static void setInputData() throws IOException {
        N = stoi(ioBuffered.readLine());
        setVideo();
    }

    private static void setVideo() throws IOException {
        video = new int[N][N];
        for (int i = 0; i < N; i++) {
            char[] chars = ioBuffered.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                video[i][j] = chars[j] - '0';
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = Video.compression(video);
    }

    private static void printOutputData() throws IOException {
        ioBuffered.print(answer);
    }

    private static class Video {
        private final int[] xsa = new int[]{0, 2, 0, 2};
        private final int[] xea = new int[]{2, 1, 2, 1};
        private final int[] ysa = new int[]{0, 0, 2, 2};
        private final int[] yea = new int[]{2, 2, 1, 1};
        private Video() {
        }

        public static String compression(int[][] input) {
            return new Video().recursion(input).toString();
        }

        private StringBuilder recursion(int[][] temp) {
            StringBuilder sb = new StringBuilder();
            int length = temp.length;
            if (isOne(temp, length)) {
                sb.append(temp[0][0]);
                return sb;
            }

            sb.append("(");
            for (int i = 0; i < 4; i++) {
                int xs = division(length, xsa[i]);
                int xe = division(length, xea[i]);
                int ys = division(length, ysa[i]);
                int ye = division(length, yea[i]);
                sb.append(recursion(createArray(xs, xe, ys, ye, temp)));
            }

            sb.append(")");
            return sb;
        }

        private boolean isOne(int[][] temp, int length) {
            return length == 1 || check(temp);
        }

        private int division(int target, int div) {
            return div == 0 ? 0 : target / div;
        }

        private boolean check(int[][] temp) {
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp.length; j++) {
                    if (temp[0][0] != temp[i][j]) {
                        return false;
                    }
                }
            }

            return true;
        }

        private int[][] createArray(int xs, int xe, int ys, int ye, int[][] array) {
            int[][] temp = new int[ye - ys][xe - xs];
            for (int i = ys; i < ye; i++) {
                for (int j = xs; j < xe; j++) {
                    temp[i - ys][j - xs] = array[i][j];
                }
            }

            return temp;
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

        public void print(String answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(String answer) throws IOException {
            bufferedWriter.write(answer);
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}