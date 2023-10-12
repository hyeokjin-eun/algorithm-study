package com.company.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/20529
public class Backjun20529 {
    private static final String[] array = {
            "3\n" +
            "3\n" +
            "ENTJ INTP ESFJ\n" +
            "4\n" +
            "ESFP ESFP ESFP ESFP\n" +
            "5\n" +
            "INFP INFP ESTP ESTJ ISTJ"
    };

    private static IOBuffered ioBuffered;
    private static int T;
    private static int[] N;
    private static List[] MBTI;
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
        T = stoi(ioBuffered.readLine());
        N = new int[T];
        MBTI = new List[T];
        for (int i = 0; i < T; i++) {
            N[i] = stoi(ioBuffered.readLine());
            MBTI[i] = new ArrayList<String>();
            StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
            for (int j = 0; j < N[i]; j++) {
                MBTI[i].add(stringTokenizer.nextToken());
            }
        }

        answer = new int[T];
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        for (int i = 0; i < T; i++) {
            answer[i] = 33 <= N[i] ? 0 : solve(N[i], MBTI[i]);
        }
    }

    private static int solve(int n, List<String> mbtis) {
        int min = Integer.MAX_VALUE;
        int[] temp = new int[n];
        for (int i = 0; i < 3; i++) {
            temp[i] = 1;
        }

        Arrays.sort(temp);
        do {
            List<String> input = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (temp[i] == 1) input.add(mbtis.get(i));
            }

            min = Math.min(min, distances(input));
        } while(next(temp));

        return min;
    }

    private static boolean next(int[] temp) {
        int i = temp.length - 1;
        while (i > 0 && temp[i - 1] >= temp[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        int j = temp.length - 1;
        while (temp[j] <= temp[i - 1]) {
            j--;
        }

        int nums = temp[i - 1];
        temp[i - 1] = temp[j];
        temp[j] = nums;

        j = temp.length - 1;
        while (i < j) {
            nums = temp[i];
            temp[i] = temp[j];
            temp[j] = nums;
            i++;
            j--;
        }

        return true;
    }

    private static int distances(List<String> mbtis) {
        return distance(mbtis.get(0), mbtis.get(1)) + distance(mbtis.get(1), mbtis.get(2)) + distance(mbtis.get(0), mbtis.get(2));
    }

    private static int distance(String a, String b) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            char at = a.charAt(i);
            char bt = b.charAt(i);
            if (at != bt) {
                count++;
            }
        }

        return count;
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
