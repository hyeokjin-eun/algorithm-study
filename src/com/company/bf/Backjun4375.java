package com.company.bf;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

// link
// https://www.acmicpc.net/problem/4375
public class Backjun4375 {
    private static final String[] array = {
            "3\n" +
            "7\n" +
            "9901\n" +
            "9999"
    };
    private static IOBuffered ioBuffered;
    private static List<Integer> inputData;
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
        for (int i = 0; i < inputData.size(); i++) {
            setAnswer(i);
        }

        printAnswer();
    }

    private static void setData() throws IOException {
        inputData = new ArrayList<>();
        while (true) {
            String s = ioBuffered.readLine();
            if (s == null || s.isEmpty()) {
                break;
            }

            inputData.add(stoi(s));
        }

        answer = new int[inputData.size()];
    }

    private static Integer stoi(final String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer(final int target) {
        BigInteger num = BigInteger.valueOf(inputData.get(target));
        BigInteger temp = BigInteger.ONE;
        while (true) {
            if (isSmall(num, temp)) {
                temp = addOne(temp);
            }

            if (isDivided(num, temp)) {
                answer[target] = temp.toString().length();
                break;
            } else {
                temp = addOne(temp);
            }
        }
    }

    private static boolean isSmall(BigInteger target, BigInteger num) {
        return target.compareTo(num) > 0;
    }

    private static boolean isDivided(BigInteger num, BigInteger temp) {
        return temp.remainder(num).equals(BigInteger.ZERO);
    }

    private static BigInteger addOne(BigInteger temp) {
        return temp.multiply(BigInteger.TEN).add(BigInteger.ONE);
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
}
