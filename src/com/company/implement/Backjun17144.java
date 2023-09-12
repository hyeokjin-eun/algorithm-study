package com.company.implement;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/submit/17144
public class Backjun17144 {

    private static final String[] array = {
            "7 8 1\n" +
            "0 0 0 0 0 0 0 9\n" +
            "0 0 0 0 3 0 0 8\n" +
            "-1 0 5 0 0 0 22 0\n" +
            "-1 8 0 0 0 0 0 0\n" +
            "0 0 0 0 0 10 43 0\n" +
            "0 0 5 0 15 0 0 0\n" +
            "0 0 40 0 0 0 20 0",
            "7 8 2\n" +
            "0 0 0 0 0 0 0 9\n" +
            "0 0 0 0 3 0 0 8\n" +
            "-1 0 5 0 0 0 22 0\n" +
            "-1 8 0 0 0 0 0 0\n" +
            "0 0 0 0 0 10 43 0\n" +
            "0 0 5 0 15 0 0 0\n" +
            "0 0 40 0 0 0 20 0",
            "7 8 3\n" +
            "0 0 0 0 0 0 0 9\n" +
            "0 0 0 0 3 0 0 8\n" +
            "-1 0 5 0 0 0 22 0\n" +
            "-1 8 0 0 0 0 0 0\n" +
            "0 0 0 0 0 10 43 0\n" +
            "0 0 5 0 15 0 0 0\n" +
            "0 0 40 0 0 0 20 0",
            "7 8 4\n" +
            "0 0 0 0 0 0 0 9\n" +
            "0 0 0 0 3 0 0 8\n" +
            "-1 0 5 0 0 0 22 0\n" +
            "-1 8 0 0 0 0 0 0\n" +
            "0 0 0 0 0 10 43 0\n" +
            "0 0 5 0 15 0 0 0\n" +
            "0 0 40 0 0 0 20 0",
            "7 8 5\n" +
            "0 0 0 0 0 0 0 9\n" +
            "0 0 0 0 3 0 0 8\n" +
            "-1 0 5 0 0 0 22 0\n" +
            "-1 8 0 0 0 0 0 0\n" +
            "0 0 0 0 0 10 43 0\n" +
            "0 0 5 0 15 0 0 0\n" +
            "0 0 40 0 0 0 20 0",
            "7 8 20\n" +
            "0 0 0 0 0 0 0 9\n" +
            "0 0 0 0 3 0 0 8\n" +
            "-1 0 5 0 0 0 22 0\n" +
            "-1 8 0 0 0 0 0 0\n" +
            "0 0 0 0 0 10 43 0\n" +
            "0 0 5 0 15 0 0 0\n" +
            "0 0 40 0 0 0 20 0",
            "7 8 30\n" +
            "0 0 0 0 0 0 0 9\n" +
            "0 0 0 0 3 0 0 8\n" +
            "-1 0 5 0 0 0 22 0\n" +
            "-1 8 0 0 0 0 0 0\n" +
            "0 0 0 0 0 10 43 0\n" +
            "0 0 5 0 15 0 0 0\n" +
            "0 0 40 0 0 0 20 0",
            "7 8 50\n" +
            "0 0 0 0 0 0 0 9\n" +
            "0 0 0 0 3 0 0 8\n" +
            "-1 0 5 0 0 0 22 0\n" +
            "-1 8 0 0 0 0 0 0\n" +
            "0 0 0 0 0 10 43 0\n" +
            "0 0 5 0 15 0 0 0\n" +
            "0 0 40 0 0 0 20 0"
    };

    private static IOBuffered ioBuffered;
    private static StringTokenizer stringTokenizer;
    private static int R;
    private static int C;
    private static int T;
    private static int[][] room;
    private static int answer;
    private static final int[] top = new int[2];
    private static final int[] bottom = new int[2];

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
        stringTokenizer = new StringTokenizer(ioBuffered.readLine());
        R = Util.stoi(stringTokenizer.nextToken());
        C = Util.stoi(stringTokenizer.nextToken());
        T = Util.stoi(stringTokenizer.nextToken());
        room = new int[R][C];
        for (int i = 0; i < R; i++) {
            stringTokenizer = new StringTokenizer(ioBuffered.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Util.stoi(stringTokenizer.nextToken());
                if (i != 0 && room[i][j] == -1) {
                    top[0] = i - 1;
                    top[1] = j;
                    bottom[0] = i;
                    bottom[1] = j;
                }
            }
        }
    }

    private static void setAnswer() {
        for (int i = 0; i < T; i++) {
            room = Array.diffusion();
            Array.rotationLeft(top[0], top[1]);
            Array.rotationRight(bottom[0], bottom[1]);
        }

        answer = Array.sum(room);
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static class Array {

        private static final int[] xa = new int[]{1, 0, -1, 0};
        private static final int[] ya = new int[]{0, -1, 0, 1};
        public static int[][] diffusion() {
            int[][] ret = Array.copy(room);
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (room[i][j] == -1 || room[i][j] == 0) {
                        continue;
                    }

                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = j + xa[k];
                        int ny = i + ya[k];
                        if (nx < 0 || C <= nx || ny < 0 || R <= ny) {
                            continue;
                        }

                        if (room[ny][nx] == -1) {
                            continue;
                        }

                        ret[ny][nx] += room[i][j] / 5;
                        count++;
                    }

                    ret[i][j] -= room[i][j] / 5 * count;
                }
            }

            return ret;
        }

        public static int[][] copy(int[][] target) {
            int[][] ret = new int[target.length][target[0].length];
            for (int i = 0; i < target.length; i++) {
                System.arraycopy(target[i], 0, ret[i], 0, target[0].length);
            }

            return ret;
        }

        public static int sum(int[][] room) {
            int sum = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (room[i][j] == -1) {
                        continue;
                    }

                    sum += room[i][j];
                }
            }

            return sum;
        }

        public static void rotationLeft(int x, int y) {
            // left
            for (int i = x - 1; 0 <= i; i--) {
                int cur = i;
                int next = i + 1;
                if (room[next][0] != -1) {
                    room[next][0] = room[cur][0];
                    room[cur][0] = 0;
                    if (room[cur][0] != -1) {
                        room[cur][0] = 0;
                    }
                }
            }

            // top
            for (int i = 1; i < C; i++) {
                int cur = i;
                int next = i - 1;
                if (room[0][next] != -1) {
                    room[0][next] = room[0][cur];
                    if (room[0][cur] != -1) {
                        room[0][cur] = 0;
                    }
                }
            }

            // right
            for (int i = 1; i <= x; i++) {
                int cur = i;
                int next = i - 1;
                if (room[next][C - 1] != -1) {
                    room[next][C - 1] = room[cur][C - 1];
                    if (room[cur][C - 1] != -1) {
                        room[cur][C - 1] = 0;
                    }
                }
            }

            // bottom
            for (int i = C - 2; 0 <= i; i--) {
                int cur = i;
                int next = i + 1;
                if (room[x][next] != -1) {
                    room[x][next] = room[x][cur] == -1 ? 0 : room[x][cur];
                    if (room[x][cur] != -1) {
                        room[x][cur] = 0;
                    }
                }
            }
        }

        public static void rotationRight(int x, int y) {
            // left
            for (int i = x + 1; i < R; i++) {
                int cur = i;
                int next = i - 1;
                if (room[next][0] != -1) {
                    room[next][0] = room[cur][0];
                    if (room[cur][0] != -1) {
                        room[cur][0] = 0;
                    }
                }
            }

            // bottom
            for (int i = 1; i < C; i++) {
                int cur = i;
                int next = i - 1;
                if (room[R - 1][next] != -1) {
                    room[R - 1][next] = room[R - 1][cur];
                    if (room[R - 1][cur] != -1) {
                        room[R - 1][cur] = 0;
                    }
                }
            }

            // right
            for (int i = R - 2; x <= i; i--) {
                int cur = i;
                int next = i + 1;
                if (room[next][C - 1] != -1) {
                    room[next][C - 1] = room[cur][C - 1];
                    if (room[cur][C - 1] != -1) {
                        room[cur][C - 1] = 0;
                    }
                }
            }

            // top
            for (int i = C - 2; 0 <= i; i--) {
                int cur = i;
                int next = i + 1;
                if (room[x][next] != -1) {
                    room[x][next] = room[x][cur] == -1 ? 0 : room[x][cur];
                    if (room[x][cur] != -1) {
                        room[x][cur] = 0;
                    }
                }
            }
        }
    }

    private static class Util {
        public static int stoi(String s) {
            return Integer.parseInt(s);
        }
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
