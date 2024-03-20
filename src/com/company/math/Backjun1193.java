package com.company.math;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1193
public class Backjun1193 {

    private static final String[] array = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "14",
            "10000000"
    };

    private static IOBuffered ioBuffered;
    private static int X;
    private static int index;
    private static int sum;
    private static int[] temp;

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
        X = stoi(ioBuffered.readLine());
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        getIndexAndSum(X);
        temp = resolve(index, sum - index, X);
    }

    private static void getIndexAndSum(int number) {
        int temp = 0;
        int weight = 0;
        while (!(number <= temp)) {
            temp += weight;
            weight++;
        }

        index = weight - 1;
        sum = temp;
    }

    private static boolean isOddNumber(int number) {
        return number % 2 != 0;
    }

    private static int[] resolve(int number, int sum, int target) {
        int[] temp;
        if (isOddNumber(number)) {
            temp = new int[]{number - (target - sum - 1), 1 + (target - sum - 1)};
        } else {
            temp = new int[]{1 + (target - sum - 1), number - (target - sum - 1)};
        }

        return temp;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(temp);
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
            bufferedWriter.write(answer[0] + "/" + answer[1]);
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}

