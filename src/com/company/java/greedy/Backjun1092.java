package com.company.java.greedy;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1092
public class Backjun1092 {
    private static IOBuffered io;
    private static int N;
    private static ArrayList<Integer> crane;
    private static int M;
    private static ArrayList<Integer> box;
    private static int time = 0;
    private static final String[] array = {
            "3\n" +
            "6 8 9\n" +
            "5\n" +
            "2 5 2 4 7",
            "2\n" +
            "19 20\n" +
            "7\n" +
            "14 12 16 19 16 1 5",
            "4\n" +
            "23 32 25 28\n" +
            "10\n" +
            "5 27 10 16 24 20 2 32 18 7",
            "10\n" +
            "11 17 5 2 20 7 5 5 20 7\n" +
            "5\n" +
            "18 18 15 15 17",
            "TEST MADE"
    };

    public static void main (String[] args) throws IOException {
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

    private static void test() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("50");
        sb.append("\n");
        for (int i = 0; i < 50; i++) {
            if (i == 0) {
                sb.append("1000000");
            } else {
                sb.append("1");
            }

            if (i != 49) {
                sb.append(" ");
            }
        }

        sb.append("\n");
        sb.append("10000");
        sb.append("\n");
        for (int i = 0; i < 10000; i++) {
            sb.append("1000000");
            if (i != 9999) {
                sb.append(" ");
            }
        }

        array[4] = sb.toString();
    }

    private static void solution(String input) throws IOException {
        io = new IOBuffered(input);
        setInputData();
        getTime();
        printOutputData();
    }

    private static void setInputData() throws IOException {
        N = stoi(io.readLine());
        makeCrane();
        M = stoi(io.readLine());
        makeBox();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void makeCrane() throws IOException {
        crane = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(io.readLine());
        for (int i = 0 ; i < N; i++) {
            crane.add(stoi(st.nextToken()));
        }

        crane.sort(Collections.reverseOrder());
    }

    private static void makeBox() throws IOException {
        box = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(io.readLine());
        for (int i = 0; i < M; i++) {
            box.add(stoi(st.nextToken()));
        }

        box.sort(Collections.reverseOrder());
    }

    private static void getTime() {
        if (crane.get(0) < box.get(0)) {
            time = -1;
        } else {
            time = 0;
            while (!box.isEmpty()) {
                int index = 0;
                for (int i = 0; i < crane.size();) {
                    if (index == box.size()) {
                        break;
                    } else if (crane.get(i) >= box.get(index)) {
                        box.remove(index);
                        i++;
                    } else {
                        index++;
                    }
                }

                time++;
            }
        }
    }

    private static void printOutputData() throws IOException {
        io.print();
    }

    private static class IOBuffered {
        private final BufferedReader bufferedReader;
        private final BufferedWriter bufferedWriter;

        public IOBuffered(String input) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
            InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print() throws IOException {
            write();
            flush();
        }

        private void write() throws IOException {
            bufferedWriter.write(String.valueOf(time));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}