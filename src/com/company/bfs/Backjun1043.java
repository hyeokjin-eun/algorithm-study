package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/1043
public class Backjun1043 {

    private static final String[] array = {
            "4 3\n" +
            "0\n" +
            "2 1 2\n" +
            "1 3\n" +
            "3 2 3 4",
            "4 1\n" +
            "1 1\n" +
            "4 1 2 3 4",
            "4 1\n" +
            "0\n" +
            "4 1 2 3 4",
            "4 5\n" +
            "1 1\n" +
            "1 1\n" +
            "1 2\n" +
            "1 3\n" +
            "1 4\n" +
            "2 4 1",
            "10 9\n" +
            "4 1 2 3 4\n" +
            "2 1 5\n" +
            "2 2 6\n" +
            "1 7\n" +
            "1 8\n" +
            "2 7 8\n" +
            "1 9\n" +
            "1 10\n" +
            "2 3 10\n" +
            "1 4",
            "8 5\n" +
            "3 1 2 7\n" +
            "2 3 4\n" +
            "1 5\n" +
            "2 5 6\n" +
            "2 6 8\n" +
            "1 8",
            "3 4\n" +
            "1 3\n" +
            "1 1\n" +
            "1 2\n" +
            "2 1 2\n" +
            "3 1 2 3",
            "3 3\n" +
            "1 3\n" +
            "1 1\n" +
            "2 1 2\n" +
            "2 2 3" // 0
    };
    private static IOBuffered ioBuffered;
    private static boolean[] person;
    private static ArrayList<Integer>[] party;
    private static int N;
    private static int M;
    private static int answer;

    public static void main(String[] args) throws IOException {
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
        //TODO Algorithm Start
        ioBuffered = IOBuffered.create(input);
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setData() throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        N = stringToInteger(stringTokenizer.nextToken());
        M = stringToInteger(stringTokenizer.nextToken());
        person = new boolean[N + 1];
        stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        int N2 = stringToInteger(stringTokenizer.nextToken());
        for (int i = 0; i < N2; i++) {
            person[stringToInteger(stringTokenizer.nextToken())] = true;
        }

        party = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(ioBuffered.readLine());
            party[i] = new ArrayList<>();
            int temp = stringToInteger(stringTokenizer.nextToken());
            for (int j = 0; j < temp; j++) {
                party[i].add(stringToInteger(stringTokenizer.nextToken()));
            }
        }
    }

    private static int stringToInteger(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = getPartyCount();
    }

    private static int getPartyCount() {
        Queue<Integer> queue = new LinkedList<>();
        int[] partyCheck = new int[M];
        int[] personCheck = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            if (person[i]) {
                queue.add(i);
                personCheck[i] = 1;
            }
        }

        int answer = M;
        while ( ! queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < M; i++) {
                if (partyCheck[i] == 1) {
                    continue;
                }

                if (!party[i].contains(cur)) {
                    continue;
                }

                for (int j = 0; j < party[i].size(); j++) {
                    int next = party[i].get(j);
                    if (personCheck[next] == 1) {
                        continue;
                    }

                    personCheck[next] = 1;
                    queue.add(next);
                }

                partyCheck[i] = 1;
                answer--;
            }
        }

        return answer;
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

        /**
         * IOBuffered Create
         * @param input Input String
         * @return IOBuffered Instance
         */
        public static IOBuffered create(String input) throws IOException {
            return new IOBuffered(input);
        }

        /**
         * BufferedReader Read Line
         * @return BufferedReader.readLIne
         */
        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        /**
         * Console Print Out (BufferedWriter.write())
         */
        public void print(int answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int answer) throws IOException {
            //TODO Answer Write Implement
            bufferedWriter.write(String.valueOf(answer));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
