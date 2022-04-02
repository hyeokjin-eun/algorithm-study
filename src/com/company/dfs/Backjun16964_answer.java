package com.company.dfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/16964
public class Backjun16964_answer {
    private static final String[] array = {
            "4\n" + // 0
            "1 2\n" +
            "1 3\n" +
            "2 4\n" +
            "1 2 3 4",
            "4\n" + // 1
            "1 2\n" +
            "1 3\n" +
            "2 4\n" +
            "1 2 4 3",
            "4\n" + // 1
            "1 2\n" +
            "1 3\n" +
            "2 4\n" +
            "1 3 2 4"
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
        int n = Integer.parseInt(br.readLine());
        a = new ArrayList[n];
        check = new boolean[n];
        int[] b = new int[n];
        int[] order = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = new ArrayList<>();
        }

        for (int i=0; i<n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            a[u].add(v);
            a[v].add(u);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            b[i] = Integer.parseInt(st.nextToken())-1;
            order[b[i]] = i;
        }
        for (int i=0; i<n; i++) {
            Collections.sort(a[i], new Comparator<Integer>() {
                public int compare(Integer u, Integer v) {
                    if (order[u] < order[v]) {
                        return -1;
                    } else if (order[u] == order[v]) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
        }
        dfs(0);
        boolean ok = true;
        for (int i=0; i<n; i++) {
            if (dfs_order.get(i) != b[i]) {
                ok = false;
            }
        }
        if (ok) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static ArrayList<Integer>[] a;
    static ArrayList<Integer> dfs_order = new ArrayList<>();
    static boolean[] check;
    static void dfs(int x) {
        if (check[x]) return;
        dfs_order.add(x);
        check[x] = true;
        for (int y : a[x]) {
            dfs(y);
        }
    }
}
