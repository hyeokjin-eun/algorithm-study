package com.company.greedy;

import java.io.*;
import java.util.Arrays;

// link
// https://www.acmicpc.net/problem/1448
public class Backjun1448 {

    private static final String[] array = {
            "3\n" +
            "1\n" +
            "2\n" +
            "3",
            "3\n" +
            "1\n" +
            "2\n" +
            "2",
            "6\n" +
            "2\n" +
            "3\n" +
            "2\n" +
            "3\n" +
            "2\n" +
            "4",
            "5\n" +
            "4\n" +
            "5\n" +
            "6\n" +
            "7\n" +
            "20"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int[] number;
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
        N = Util.stoi(ioBuffered.readLine());
        number = new int[N];
        for (int i = 0; i < N; i++) {
            number[i] = Util.stoi(ioBuffered.readLine());
        }
    }

    private static void setAnswer() {
        Util.sort(number);
        answer = -1;
        for (int i = N - 1; 2 <= i; i--) {
            int a = number[i - 2];
            int b = number[i - 1];
            int c = number[i];
            if (c < a + b) {
                answer = a + b+ c;
                break;
            }
        }
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }
}

class Util {
    public static int stoi(String s) {
        return Integer.parseInt(s);
    }

    public static void sort(int[] array) {
        Arrays.sort(array);
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
