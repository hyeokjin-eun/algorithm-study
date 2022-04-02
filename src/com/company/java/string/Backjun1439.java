package com.company.java.string;

import java.io.*;

// link
// https://www.acmicpc.net/problem/1439
public class Backjun1439 {
    private static IOBuffered ioBuffered;
    private static String str;
    private static final String[] array = {
            "0001100",
            "11111",
            "00000001",
            "11001100110011000001",
            "11101101"
    };

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
        setInputData();
        int min = minChange();
        printOutputData(min);
    }

    private static void setInputData() throws IOException {
        str = ioBuffered.readLine();
    }

    private static int minChange() {
        int zero = 0;
        int one = 0;
        if (str.charAt(0) == '0') {
            zero++;
        } else {
            one++;
        }

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i - 1) != str.charAt(i)) {
                if (str.charAt(i) == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
        }

        return Math.min(zero, one);
    }

    private static void printOutputData(int min) throws IOException {
        ioBuffered.print(min);
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

        public void print(int num) throws IOException {
            write(num);
            flush();
        }

        private void write(int num) throws IOException {
            bufferedWriter.write(String.valueOf(num));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}