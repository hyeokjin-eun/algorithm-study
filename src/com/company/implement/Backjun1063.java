package com.company.implement;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1063
public class Backjun1063 {

    private static final String[] array = {
            "A1 A2 5\n" +
            "B\n" +
            "L\n" +
            "LB\n" +
            "RB\n" +
            "LT",
            "A1 H8 1\n" +
            "T",
            "A1 A2 1\n" +
            "T",
            "A1 A2 2\n" +
            "T\n" +
            "R",
            "A8 B7 18\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB\n" +
            "RB",
            "C1 B1 3\n" +
            "L\n" +
            "T\n" +
            "LB"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static String[] commands;
    private static int[] king;
    private static int[] rock;
    private static String[] answer;

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
        king = convertXY(stringTokenizer.nextToken());
        rock = convertXY(stringTokenizer.nextToken());
        N = stoi(stringTokenizer.nextToken());
        commands = new String[N];
        for (int i = 0; i < N; i++) {
            commands[i] = ioBuffered.readLine();
        }

        answer = new String[2];
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static int[] convertXY(String s) {
        int x = s.charAt(0) - 'A';
        int y = 7 - (s.charAt(1) - '1');
        return new int[]{x, y};
    }

    private static void setAnswer() {
        for (int i = 0; i < N; i++) {
            int[] command = convertCommand(commands[i]);
            int knx = king[0] + command[0];
            int kny = king[1] + command[1];
            int rnx = rock[0];
            int rny = rock[1];

            if (knx == rnx && kny == rny) {
                rnx = rock[0] + command[0];
                rny = rock[1] + command[1];
            }

            if (outOfIndex(knx, kny) || outOfIndex(rnx, rny)) {
                continue;
            }

            king = new int[]{knx, kny};
            rock = new int[]{rnx, rny};
        }

        answer[0] = convertString(king);
        answer[1] = convertString(rock);
    }

    private static int[] convertCommand(String s) {
        switch (s) {
            case "R":
                return new int[]{1, 0};
            case "L":
                return new int[]{-1, 0};
            case "B":
                return new int[]{0, 1};
            case "T":
                return new int[]{0, -1};
            case "RT":
                return new int[]{1, -1};
            case "LT":
                return new int[]{-1, -1};
            case "RB":
                return new int[]{1, 1};
            case "LB":
                return new int[]{-1, 1};
        }

        throw new IllegalArgumentException("Not Support Command");
    }

    private static boolean outOfIndex(int x, int y) {
        return x < 0 || 8 <= x || y < 0 || 8 <= y;
    }

    private static String convertString(int[] xy) {
        String x = String.valueOf((char) (xy[0] + 'A'));
        String y = String.valueOf((char) (((xy[1] - 7) * -1) + '1'));
        return x + y;
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

        public void print(String[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(String[] answer) throws IOException {
            for (int i = 0; i < answer.length; i++) {
                bufferedWriter.write(answer[i]);
                if (i == 0) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
