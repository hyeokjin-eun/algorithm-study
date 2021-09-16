package com.company.lecture.algorithm2.graph;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/7562
public class Backjun7562 {
    private static final String[] array = {
            "3\n" +
            "8\n" +
            "0 0\n" +
            "7 0\n" +
            "100\n" +
            "0 0\n" +
            "30 50\n" +
            "10\n" +
            "1 1\n" +
            "1 1"
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

    private static void solution (String input) throws IOException {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int test = Integer.parseInt(br.readLine());
        for (int i = 0; i < test; i++) {
            int xy = Integer.parseInt(br.readLine());
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            Location start = new Location(Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken()));
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            Location end = new Location(Integer.parseInt(st2.nextToken()), Integer.parseInt(st2.nextToken()));
            int[][] a = new int[xy][xy];
            boolean[][] check = new boolean[xy][xy];
            int answer = bfs(start, end, xy, check, a);
            bw.write(String.valueOf(answer));
            bw.write("\n");
        }

        bw.flush();
    }

    private static int bfs (Location start, Location end, int xy, boolean[][] check, int[][] a) {
        Queue<Location> q = new LinkedList<>();
        q.add(start);
        check[start.y][start.x] = true;
        a[start.y][start.x] = 0;
        int[] xw = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
        int[] yh = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
        while (!q.isEmpty()) {
            Location l = q.poll();
            if (end.equals(l)) {
                break;
            }

            int x = l.x;
            int y = l.y;
            for (int i = 0; i < 8; i++) {
                if (0 <= x + xw[i] && x + xw[i] < xy && 0 <= y + yh[i] && y + yh[i] < xy) {
                    if (!check[y + yh[i]][x + xw[i]]) {
                        q.add(new Location(x + xw[i], y + yh[i]));
                        check[y + yh[i]][x + xw[i]] = true;
                        a[y + yh[i]][x + xw[i]] = a[y][x] + 1;
                    }
                }
            }
        }

        return a[end.y][end.x];
    }

    private static class Location {
        int x;
        int y;
        public Location (int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x && y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
