package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/10546
public class Backjun10546 {
    private static final String[] array = {
            "3\n" +
            "leo\n" +
            "kiki\n" +
            "eden\n" +
            "eden\n" +
            "kiki",
            "5\n" +
            "marina\n" +
            "josipa\n" +
            "nikola\n" +
            "vinko\n" +
            "filipa\n" +
            "josipa\n" +
            "filipa\n" +
            "marina\n" +
            "nikola",
            "4\n" +
            "mislav\n" +
            "stanko\n" +
            "mislav\n" +
            "ana\n" +
            "stanko\n" +
            "ana\n" +
            "mislav"
    };
    private static IOBuffered ioBuffered;
    private static Map<String, Integer> map;

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
        printData();
    }

    private static void setData() throws IOException {
        int N = stoi(ioBuffered.readLine());
        map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String key = ioBuffered.readLine();
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                map.replace(key, map.get(key) + 1);
            }
        }

        for (int i = 0; i < N - 1; i++) {
            String key = ioBuffered.readLine();
            if (map.containsKey(key)) {
                if (map.get(key) == 1) {
                    map.remove(key);
                } else {
                    map.replace(key, map.get(key) - 1);
                }
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void printData() throws IOException {
        ioBuffered.print(map);
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

        public void print(Map<String, Integer> map) throws IOException {
            write(map);
            flush();
        }

        private void write(Map<String, Integer> map) throws IOException {
            for (String key : map.keySet()) {
                bufferedWriter.write(key);
                bufferedWriter.write("\n");
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}