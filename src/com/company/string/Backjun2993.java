package com.company.string;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2993
public class Backjun2993 {

    private static final String[] array = {
            "dcbagfekjih",
            "mobitel",
            "anakonda"
    };

    private static IOBuffered ioBuffered;
    private static String str;
    private static List<String> strList;
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
        strList = new ArrayList<>();
    }

    private static void setAnswer() {
        int length = str.length();
        for (int i = 1 ; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                String first = new StringBuilder(str.substring(0, i)).reverse().toString();
                String second = new StringBuilder(str.substring(i, j)).reverse().toString();
                String third = new StringBuilder(str.substring(j, length)).reverse().toString();
//                System.out.println(first + " : 0 ~ " + i + " , " + second +" : " + i + " ~ " + j + " , " + third + " : " + j + " ~ " + length);
                strList.add(first + second + third);
            }
        }

        Collections.sort(strList);
        answer = strList.get(0);
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
