package com.company.math;

import java.io.*;

// TODO : 제출 필요
// link
// https://www.acmicpc.net/problem/28702
public class Backjun28702 {

    private static final String[] array = {
            "Fizz\n" +
            "Buzz\n" +
            "11",
            "980803\n" +
            "980804\n" +
            "FizzBuzz",
            "Buzz\n" +
            "Fizz\n" +
            "99999997"
    };

    private static IOBuffered ioBuffered;
    private static final int max = 100000000;
    private static String[] comparisons;
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

    private static void setData() throws IOException {
        comparisons = new String[3];
        for (int i = 0; i < comparisons.length; i++) {
            comparisons[i] = ioBuffered.readLine();
        }
    }

    private static void setAnswer() {
        for (int i = getFizzBuzzInt(comparisons[0]); i < max - comparisons.length - 1; i += getFizzBuzzInt(comparisons[0])) {
            if (comparisons[0].equals(getFizzBuzz(i)) &&
                comparisons[1].equals(getFizzBuzz(i + 1)) &&
                comparisons[2].equals(getFizzBuzz(i + 2))) {
                answer = getFizzBuzz(i + 3);
                break;
            }
        }
    }

    private static int getFizzBuzzInt(String s) {
        if ("FizzBuzz".equals(s)) {
            return 15;
        } else if ("Fizz".equals(s)) {
            return 3;
        } else if ("Buzz".equals(s)) {
            return 5;
        } else {
            return Integer.parseInt(s);
        }
    }

    private static String getFizzBuzz(int i) {
        if (isFizzBuzz(i)) {
            return "FizzBuzz";
        } else if (isFizz(i)) {
            return "Fizz";
        } else if (isBuzz(i)) {
            return "Buzz";
        } else {
            return String.valueOf(i);
        }
    }

    private static boolean isFizzBuzz(int i) {
        if (i % 3 == 0 && i % 5 == 0) {
            return true;
        }

        return false;
    }

    private static boolean isFizz(int i) {
        if (i % 3 == 0 && i % 5 != 0) {
            return true;
        }

        return false;
    }

    private static boolean isBuzz(int i) {
        if (i % 3 != 0 && i % 5 == 0) {
            return true;
        }

        return false;
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
