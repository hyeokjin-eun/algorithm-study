package com.company.java.math;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1629
// reference
// https://sskl660.tistory.com/75
public class Backjun1629 {
    private static IOBuffered ioBuffered;
    private static long A;
    private static long B;
    private static long C;
    private static long answer;
    private static final HashMap<Long, Long> memory = new HashMap<>();
    private static final String[] input = {
            "10 11 12",
            "4 1 2"
    };

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < input.length; i++) {
            System.out.println("===== Test Case " + i + " Start =====");
            long before = System.currentTimeMillis();
            solution(input[i]);
            long after = System.currentTimeMillis();
            System.out.println();
            System.out.println("===== Time : " + (after - before) + "   =====");
            System.out.println("===== Test Case " + i + " End   =====");
        }
    }

    private static void solution(String input) throws IOException {
        ioBuffered = new IOBuffered(input);
        setInputData();
        answer = recursion(B);
        printOutputData();
    }

    private static void setInputData() throws IOException {
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        A = stoi(st.nextToken());
        B = stoi(st.nextToken());
        C = stoi(st.nextToken());
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static long recursion(long num) {
        if (num == 1) {
            return (A % C);
        }

        if (isMemory(num)) {
            return memory.get(num);
        }

        long result = getResult(num);
        memory.put(num, result);
        return result;
    }

    private static boolean isMemory(long num) {
        return memory.containsKey(num);
    }

    private static long getResult(long num) {
        long result = modular(num);
        return arrangementRemain(num, result);
    }

    private static long modular(long num) {
        return ((recursion(num / 2) % C) * (recursion(num / 2) % C)) % C;
    }

    private static long arrangementRemain(long num, long result) {
        if (isOddNumber(num)) {
            result = (result * A) % C;
        }

        return result;
    }

    private static boolean isOddNumber(long num) {
        return num % 2 != 0;
    }

    private static void printOutputData() throws IOException {
        ioBuffered.print();
    }

    public static class IOBuffered {
        private final BufferedReader bufferedReader;
        private final BufferedWriter bufferedWriter;

        private IOBuffered(String input) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
            InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }


        public String readLine() throws IOException{
            return bufferedReader.readLine();
        }

        public void print() throws IOException {
            write();
            flush();
        }

        private void write() throws IOException {
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
