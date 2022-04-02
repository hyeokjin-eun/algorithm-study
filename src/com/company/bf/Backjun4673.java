package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/4673
public class Backjun4673 {
    private static final String[] array = {
            ""
    };
    private static IOBuffered ioBuffered;
    private static boolean[] check;

    public static void main(String[] args) throws IOException {
        // TEST
        test();
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

    private static void test() {
        setData();
        boolean[] testAnswer = new boolean[101];
        Arrays.fill(testAnswer, true);
        int[] nums = new int[]{
                1, 3, 5, 7, 9, 20, 31, 42, 53, 64, 75, 86, 97
        };

        for (int i = 0 ; i < nums.length; i++) {
            testAnswer[nums[i]] = false;
        }

        for (int i = 1; i < nums.length; i++) {
            assert testAnswer[i] == check[i];
        }
    }

    private static void solution(String input) throws IOException {
        ioBuffered = IOBuffered.create(input);
        setData();
        printAnswer();
    }

    private static void setData() {
        check = new boolean[10001];
        for (int i = 1; i < check.length; i++) {
            notSelfNumberCheck(i);
        }
    }

    private static void notSelfNumberCheck(int number) {
        int target = number;
        do {
            int sum = notSelfNumber(target);
            if (outOfArrayIndex(sum)) {
                check[sum] = true;
            }

            target = sum;
        } while (outOfArrayIndex(target));
    }

    private static int notSelfNumber(int target) {
        int sum = target;
        char[] chars = convertIntegerToCharArray(target);
        for (int j = 0; j < chars.length; j++) {
            sum += chars[j] - '0';
        }
        return sum;
    }

    private static boolean outOfArrayIndex(int target) {
        return target < check.length;
    }

    private static char[] convertIntegerToCharArray(int i) {
        return String.valueOf(i).toCharArray();
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(check);
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

        public void print(boolean[] check) throws IOException {
            write(check);
            flush();
        }

        private void write(boolean[] check) throws IOException {
            for (int i = 1 ; i < check.length; i++) {
                if (!check[i]) {
                    bufferedWriter.write(String.valueOf(i));
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}