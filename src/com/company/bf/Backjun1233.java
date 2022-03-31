package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1233
public class Backjun1233 {
    private static final String[] array = {
            "3 2 3",
            "20 20 40"
    };
    private static IOBuffered ioBuffered;
    private static int[] nums;
    private static int[] temp;
    private static Set<Pair> set;
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
        nums = new int[3];
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        for (int i = 0; i < 3; i++) {
            nums[i] = stoi(st.nextToken());
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        temp = new int[nums[0] + nums[1] + nums[2] + 1];
        set = new HashSet<>();
        recursion(1, 1, 1);
        answer = getAnswer();
    }

    private static void recursion(int s1, int s2, int s3) {
        if (isBreak(s1, s2, s3) || isDuplicated(s1, s2, s3)) {
            return;
        }

        int sum = s1 + s2 + s3;
        set.add(Pair.of(s1, s2, s3));
        temp[sum]++;
        recursion(s1 + 1, s2, s3);
        recursion(s1, s2 + 1, s3);
        recursion(s1, s2, s3 + 1);
    }

    private static boolean isBreak(int s1, int s2, int s3) {
        return nums[0] < s1 || nums[1] < s2 || nums[2] < s3;
    }

    private static boolean isDuplicated(int s1, int s2, int s3) {
        return set.contains(Pair.of(s1, s2, s3));
    }

    private static int getAnswer() {
        int max = 0;
        for (int i : temp) {
            max = Math.max(max, i);
        }

        for (int i = 0; i < temp.length; i++) {
            if (max == temp[i]) {
                return i;
            }
        }

        return 0;
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static class Pair {
        private final int s1;
        private final int s2;
        private final int s3;

        private Pair(int s1, int s2, int s3) {
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
        }

        public static Pair of(int s1, int s2, int s3) {
            return new Pair(s1, s2, s3);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return s1 == pair.s1 && s2 == pair.s2 && s3 == pair.s3;
        }

        @Override
        public int hashCode() {
            return Objects.hash(s1, s2, s3);
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
}
