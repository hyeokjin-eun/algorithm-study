package com.company.string;

import com.company.math.Backjun1864;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1718
public class Backjun1718 {
    private static final String[] array = {
            "nice day\n" +
            "love"
    };
    private static IOBuffered ioBuffered;
    private static String str;
    private static String key;
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
        str = ioBuffered.readLine();
        key = ioBuffered.readLine();
    }

    private static void setAnswer() {
        StringBuilder sb = new StringBuilder();
        char a;
        for (int i = 0; i < str.length(); i++) {
            a = str.charAt(i);
            if (a != ' ') {
                int k = (key.charAt(i % key.length()) - 96);
                sb.append((char) (a - k < 97 ? (a - k + 26) : a - k));
            } else {
                sb.append(' ');
            }
        }

        answer = sb.toString();
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
