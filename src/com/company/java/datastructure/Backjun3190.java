package com.company.java.datastructure;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/3190
// TODO: 2021-10-07 문제 다시 풀어볼것
public class Backjun3190 {
    private static final String[] array = {
            "6\n" +
            "3\n" +
            "3 4\n" +
            "2 5\n" +
            "5 3\n" +
            "3\n" +
            "3 D\n" +
            "15 L\n" +
            "17 D",
            "10\n" +
            "4\n" +
            "1 2\n" +
            "1 3\n" +
            "1 4\n" +
            "1 5\n" +
            "4\n" +
            "8 D\n" +
            "10 D\n" +
            "11 D\n" +
            "13 L",
            "10\n" +
            "5\n" +
            "1 5\n" +
            "1 3\n" +
            "1 2\n" +
            "1 6\n" +
            "1 7\n" +
            "4\n" +
            "8 D\n" +
            "10 D\n" +
            "11 D\n" +
            "13 L"
    };

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
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[][] a = new int[N][N];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            a[y][x] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        Queue<Pair> direction = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = st.nextToken().charAt(0);
            direction.add(new Pair(x, y));
        }

        int time = 0;
        Deque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(0, 0));
        int[] xa = new int[]{0, 1, 0, -1};
        int[] ya = new int[]{-1, 0, 1, 0};
        int dir = 1;
        int turn = direction.peek().x;
        int c = direction.poll().y;
        while (true) {
            time++;
            Pair pair = q.pollFirst();
            int nx = pair.x + xa[dir % 4];
            int ny = pair.y + ya[dir % 4];

            if (nx < 0 || ny < 0 || N <= nx || N <= ny) {
                break;
            }

            if (a[ny][nx] == -1) {
                break;
            }

            if (a[pair.y][pair.x] == 1) {
                a[pair.y][pair.x] = -1;
                a[pair.y][pair.x] = 0;
                q.addFirst(pair);
            } else {
                a[pair.y][pair.x] = -1;
                q.addFirst(pair);
                a[q.peekLast().y][q.peekLast().x] = 0;
                q.pollLast();
            }

            q.addFirst(new Pair(nx, ny));
            if (time == turn) {
                if (c == 'L') {
                    dir--;
                } else {
                    dir++;
                }

                if (!direction.isEmpty()) {
                    turn = direction.peek().x;
                    c = direction.poll().y;
                }
            }
        }

        bw.write(String.valueOf(time));
        bw.flush();
    }

    private static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
