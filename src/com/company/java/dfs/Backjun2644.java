package com.company.java.dfs;

import java.io.*;
import java.util.*;

// link
// https://www.acmicpc.net/problem/2644
public class Backjun2644 {
    private static final String[] array = {
            "9\n" +
            "7 3\n" +
            "7\n" +
            "1 2\n" +
            "1 3\n" +
            "2 7\n" +
            "2 8\n" +
            "2 9\n" +
            "4 5\n" +
            "4 6",
            "9\n" +
            "8 6\n" +
            "7\n" +
            "1 2\n" +
            "1 3\n" +
            "2 7\n" +
            "2 8\n" +
            "2 9\n" +
            "4 5\n" +
            "4 6"
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
        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            a.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken()) - 1;
            a.get(f).add(t);
            a.get(t).add(f);
        }

        boolean[] check = new boolean[n];
        int answer = dfs(from - 1, to - 1, 0, check, a);
        bw.write(String.valueOf(answer == 0 ? -1 : answer));
        bw.flush();
    }

    private static int dfs(int c, int e, int index, boolean[] check, ArrayList<ArrayList<Integer>> a) {
        if (e == c) {
            return index;
        }

        check[c] = true;
        for (int i = 0; i < a.get(c).size(); i++) {
            if (check[a.get(c).get(i)]) {
                continue;
            }

            int temp = dfs(a.get(c).get(i), e, index + 1, check, a);
            if (temp != 0) {
                return temp;
            }
        }

        return 0;
    }
}
