package com.company.math;

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
    private static long answer = 1;
    private static final String[] input = {
            "10 11 12"
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
        modulus();
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

    private static void modulus() {
        for (int i = 0; i < B; i++) {
            answer = ((answer % C) * (A % C)) % C;
        }
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
