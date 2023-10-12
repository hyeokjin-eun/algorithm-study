package com.company.graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// link
// https://www.acmicpc.net/problem/21736
public class Backjun21736 {
    private static final String[] array = {
            "3 5\n" +
            "OOOPO\n" +
            "OIOOX\n" +
            "OOOXP",
            "3 3\n" +
            "IOX\n" +
            "OXP\n" +
            "XPP"
    };

    private static IOBuffered ioBuffered;
    private static int N;
    private static int M;
    private static char[][] map;
    private static boolean[][] visit;
    private static String answer;
    private static int[] start;

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
        map = new char[N][M];
        visit = new boolean[N][M];
        start = new int[2];
        for (int i = 0; i < N; i++) {
            String temp = ioBuffered.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j);
                if (isStartPoint(j, i)) {
                    start[0] = j;
                    start[1] = i;
                }
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer = bfs(start[0], start[1]);
    }

    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static String bfs(int x, int y) {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        visit[y][x] = true;
        queue.add(x);
        queue.add(y);
        while (!queue.isEmpty()) {
            int cx = queue.poll();
            int cy = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (isValid(nx, ny)) {
                    continue;
                }

                if (isPerson(nx, ny)) {
                    count++;
                }

                visit[ny][nx] = true;
                queue.add(nx);
                queue.add(ny);
            }
        }

        return count == 0 ? "TT" : String.valueOf(count);
    }

    private static boolean isValid(int x, int y) {
        return outOfIndex(x, y) || isWall(x, y) || isVisit(x, y);
    }

    private static boolean outOfIndex(int x, int y) {
        return x < 0 || y < 0 || M <= x || N <= y;
    }

    private static boolean isWall(int x, int y) {
        return map[y][x] == 'X';
    }

    private static boolean isPerson(int x, int y) {
        return map[y][x] == 'P';
    }

    private static boolean isStartPoint(int x, int y) {
        return map[y][x] == 'I';
    }

    private static boolean isVisit(int x, int y) {
        return visit[y][x];
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

        public void print(String answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(String answer) throws IOException {
            bufferedWriter.write(answer);
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
