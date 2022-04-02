package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2303
public class Backjun2303 {
    private static IOBuffered ioBuffered;
    private static int N;
    private static int[][] players;
    private static int answer;
    private static final String[] array = {
            "3\n" +
            "7 5 5 4 9\n" +
            "1 1 1 1 1\n" +
            "2 3 3 2 10"
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

    private static void solution(String input) throws IOException {
        ioBuffered = new IOBuffered(input);
        setInputData();
        maxSum();
        printOutputData();
    }

    private static void setInputData() throws IOException {
        N = stoi(ioBuffered.readLine());
        makePlayer();
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void makePlayer() throws IOException {
        players = new int[N][5];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
            for (int j = 0; j < 5; j++) {
                players[i][j] = stoi(st.nextToken());
            }
        }
    }

    private static void maxSum() {
        int max = 0;
        answer = 0;
        for (int i = 0; i < N; i++) {
            int temp = playerMax(i);
            if (max <= temp) {
                answer = i + 1;
                max = temp;
            }
        }
    }

    private static int playerMax(int player) {
        int[] selection = makeSelection(players[player]);
        int max = 0;
        do {
            int sum = getSum(player, selection);
            max = Math.max(max, sum % 10);
        } while (nextPermutation(selection));

        return max;
    }

    private static int[] makeSelection(int[] player) {
        int[] selection = new int[player.length];
        for (int i = 0; i < 3; i++) {
            selection[i] = 1;
        }

        Arrays.sort(selection);
        return selection;
    }

    private static int getSum(int player, int[] selection) {
        int sum = 0;
        for (int i = 0; i < players[player].length; i++) {
            if (selection[i] == 1) {
                sum += players[player][i];
            }
        }

        return sum;
    }

    private static boolean nextPermutation(int[] selection) {
        int i = selection.length - 1;
        while (i > 0 && selection[i - 1] >= selection[i]) {
            i--;
        }

        if (i < 1) {
            return false;
        }

        int j = selection.length - 1;
        while (selection[i - 1] >= selection[j]) {
            j--;
        }

        int temp = selection[i - 1];
        selection[i - 1] = selection[j];
        selection[j] = temp;
        j = selection.length - 1;
        while (i < j) {
            temp = selection[i];
            selection[i] = selection[j];
            selection[j] = temp;
            i++;
            j--;
        }

        return true;
    }

    private static void printOutputData() throws IOException {
        ioBuffered.print(answer);
    }

    private static class IOBuffered {
        private final BufferedReader bufferedReader;
        private final BufferedWriter bufferedWriter;

        public IOBuffered(String input) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
            InputStreamReader inputStreamReader= new InputStreamReader(byteArrayInputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }

        public String readLine() throws IOException {
            return bufferedReader.readLine();
        }

        public void print(int num) throws IOException {
            write(num);
            flush();
        }

        private void write(int num) throws IOException {
            bufferedWriter.write(String.valueOf(num));
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}