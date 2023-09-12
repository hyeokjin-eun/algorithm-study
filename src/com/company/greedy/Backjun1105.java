package com.company.greedy;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1105
public class Backjun1105 {

    private static final String[] array = {
            "1 10",
            "88 88",
            "800 899",
            "8808 8880"
    };

    private static IOBuffered ioBuffered;
    private static long L;
    private static long R;
    private static int answer;

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
        L = stol(stringTokenizer.nextToken());
        R = stol(stringTokenizer.nextToken());
    }

    private static long stol(String s) {
        return Long.parseLong(s);
    }

    private static String ltos(long i) {
        return String.valueOf(i);
    }

    private static void setAnswer() {
        String number1 = ltos(L);
        String number2 = ltos(R);
        int count = 0;

        if (number1.length() == number2.length()) {
            for (int i = 0; i < number1.length(); i++) {
                if (number1.charAt(i) != number2.charAt(i)) {
                    break;
                }

                if (number2.charAt(i) == '8') {
                    count++;
                }
            }
        }

        answer = count;
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

        public void print(int answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int answer) throws IOException {
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}