package com.company.bfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16946
public class Backjun16946 {
    private static final int[] xa = new int[]{1, 0, -1, 0};
    private static final int[] ya = new int[]{0, 1, 0, -1};
    private static final String[] array = {
            "3 3\n" +
            "101\n" +
            "010\n" +
            "101",
            "4 5\n" +
            "11001\n" +
            "00111\n" +
            "01010\n" +
            "10101"
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
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int[][] gc = new int[N][M];
        int[] group = new int[500001];
        int index = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M ; j++) {
                if (gc[i][j] == 0 && board[i][j] == 0) {
                    int cnt = bfs(j, i, N, M, board, gc, index);
                    group[index] = cnt;
                    index++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M ; j++) {
                if (board[i][j] == 0) {
                    bw.write(String.valueOf(0));
                } else {
                    HashSet<Integer> sg = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int nx = j + xa[k];
                        int ny = i + ya[k];
                        if (nx < 0 || ny < 0 || M <= nx || N <= ny) {
                            continue;
                        }

                        sg.add(gc[ny][nx]);
                    }

                    ArrayList<Integer> temp = new ArrayList<>(sg);
                    int sum = 1;
                    for (int k = 0 ; k < temp.size(); k++) {
                        sum = sum + group[temp.get(k)];
                    }

                    bw.write(String.valueOf(sum % 10));
                }
            }

            if(i != N - 1){
                bw.write("\n");
            }
        }

        bw.flush();
    }

    private static int bfs(int x, int y, int N, int M, int[][] board, int[][] gc, int group) {
        Queue<Integer> xq = new LinkedList<>();
        Queue<Integer> yq = new LinkedList<>();
        xq.add(x);
        yq.add(y);
        gc[y][x] = group;
        int cnt = 1;
        while (!xq.isEmpty() && !yq.isEmpty()) {
            int cx = xq.poll();
            int cy = yq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cx + xa[i];
                int ny = cy + ya[i];
                if (nx < 0 || ny < 0 || M <= nx || N <= ny) {
                    continue;
                }

                if (gc[ny][nx] == 0 && board[ny][nx] == 0) {
                    gc[ny][nx] = group;
                    xq.add(nx);
                    yq.add(ny);
                    cnt++;
                }
            }
        }

        return cnt;
    }
}