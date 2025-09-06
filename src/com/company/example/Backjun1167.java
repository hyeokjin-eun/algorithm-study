package com.company.example;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Backjun1167 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Node>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            while (true) {
                int item = Integer.parseInt(st.nextToken());
                if (item == -1) {
                    break;
                }

                int value = Integer.parseInt(st.nextToken());
                graph.get(cur).add(new Node(item, value));
            }
        }

        boolean[] visited;
        visited = new boolean[n + 1];
        visited[1] = true;
        int[] first = dfs(graph, 1, 0, visited);

        visited = new boolean[n + 1];
        visited[first[0]] = true;
        int[] second = dfs(graph, first[0], 0, visited);

        bw.write(second[1] + "\n");
        bw.flush();
    }

    public static int[] dfs(ArrayList<ArrayList<Node>> graph, int cur, int count, boolean[] visited) {
        ArrayList<Node> curList = graph.get(cur);
        int[] temp = new int[]{cur, count};
        for (Node item : curList) {
            if (visited[item.index]) {
                continue;
            }

            visited[item.index] = true;
            int[] result= dfs(graph, item.index, count + item.value, visited);
            if (temp[1] < result[1]) {
                temp = result.clone();
            }
        }

        return temp;
    }

    public static class Node {
        int index;
        int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
