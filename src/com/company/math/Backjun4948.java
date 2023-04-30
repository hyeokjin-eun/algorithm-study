package com.company.math;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// link
// https://www.acmicpc.net/problem/4948
public class Backjun4948 {

    private static final String[] array = {
            "1\n" +
            "10\n" +
            "13\n" +
            "100\n" +
            "1000\n" +
            "10000\n" +
            "100000\n" +
            "0"
    };

    private static IOBuffered ioBuffered;
    private static int max = (123456 * 2) + 1;
    private static boolean[] eratosthenes;
    private static List<Integer> temp;
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
        eratosthenes = Util.eratosthenes(max);
        temp = new ArrayList<>();
        while (true) {
            int number = Util.stoi(ioBuffered.readLine());
            if (number == 0) {
                break;
            } else {
                temp.add(number);
            }
        }
    }

    private static void setAnswer() {
        answer = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            answer[i] = getCounts(temp.get(i));
        }
    }

    private static int getCounts(int num) {
        int count = 0;
        for (int i = num + 1; i <= num * 2; i++) {
            if (!eratosthenes[i]) {
                count++;
            }
        }

        return count;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }
}

class Util {
    public static int stoi(String s) {
        return Integer.parseInt(s);
    }

    public static boolean[] eratosthenes(int max) {
        boolean[] eratosthenes = new boolean[max];
        for (int i = 2; i < Math.sqrt(max); i++) {
            for (int j = i * i; j < max; j += i) {
                eratosthenes[j] = true;
            }
        }

        return eratosthenes;
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
                bufferedWriter.write("\n");
            }
        }
    }

    private void flush() throws IOException {
        bufferedWriter.flush();
    }
}
