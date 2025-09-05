package com.company.example;

import java.io.*;
import java.util.*;

public class Backjun1260 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x - 1][y - 1] = 1;
            map[y - 1][x - 1] = 1;
        }

        boolean[] visited;

        // DFS
        visited = new boolean[N];
        List<Integer> dfs = new ArrayList<>();
        dfs(map, V - 1, visited, dfs);

        // BFS
        visited = new boolean[N];
        List<Integer> bfs = new ArrayList<>();
        bfs(map, V - 1, visited, bfs);

        for (int i = 0; i < dfs.size(); i++) {
            if (i != 0) {
                bw.write(" ");
            }

            bw.write(String.valueOf(dfs.get(i) + 1));
        }

        bw.write("\n");
        for (int i = 0; i < bfs.size(); i++) {
            if (i != 0) {
                bw.write(" ");
            }

            bw.write(String.valueOf(bfs.get(i) + 1));
        }

        bw.flush();
    }

    private static void dfs(int[][] map, int cur, boolean[] visited, List<Integer> dfs) {
        visited[cur] = true;
        dfs.add(cur);
        for (int i = 0; i < map.length; i++) {
            if (map[cur][i] == 0) {
                continue;
            }

            if (visited[i]) {
                continue;
            }

            dfs(map, i, visited, dfs);
        }
    }

    private static void bfs(int[][] map, int cur, boolean[] visited, List<Integer> bfs) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(cur);
        visited[cur] = true;
        while (!queue.isEmpty()) {
            int t = queue.poll();
            bfs.add(t);
            for (int i = 0; i < map.length; i++) {
                if (map[t][i] == 0) {
                    continue;
                }

                if (visited[i]) {
                    continue;
                }

                visited[i] = true;
                queue.add(i);
            }
        }
    }
}
