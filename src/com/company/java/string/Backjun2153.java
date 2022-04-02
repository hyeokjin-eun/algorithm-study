package com.company.java.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/2153
public class Backjun2153 {
    private static final String[] array = {
            "UFRN",
            "contest"
    };
    private static IOBuffered ioBuffered;
    private static boolean[] prime;
    private static int num;
    private static String answer;

    public static void main (String[] args) throws IOException {
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
        String testString = "cyworld";
        int testNum = convertNum(testString);
        assert testNum == 100;
    }

    private static void solution(String input) throws IOException {
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setData() throws IOException {
        String S = ioBuffered.readLine();
        num = convertNum(S);
        setPrime();
    }

    private static int convertNum(String s) {
        int temp = 0;
        for (int i = 0; i < s.length(); i++) {
            if ('a' <= s.charAt(i) && s.charAt(i) <= 'z') {
                temp += s.charAt(i) - 'a' + 1;
            } else {
                temp += s.charAt(i) - 'A' + 27;
            }
        }

        return temp;
    }

    private static void setPrime() {
        prime = new boolean[1041];
        for (int i = 2; i < prime.length; i++) {
            for (int j = i + i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }

    private static void setAnswer() {
        answer = prime[num] ? "It is not a prime word." : "It is a prime word.";
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
