package com.company.dp;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/9184
public class Backjun9184 {
    private static final String[] array = {
            "1 1 1\n" +
            "2 2 2\n" +
            "10 4 6\n" +
            "50 50 50\n" +
            "-1 7 18\n" +
            "-1 -1 -1"
    };
    private static IOBuffered ioBuffered;
    private static ArrayList<int[]> numbers;
    private static int[] answer;
    private static HashMap<Pair, Integer> map;

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
        numbers = new ArrayList<>();
        while (true) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            int c = stoi(st.nextToken());
            if (a == -1 && b == -1 && c == -1) {
                break;
            }

            numbers.add(new int[]{a, b, c});
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        map = new HashMap<>();
        answer = new int[numbers.size()];
        for (int i = 0; i < answer.length; i++) {
            int[] nums = numbers.get(i);
            answer[i] = recursion(nums[0], nums[1], nums[2]);
        }
    }

    private static int recursion(int a, int b, int c) {
        if (map.containsKey(Pair.of(a, b, c))) {
            return map.get(Pair.of(a, b, c));
        }

        int result;
        if (a <= 0 || b <= 0 || c <= 0) {
            result = 1;
        } else if (a > 20 || b > 20 || c > 20) {
            result = recursion(20, 20, 20);
        } else if (a < b && b < c) {
            result = recursion(a, b, c - 1) +
                    recursion(a, b - 1, c- 1) -
                    recursion(a, b - 1, c);
        } else {
            result = recursion(a - 1, b, c) +
                    recursion(a - 1, b - 1, c) +
                    recursion(a - 1, b, c - 1) -
                    recursion(a - 1, b - 1, c - 1);
        }

        map.put(Pair.of(a, b, c), result);
        return result;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(numbers, answer);
    }

    private static class Pair {
        private final int a;
        private final int b;
        private final int c;

        private Pair(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public static Pair of(int a, int b, int c) {
            return new Pair(a, b, c);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return a == pair.a && b == pair.b && c == pair.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }
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

        public void print(ArrayList<int[]> in, int[] answer) throws IOException {
            write(in, answer);
            flush();
        }

        private void write(ArrayList<int[]> in, int[] answer) throws IOException {
            for (int i = 0 ; i < answer.length; i++) {
                int[] num = in.get(i);
                bufferedWriter.write(
                        "w(" + num[0] + ", " + num[1] + ", " + num[2] + ") = " + answer[i]
                );
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
