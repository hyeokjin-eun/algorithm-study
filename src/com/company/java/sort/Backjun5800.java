package com.company.java.sort;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/5800
public class Backjun5800 {
    private static final String[] array = {
            "2\n" +
            "5 30 25 76 23 78\n" +
            "6 25 50 70 99 70 90"
    };
    private static IOBuffered ioBuffered;
    private static int K;
    private static int[] classRooms;
    private static int[][] answer;

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
        int T = stoi(ioBuffered.readLine());
        answer = new int[T][3];
        for (int t = 0; t < T; t++) {
            setData();
            answer[t] = setAnswer();
        }

        printAnswer();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setData() throws IOException {
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        K = stoi(st.nextToken());
        classRooms = new int[K];
        for (int k = 0; k < K; k++) {
            classRooms[k] = stoi(st.nextToken());
        }
    }

    private static int[] setAnswer() {
        int[] temp = classRooms;
        Arrays.sort(temp);
        int max = 0;
        for (int i = 0; i < temp.length - 1; i++) {
            max = Math.max(max, Math.abs(temp[i] - temp[i + 1]));
        }

        return new int[]{temp[K - 1], temp[0], max};
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

        public void print(int[][] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int[][] answer) throws IOException {
            for (int i = 0; i < answer.length; i++) {
                bufferedWriter.write("Class " + (i + 1));
                bufferedWriter.write("\n");
                bufferedWriter.write("Max " + answer[i][0]);
                bufferedWriter.write(", ");
                bufferedWriter.write("Min " + answer[i][1]);
                bufferedWriter.write(", ");
                bufferedWriter.write("Largest gap " + answer[i][2]);
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
