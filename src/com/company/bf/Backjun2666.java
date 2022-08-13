package com.company.bf;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/2666
public class Backjun2666 {
    private static final String[] array = {
        "7\n" +
        "2 5\n" +
        "4\n" +
        "3\n" +
        "1\n" +
        "6\n" +
        "5"
    };
    private static IOBuffered ioBuffered;
    private static int n;
    private static int m;
    private static int[] order;
    private static int door1;
    private static int door2;
    private static int answer;

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
        n = stoi(ioBuffered.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        door1 = stoi(stringTokenizer.nextToken());
        door2 = stoi(stringTokenizer.nextToken());
        m = stoi(ioBuffered.readLine());
        order = new int[m];
        for (int i = 0; i < m; i++) {
            order[i] = stoi(ioBuffered.readLine());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = recursion(0, door1, door2);
    }

    private static int recursion(int count, int door1, int door2) {
        if (count == m) {
            return 0;
        }

        int temp1 = Math.abs(door1 - order[count]);
        int temp2 = Math.abs(door2 - order[count]);
        return Math.min(temp2 + recursion(count + 1, door1, order[count]), temp1 + recursion(count + 1, order[count], door2));
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
        public void print(int answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int answer) throws IOException {
            //TODO Answer Write Implement
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
