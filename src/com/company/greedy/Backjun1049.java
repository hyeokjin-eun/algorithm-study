package com.company.greedy;

import java.io.*;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/1049
public class Backjun1049 {

    private static final String[] array = {
            "4 2\n" +
            "12 3\n" +
            "15 4",
            "10 3\n" +
            "20 8\n" +
            "40 7\n" +
            "60 4",
            "15 1\n" +
            "100 40",
            "17 1\n" +
            "12 3",
            "7 2\n" +
            "10 3\n" +
            "12 2",
            "9 16\n" +
            "21 25\n" +
            "77 23\n" +
            "23 88\n" +
            "95 43\n" +
            "96 19\n" +
            "59 36\n" +
            "80 13\n" +
            "51 24\n" +
            "15 8\n" +
            "25 61\n" +
            "21 22\n" +
            "3 9\n" +
            "68 68\n" +
            "67 100\n" +
            "83 98\n" +
            "96 57"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int M;
    private static int minPackagePrice;
    private static int minSinglePrice;
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
        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        N = stoi(stringTokenizer.nextToken());
        M = stoi(stringTokenizer.nextToken());
        minPackagePrice = Integer.MAX_VALUE;
        minSinglePrice = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(ioBuffered.readLine());
            int packagePrice = stoi(stringTokenizer.nextToken());
            int singlePrice = stoi(stringTokenizer.nextToken());
            if (packagePrice < minPackagePrice) {
                minPackagePrice = packagePrice;
            }

            if (singlePrice < minSinglePrice) {
                minSinglePrice = singlePrice;
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        int mixPackageAndSingle = mixPackageAndSingle();
        int onlyPackage = onlyPackage();
        int onlySingle = onlySingle();
        answer = Math.min(mixPackageAndSingle, Math.min(onlyPackage, onlySingle));
    }

    private static int onlyPackage() {
        if (N % 6 == 0) {
            return ((N / 6) * minPackagePrice);
        } else {
            return ((N / 6) * minPackagePrice) + minPackagePrice;
        }
    }

    private static int mixPackageAndSingle() {
        return ((N / 6) * minPackagePrice) + ((N % 6) * minSinglePrice);
    }

    private static int onlySingle() {
        return N * minSinglePrice;
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
