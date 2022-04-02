package com.company.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1788
public class Backjun1788 {
    private static final String[] array = {
            "-2",
            "0",
            "10",
            "-7"
    };
    private static IOBuffered ioBuffered;
    private static Map<Integer, Long> map;
    private static int N;
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
        N = stoi(ioBuffered.readLine());
        int div = 1000000000;
        map = new HashMap<>();
        map.put(0, 0L);
        map.put(1, 1L);
        for (int i = 2; i <= 1000000; i++) {
            map.put(i, ((map.get(i - 1) + map.get(i - 2)) % div));
        }

        for (int i = 1; -1000000 <= i; i--) {
            map.put(i - 2, ((map.get(i) - map.get(i - 1)) % div));
        }
    }

    private static void setAnswer() {
        answer = new long[2];
        long temp = map.get(N);
        if (temp < 0) {
            answer[0] = -1;
        } else if (0 < temp) {
            answer[0] = 1;
        }

        answer[1] = Math.abs(temp);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
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

        public void print(long[] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(long[] answer) throws IOException {
            for (int i = 0; i < answer.length; i++) {
                bufferedWriter.write(String.valueOf(answer[i]));
                if (i != answer.length - 1) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
