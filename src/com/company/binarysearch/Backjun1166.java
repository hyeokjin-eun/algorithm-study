package com.company.binarysearch;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1166
public class Backjun1166 {

    private static final String[] array = {
            "10 4 2 10",
            "2 2 2 2",
            "1 12 47 5",
            "77 146 523 229"
    };

    private static IOBuffered ioBuffered;
    private static long N;
    private static long L;
    private static long W;
    private static long H;
    private static double answer;

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
        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        N = stoi(stringTokenizer.nextToken());
        L = stoi(stringTokenizer.nextToken());
        W = stoi(stringTokenizer.nextToken());
        H = stoi(stringTokenizer.nextToken());
    }

    private static long stoi(String s) {
        return Long.parseLong(s);
    }

    private static void setAnswer() {
        double mid;
        double low = 0;
        double high = Math.min(L, Math.min(W, H));
        while (low < high) {
            mid = (low + high) / 2;
            if ((long) (L / mid) * (long) (W / mid) * (long) (H / mid) < N) {
                if (high == mid) {
                    break;
                }

                high = mid;
            } else {
                if (low == mid) {
                    break;
                }

                low = mid;
            }
        }

        answer = low;
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

        public void print(double answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(double answer) throws IOException {
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
