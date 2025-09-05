package com.company.example;

import java.io.*;
import java.util.*;

public class Backjun11725 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        // 인접 리스트
        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            g[b].add(a);
        }

        int[] parent = new int[n + 1]; // parent[1] = 0 (루트)
        bfsParent(1, g, parent);

        for (int i = 2; i <= n; i++) out.append(parent[i]).append('\n');
        System.out.print(out);
    }

    private static void bfsParent(int root, List<Integer>[] g, int[] parent) {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(root);
        parent[root] = 0; // 혹은 root 자기 자신으로 해도 무방

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : g[cur]) {
                if (parent[nxt] != 0 || nxt == root) continue; // 방문 체크: parent 할당 여부로 대체
                parent[nxt] = cur;  // 부모 기록
                q.add(nxt);
            }
        }
    }
}
