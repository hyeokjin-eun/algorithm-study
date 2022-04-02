package com.company.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/11403
public class Backjun11403 {
    private static IOBuffered ioBuffered;
    private static int N;
    private static ArrayList<ArrayList<Integer>> pathway;
    private static int[][] answer;
    private static final String[] array = {
            "3\n" +
            "0 1 0\n" +
            "0 0 1\n" +
            "1 0 0",
            "7\n" +
            "0 0 0 1 0 0 0\n" +
            "0 0 0 0 0 0 1\n" +
            "0 0 0 0 0 0 0\n" +
            "0 0 0 0 1 1 0\n" +
            "1 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 1\n" +
            "0 0 1 0 0 0 0"
    };

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

    private static void solution(String input) {
        ioBuffered = IOBuffered.create(input);
        setInputData();
        findPathway();
        printOutputData();
    }

    private static void setInputData() {
        N = stoi(ioBuffered.readLine());
        setArrayData();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setArrayData() {
        createArray();
        setArray();
    }

    private static void createArray() {
        pathway = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            pathway.add(new ArrayList<>());
        }
    }

    private static void setArray() {
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            for (int j = 0; j < N; j++) {
                if (isDirection(st)) {
                    pathway.get(i).add(j);
                }
            }
        }
    }

    private static boolean isDirection(StringTokenizer st) {
        return stoi(st.nextToken()) == 1;
    }

    private static void findPathway() {
        answer = new int[N][N];
        for (int i = 0; i < N; i++) {
            Graph.bfs(i);
        }
    }

    private static void printOutputData() {
        ioBuffered.print();
    }

    private static class Graph {
        private static void bfs(int target) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(target);
            while (!queue.isEmpty()) {
                int current = queue.poll();
                ArrayList<Integer> currentArray = pathway.get(current);
                for (int i = 0; i < currentArray.size(); i++) {
                    int next = currentArray.get(i);
                    if (answer[target][next] == 0) {
                        queue.offer(next);
                        answer[target][next] = 1;
                    }
                }
            }
        }
    }

    private static class IOBuffered {
        private BufferedReader bufferedReader;
        private BufferedWriter bufferedWriter;

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

        public String readLine() {
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                return "";
            }
        }

        public void print() {
            try {
                write();
                flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void write() throws IOException {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    bufferedWriter.write(String.valueOf(answer[i][j]));
                    if (j != N - 1) {
                        bufferedWriter.write(" ");
                    }
                }

                if (i != N - 1) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}