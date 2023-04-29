package com.company.binarysearch;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/3273
public class Backjun3273 {

    private static final String[] array = {
            "9\n" +
            "5 12 7 10 9 1 2 3 11\n" +
            "13"
    };

    private static IOBuffered ioBuffered;
    private static int n;
    private static int x;
    private static int[] numbers;
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
        n = Util.stoi(ioBuffered.readLine());
        numbers = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Util.stoi(stringTokenizer.nextToken());
        }

        Arrays.sort(numbers);
        x = Util.stoi(ioBuffered.readLine());
    }

    private static void setAnswer() {
        int count = 0;
        int start = 0;
        int end = n - 1;
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == x) {
                count++;
            }

            if (sum <= x) {
                start++;
            } else {
                end--;
            }
        }

        answer = count;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }
}

class Util {
    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}

class IOBuffered {
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
