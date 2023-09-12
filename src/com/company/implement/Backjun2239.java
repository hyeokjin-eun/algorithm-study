package com.company.implement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// link
// https://www.acmicpc.net/problem/2239
public class Backjun2239 {

    private static final String[] array = {
            "103000509\n" +
            "002109400\n" +
            "000704000\n" +
            "300502006\n" +
            "060000050\n" +
            "700803004\n" +
            "000401000\n" +
            "009205800\n" +
            "804000107",
            "000800900\n" +
            "984000005\n" +
            "000509008\n" +
            "800157090\n" +
            "700090810\n" +
            "190280057\n" +
            "078015009\n" +
            "009700581\n" +
            "561928473"
    };

    private static IOBuffered ioBuffered;
    private static int[][] board;
    private static List<int[]> point;
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
        setData();
        setAnswer();
        printAnswer();
    }

    private static void setData() throws IOException {
        board = new int[9][9];
        point = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            String temp = ioBuffered.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = temp.charAt(j) - '0';
                if (board[i][j] == 0) {
                    point.add(new int[]{i, j});
                }
            }
        }
    }

    private static void setAnswer() {
        recursion(0);
        answer = board;
    }

    private static boolean recursion(int count) {
        if (point.size() == count) {
            return true;
        }

        int x = point.get(count)[0];
        int y = point.get(count)[1];
        boolean[] is = isPossible(x, y);
        for (int i = 1; i < 10; i++) {
            if (!is[i]) {
                board[x][y] = i;
                if (recursion(count + 1)) {
                    return true;
                }
                board[x][y] = 0;
            }
        }

        return false;
    }

    private static boolean[] isPossible(int x, int y) {
        boolean[] ret = new boolean[10];
        for (int i = 0; i < 9; i++) {
            ret[board[x][i]] = true;
        }

        for (int i = 0; i < 9; i++) {
            ret[board[i][y]] = true;
        }

        int sx = (x / 3) * 3;
        int sy = (y / 3) * 3;
        for (int i = sx; i < sx + 3; i++) {
            for (int j = sy; j < sy + 3; j++) {
                ret[board[i][j]] = true;
            }
        }

        return ret;
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

        public void print(int[][] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(int[][] answer) throws IOException {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    bufferedWriter.write(String.valueOf(answer[i][j]));
                }

                if (i != 8) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
