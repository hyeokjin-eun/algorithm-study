package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2754
public class Backjun2754 {
    private static final String[] array = {
            "A0"
    };
    private static IOBuffered ioBuffered;
    private static Map<String, Double> map;
    private static String score;
    private static String answer;

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

    private static final String[] key = new String[]{
            "A+", "A0", "A-",
            "B+", "B0", "B-",
            "C+", "C0", "C-",
            "D+", "D0", "D-",
            "F"
    };

    private static final Double[] value = new Double[]{
            4.3, 4.0, 3.7,
            3.3, 3.0, 2.7,
            2.3, 2.0, 1.7,
            1.3, 1.0, 0.7,
            0.0
    };

    private static void setData() throws IOException {
        map = new HashMap<>();
        for (int i = 0; i < key.length; i++) {
            map.put(key[i], value[i]);
        }

        score = ioBuffered.readLine();
    }

    private static void setAnswer() {
        answer = String.format("%.1f", map.get(score));
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