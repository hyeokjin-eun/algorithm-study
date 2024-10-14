package com.company.graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// TODO : 제출 필요
// link
// https://www.acmicpc.net/problem/23747
public class Backjun23747 {

    private static final String[] array = {
            "4 5\n" +
            "aaabc\n" +
            "dcbbc\n" +
            "dccaa\n" +
            "ddaaa\n" +
            "3 4\n" +
            "WLLLWUURRD",
            "3 3\n" +
            "abc\n" +
            "def\n" +
            "ghi\n" +
            "2 2\n" +
            "LU"
    };

    private static IOBuffered ioBuffered;
    private static int R;
    private static int C;
    private static char[][] map;
    private static int sx;
    private static int sy;
    private static String move;
    private static char[][] answer;

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
        StringTokenizer stringTokenizer;
        stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        R = stoi(stringTokenizer.nextToken());
        C = stoi(stringTokenizer.nextToken());
        map = new char[R][C];
        answer = new char[R][C];
        for (int i = 0; i < R; i++) {
            String temp = ioBuffered.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);
                answer[i][j] = '#';
            }
        }

        stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        sx = stoi(stringTokenizer.nextToken()) - 1;
        sy = stoi(stringTokenizer.nextToken()) - 1;
        move = ioBuffered.readLine();
    }

    private static void setAnswer() {
        int cx = sx;
        int cy = sy;
        for (int i = 0; i < move.length(); i++) {
            if (move.charAt(i) == 'W') {
                bfs(cx, cy);
            } else if (move.charAt(i) == 'L') {
                cy--;
            } else if (move.charAt(i) == 'R') {
                cy++;
            } else if (move.charAt(i) == 'U') {
                cx--;
            } else if (move.charAt(i) == 'D') {
                cx++;
            }
        }

        answer[cx][cy] = '.';
        for (int i = 0; i < 4; i++) {
            int nx = cx + ax[i];
            int ny = cy + ay[i];
            if (isNotOutOfIndex(nx, ny)) {
                continue;
            }

            answer[nx][ny] = '.';
        }
    }

    private static final int[] ax = new int[]{0, 1, 0, -1};
    private static final int[] ay = new int[]{-1, 0, 1, 0};
    private static void bfs(final int x, final int y) {
        boolean[][] visit = new boolean[R][C];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        queue.add(y);
        answer[x][y] = '.';
        visit[x][y] = true;
        while (!queue.isEmpty() && queue.size() >= 2) {
            int cx = queue.poll();
            int cy = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cx + ax[i];
                int ny = cy + ay[i];
                if (isNotOutOfIndex(nx, ny) || isVisit(visit, nx, ny) || isNotSameArea(x, y, nx, ny)) {
                    continue;
                }

                queue.add(nx);
                queue.add(ny);
                answer[nx][ny] = '.';
                visit[nx][ny] = true;
            }
        }
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static boolean isNotOutOfIndex(int x, int y) {
        return x < 0 || y < 0 || x >= R || y >= C;
    }

    private static boolean isVisit(boolean[][] visit, int x, int y) {
        return visit[x][y];
    }

    private static boolean isNotSameArea(int ox, int oy, int nx, int ny) {
        return map[ox][oy] != map[nx][ny];
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

        public void print(char[][] answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(char[][] answer) throws IOException {
            for (int i = 0; i < answer.length; i++) {
                for (int j = 0; j < answer[i].length; j++) {
                    bufferedWriter.write(answer[i][j]);
                    if (j == answer[i].length - 1) {
                        bufferedWriter.write("\n");
                    }
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
