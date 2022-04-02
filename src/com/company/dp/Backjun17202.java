package com.company.dp;

import java.io.*;

// link
// https://www.acmicpc.net/problem/17202
public class Backjun17202 {
    private static final String[] array = {
            "74759336\n" +
            "36195974",
            "01234567\n" +
            "12345678"
    };
    private static IOBuffered ioBuffered;
    private static int[] numbers;
    private static int[] answer;

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
        setInputData();
        setAnswer();
        printAnswer();
    }

    private static void setInputData() throws IOException {
        char[] numberChar1 = ioBuffered.readLine().toCharArray();
        char[] numberChar2 = ioBuffered.readLine().toCharArray();
        setNumbers(numberChar1, numberChar2);
    }

    private static void setNumbers(char[] numberChar1, char[] numberChar2) {
        numbers = new int[numberChar1.length + numberChar2.length];
        for (int i = 0; i < 8; i++) {
            numbers[i * 2] = numberChar1[i] - '0';
            numbers[i * 2 + 1] = numberChar2[i] - '0';
        }
    }

    private static void setAnswer() {
        int[] temp = numbers;
        while (2 < temp.length) {
            temp = getArray(temp);
        }

        answer = temp;
    }

    private static int[] getArray(int[] temp) {
        int[] temp2 = new int[temp.length - 1];
        for (int i = 0; i < temp.length - 1; i++) {
            temp2[i] = (temp[i] + temp[i + 1]) % 10;
        }

        return temp2;
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

        public void print(int[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int[] answer) throws IOException {
            for (int n : answer) {
                bufferedWriter.write(String.valueOf(n));
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
