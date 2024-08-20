package com.company.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/31403
public class Backjun31403 {

    private static final String[] array = {
            "3\n" +
            "4\n" +
            "5"
    };

    private static IOBuffered ioBuffered;
    private static String A;
    private static String B;
    private static String C;
    private static long[] answer;

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
        A = ioBuffered.readLine();
        B = ioBuffered.readLine();
        C = ioBuffered.readLine();
        answer = new long[2];
    }

    private static void setAnswer() {
        answer[0] = stol(A) + stol(B) - stol(C);
        answer[1] = stol(add(A, B)) - stol(C);
    }

    private static long stol(String str) {
        return Long.parseLong(str);
    }

    private static String add(String a, String b) {
        return a + b;
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

        public void print(long[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(long[] answer) throws IOException {
            bufferedWriter.write(String.valueOf(answer[0]));
            bufferedWriter.write("\n");
            bufferedWriter.write(String.valueOf(answer[1]));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
