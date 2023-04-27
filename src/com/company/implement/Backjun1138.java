package com.company.implement;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1138
public class Backjun1138 {

    private static final String[] array = {
            "4\n" +
            "2 1 1 0",
            "5\n" +
            "0 0 0 0 0",
            "6\n" +
            "5 4 3 2 1 0",
            "7\n" +
            "6 1 1 1 2 0 0"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int[] position;
    private static int[] answer;


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
        position = new int[N];
        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        for (int i = 0; i < N; i++) {
            position[i] = Util.stoi(stringTokenizer.nextToken());
        }
    }

    private static void setAnswer() {
        answer = new int[N];
        int[] temp = new int[N];
        for (int i = 0; i < N; i++) {
            temp[i] = i + 1;
        }

        do {
            if (check(temp)) {
                break;
            }

        } while (Util.nextPermutation(temp));
        answer = temp;
    }

    private static boolean check(int[] temp) {
        int[] check = new int[N];
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (i + 1 == temp[j]) {
                    break;
                }

                if (i + 1 < temp[j]) {
                    count++;
                }
            }

            check[i] = count;
        }

        for (int i = 0; i < N; i++) {
            if (check[i] != position[i]) {
                return false;
            }
        }

        return true;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }
}

class Util {
    public static int stoi(String s) {
        return Integer.parseInt(s);
    }

    public static boolean nextPermutation(int[] a) {
        int i = a.length - 1;
        while (i > 0 && a[i - 1] >= a[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        int j = a.length - 1;
        while (a[j] <= a[i - 1]) {
            j--;
        }

        int temp = a[i - 1];
        a[i - 1] = a[j];
        a[j] = temp;

        j = a.length - 1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }

        return true;
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

    public void print(int[] answer) throws IOException {
        write(answer);
        flush();
    }

    private void write(int[] answer) throws IOException {
        for (int i = 0; i < answer.length; i++) {
            bufferedWriter.write(String.valueOf(answer[i]));
            if (i != answer.length - 1) {
                bufferedWriter.write(" ");
            }
        }
    }

    private void flush() throws IOException {
        bufferedWriter.flush();
    }
}
