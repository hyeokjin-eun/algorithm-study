package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1059
public class Backjun1059 {
    private static int length;
    private static int[] array;
    private static int number;
    private static final String[] input = {
            "4\n" +
            "1 7 14 10\n" +
            "2",
            "5\n" +
            "4 8 13 24 30\n" +
            "10",
            "5\n" +
            "10 20 30 40 50\n" +
            "30",
            "8\n" +
            "3 7 12 18 25 100 33 1000\n" +
            "59"
    };

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < input.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            long before = System.currentTimeMillis();
            solution(input[i]);
            long after = System.currentTimeMillis();
            System.out.println();
            System.out.println("===== Time : " + (after - before) + "   =====");
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static void solution(String input) throws IOException {
        IOBuffered ioBuffered = IOBuffered.create(input);
        setInputData(ioBuffered);
        int count = bf();
        ioBuffered.print(count);
    }

    private static void setInputData(IOBuffered ioBuffered) throws IOException {
        length = stoi(ioBuffered.readLine());
        array = new int[length];
        setArrayData(ioBuffered.readLine());
        number = stoi(ioBuffered.readLine());
    }

    private static void setArrayData(String input) {
        StringTokenizer st = new StringTokenizer(input);
        for (int i = 0; i < length; i++) {
            array[i] = stoi(st.nextToken());
        }
    }

    private static int bf() {
        int prev = 0;
        int min = 0;
        int max = 0;
        Arrays.sort(array);
        for (int i = 0; i < length; i++) {
            if (number == array[i]) {
                return 0;
            }

            if (array[i] > number) {
                min = prev + 1;
                max = array[i] - 1;
                break;
            }

            prev = array[i];
        }

        return (number - min) * (max - number + 1) + (max - number);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static class IOBuffered {
        private static BufferedReader bufferedReader;
        private static BufferedWriter bufferedWriter;

        private IOBuffered(String input) {
            bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public static IOBuffered create(String input) {
            return new IOBuffered(input);
        }

        private String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print(int count) throws IOException {
            write(count);
            flush();
        }

        private void write(int count) throws IOException {
            bufferedWriter.write(String.valueOf(count));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}