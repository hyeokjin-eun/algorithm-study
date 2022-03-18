package com.company.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/6593
public class Backjun6593 {
    private static final String[] array = {
            "3 4 5\n" +
            "S....\n" +
            ".###.\n" +
            ".##..\n" +
            "###.#\n" +
            "\n" +
            "#####\n" +
            "#####\n" +
            "##.##\n" +
            "##...\n" +
            "\n" +
            "#####\n" +
            "#####\n" +
            "#.###\n" +
            "####E\n" +
            "\n" +
            "1 3 3\n" +
            "S##\n" +
            "#E#\n" +
            "###\n" +
            "\n" +
            "0 0 0"
    };
    private static IOBuffered ioBuffered;
    private static int L;
    private static int R;
    private static int C;
    private static Pair start;
    private static Pair end;
    private static char[][][] building;
    private static int[][][] dist;
    private static ArrayList<Integer> answer;

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
        ioBuffered = IOBuffered.create(input);
        answer = new ArrayList<>();
        while (true) {
            setBuildingInfo();
            if (closeCase()) {
                break;
            }

            setData();
            setAnswer();
        }

        printAnswer();
    }

    private static void setBuildingInfo() throws IOException {
        StringTokenizer st = new StringTokenizer(ioBuffered.readLine());
        L = stoi(st.nextToken());
        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
    }

    private static boolean closeCase() {
        return L == 0 && R == 0 && C == 0;
    }

    private static void setData() throws IOException {
        building = new char[L][R][C];
        for (int l = 0; l < L; l++) {
            for (int r = 0; r < R; r++) {
                char[] chars = ioBuffered.readLine().toCharArray();
                for (int c = 0; c < C; c++) {
                    building[l][r][c] = chars[c];
                    if (building[l][r][c] == 'S') {
                        start = Pair.of(l, r, c);
                    }

                    if (building[l][r][c] == 'E') {
                        end = Pair.of(l, r, c);
                    }
                }
            }

            ioBuffered.readLine();
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void setAnswer() {
        answer.add(bfs());
    }

    private static final int[] za = new int[]{0, 0, 0, 0, 1, -1};
    private static final int[] xa = new int[]{0, 1, 0, -1, 0, 0};
    private static final int[] ya = new int[]{1, 0, -1, 0, 0, 0};

    private static int bfs() {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(start);
        dist = new int[L][R][C];
        while (!queue.isEmpty()) {
            Pair curPair = queue.poll();
            int curZ = curPair.getZ();
            int curY = curPair.getY();
            int curX = curPair.getX();
            for (int i = 0; i < 6; i++) {
                int nextZ = curZ + za[i];
                int nextY = curY + ya[i];
                int nextX = curX + xa[i];
                if (isArrayOutOfIndex(nextZ, nextY, nextX)) {
                    continue;
                }

                if (isNotVisit(nextZ, nextY, nextX) && isNotWall(nextZ, nextY, nextX)) {
                    Pair nextPair = Pair.of(nextZ, nextY, nextX);
                    setVisit(curPair, nextPair);
                    queue.offer(nextPair);
                }
            }
        }

        return dist[end.getZ()][end.getY()][end.getX()];
    }

    private static boolean isArrayOutOfIndex(int z, int y, int x) {
        return z < 0 || L <= z || y < 0 || R <= y || x < 0 || C <= x;
    }

    private static boolean isNotVisit(int z, int y, int x) {
        return dist[z][y][x] == 0;
    }

    private static void setVisit(Pair cur, Pair next) {
        dist[next.getZ()][next.getY()][next.getX()] = dist[cur.getZ()][cur.getY()][cur.getX()] + 1;
    }

    private static boolean isNotWall(int z, int y, int x) {
        return building[z][y][x] != '#';
    }

    private static void printAnswer() throws IOException {
        ioBuffered.print(answer);
    }

    private static class Pair {
        private final int z;
        private final int y;
        private final int x;

        private Pair(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }

        public static Pair of(int z, int y, int x) {
            return new Pair(z, y, x);
        }

        public int getZ() {
            return z;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    private static class IOBuffered {
        private final BufferedReader bufferedReader;
        private final BufferedWriter bufferedWriter;

        private IOBuffered(String input) throws IOException {
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

        public void print(ArrayList<Integer> answer) throws IOException {
            write(answer);
            flush();
        }

        private void write(ArrayList<Integer> answer) throws IOException {
            for (int i = 0; i < answer.size(); i++) {
                if (answer.get(i) == 0) {
                    bufferedWriter.write("Trapped!");
                } else {
                    bufferedWriter.write("Escaped in " + answer.get(i) + " minute(s).");
                }

                if (i != answer.size() - 1) {
                    bufferedWriter.write("\n");
                }
            }
        }

        private void flush() throws IOException {
            bufferedWriter.flush();
        }
    }
}
