package com.company.java.bf;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/15686
public class Backjun15686_2 {
    private static final String[] array = {
            "5 3\n" +
            "0 0 1 0 0\n" +
            "0 0 2 0 1\n" +
            "0 1 2 0 0\n" +
            "0 0 1 0 0\n" +
            "0 0 0 0 2",
            "5 2\n" +
            "0 2 0 1 0\n" +
            "1 0 1 0 0\n" +
            "0 0 0 0 0\n" +
            "2 0 0 1 1\n" +
            "2 2 0 1 2",
            "5 1\n" +
            "1 2 0 0 0\n" +
            "1 2 0 0 0\n" +
            "1 2 0 0 0\n" +
            "1 2 0 0 0\n" +
            "1 2 0 0 0",
            "5 1\n" +
            "1 2 0 2 1\n" +
            "1 2 0 2 1\n" +
            "1 2 0 2 1\n" +
            "1 2 0 2 1\n" +
            "1 2 0 2 1"
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
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(input.getBytes()));
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        BufferedReader br = new BufferedReader(isr);
        BufferedWriter bw = new BufferedWriter(osw);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] a = new int[N][N];
        ArrayList<Pair> c = new ArrayList<>();
        ArrayList<Pair> h = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                a[i][j] = num;
                if (num == 2) {
                    c.add(new Pair(j, i, 0));
                } else if (num == 1) {
                    h.add(new Pair(j, i, 0));
                }
            }
        }

        int[] b = new int[c.size()];
        for (int i = 0; i < M; i++) {
            b[i] = 1;
        }

        Arrays.sort(b);
        int answer = -1;
        do {
            int temp = bfs(N, c, h, b);
            if (answer == -1 || temp < answer) {
                answer = temp;
            }
        } while(nextPermutation(b));

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static boolean nextPermutation(int[] a) {
        int i = a.length - 1;
        while (i > 0 && a[i - 1] >= a[i]) {
            i--;
        }

        if (i < 1) {
            return false;
        }

        int j = a.length - 1;
        while (a[i - 1] >= a[j]) {
            j--;
        }

        int temp = a[i - 1];
        a[i - 1] = a[j];
        a[j] = temp;
        j = a.length - 1;
        while (i < j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }

        return true;
    }

    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static int bfs(int N, ArrayList<Pair> c, ArrayList<Pair> h, int[] b) {
        Queue<Pair> queue = new LinkedList<>();
        boolean[][] check = new boolean[N][N];
        int[][] dist = new int[N][N];
        for (int i = 0; i < c.size(); i++) {
            if (b[i] == 1) {
                queue.offer(c.get(i));
                check[c.get(i).y][c.get(i).x] = true;
            }
        }

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            for (int i = 0; i < 4; i++) {
                int xn = pair.x + xa[i];
                int yn = pair.y + ya[i];
                if (xn < 0 || yn < 0 || N <= xn || N <= yn) {
                    continue;
                }

                if (!check[yn][xn]) {
                    queue.offer(new Pair(xn, yn, 0));
                    check[yn][xn] = true;
                    dist[yn][xn] = dist[pair.y][pair.x] + 1;
                }
            }
        }

        int answer = 0;
        for (Pair pair : h) {
            answer += dist[pair.y][pair.x];
        }

        return answer;
    }

    private static class Pair {
        int x;
        int y;
        int s;
        public Pair(int x, int y, int s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }
    }
}
